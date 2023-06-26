package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.OrdersDao;
import com.liuwei.domain.Orderitem;
import com.liuwei.domain.Orders;
import com.liuwei.domain.Payinfo;
import com.liuwei.listener.OrdersListener;
import com.liuwei.service.OrderitemService;
import com.liuwei.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.service.PayinfoService;
import com.liuwei.utils.*;
import com.liuwei.vo.AddOrdersVo;
import com.liuwei.vo.CustomerOrderVo;
import com.liuwei.vo.CustomerOrderitemVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 订单表(TblOrders)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:58:03
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersDao, Orders> implements OrdersService {
    @Resource
    private OrdersService ordersService;
    @Resource
    private OrderitemService orderitemService;
    @Resource
    private PayinfoService payinfoService;
    @Override
    public Result addOrders(Orders orders) {
        orders.setCreateTime(new Date());
        orders.setUpdateTime(null);
        orders.setDelFlag(Default.DEFAULT_DELETE);
        boolean flag = ordersService.save(orders);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateOrders(Orders orders) {
        orders.setUpdateTime(new Date());
        orders.setCreateTime(null);
        boolean flag = ordersService.updateById(orders);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getOrdersById(Long id) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, id);
        return new Result(ordersService.list(queryWrapper));
    }

    @Override
    public Result getOrdersByPage(Integer current, Integer size) {
        IPage<Orders> page = new Page(current, size);
        ordersService.page(page);
        return new Result(page);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = ordersService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getByUserId(Long id) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, id);
        List<Orders> orders = ordersService.list(queryWrapper);
        return new Result(orders);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddOrdersVo.class, BeanCopyUtils.copyBeanList(
                ordersService.list(), AddOrdersVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddOrdersVo.class, new OrdersListener());
    }

    @Override
    public Result getCustomerOrders(Long id) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, id);
        List<CustomerOrderVo> collect = ordersService.list(queryWrapper).stream().parallel().map(orders -> {
            LambdaQueryWrapper<Orderitem> orderitemLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderitemLambdaQueryWrapper.eq(Orderitem::getOrderId, orders.getOrderId());
            List<Orderitem> list = orderitemService.list(orderitemLambdaQueryWrapper);
            List<CustomerOrderitemVo> customerOrderitemVos = BeanCopyUtils.copyBeanList(list, CustomerOrderitemVo.class);
            //先复制orders里的属性
            CustomerOrderVo customerOrderVo = BeanCopyUtils.copyBean(orders, CustomerOrderVo.class);
            //再复制orderitem里的
            customerOrderVo.setProduct(customerOrderitemVos);
            return customerOrderVo;
            //按时间逆序排序
        }).sorted((o1, o2) ->
            o2.getCreateTime().compareTo(o1.getCreateTime())
        ).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public Result buyProductDirectly(Orderitem orderitem, Long userId, Long addressId, Long platform) {
        //先生成订单详情
        orderitemService.addOrderitem(orderitem);
        //再生成订单信息
        Orders orders = new Orders();
        orders.setShoppingId(addressId);
        orders.setUserId(userId);
        orders.setPostage(Default.DEFAULT_POSTAGE);
        orders.setStatus(Code.ORDER_PAYED.getKey());
        orders.setPayment(orderitem.getCurrentUnitPrice() * orderitem.getQuantity());
        orders.setPaymentTime(new Date());
        //生成支付信息
        Payinfo payinfo = new Payinfo();
        payinfo.setOrderId(orders.getOrderId());
        payinfo.setUserId(userId);
        payinfo.setPlatformNumber(platform);
        //保存
        ordersService.addOrders(orders);
        Integer code = payinfoService.addPayInfo(payinfo).getCode();
        boolean flag = code == 11001 ? true: false;
        return ResultUtils.add(flag);
    }

    @Override
    public Result closeTradeByOrderId(Long id) {
        LambdaUpdateWrapper<Orders> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Orders::getOrderId, id);
        updateWrapper.set(Orders::getStatus, Code.ORDER_TRADE_COLOSED.getKey());
        updateWrapper.set(Orders::getCloseTime, new Date());
        boolean flag = ordersService.update(updateWrapper);
        return ResultUtils.update(flag);
    }
}
