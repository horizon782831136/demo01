package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.OrderitemDao;
import com.liuwei.domain.Orderitem;
import com.liuwei.domain.Product;
import com.liuwei.listener.OrderitemListener;
import com.liuwei.listener.OrdersListener;
import com.liuwei.listener.ProductListener;
import com.liuwei.service.OrderitemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.service.ProductService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.utils.ExcelUtils;
import com.liuwei.utils.Result;
import com.liuwei.utils.ResultUtils;
import com.liuwei.vo.AddOrderitemVo;
import com.liuwei.vo.AddProductVo;
import com.liuwei.vo.CustomerOrderitemVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 订单明细表(TblOrderitem)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:57:50
 */
@Service
public class OrderitemServiceImpl extends ServiceImpl<OrderitemDao, Orderitem> implements OrderitemService {
    @Resource
    private OrderitemService orderitemService;
    @Resource
    private ProductService productService;
    @Override
    public Result addOrderitem(Orderitem orderitem) {
        orderitem.setCreateTime(new Date());
        orderitem.setUpdateTime(null);
        boolean flag = orderitemService.save(orderitem);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateOrderitem(Orderitem orderitem) {
        orderitem.setUpdateTime(new Date());
        orderitem.setCreateTime(null);
        boolean flag = orderitemService.updateById(orderitem);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getOrderitemById(Long id) {
        LambdaQueryWrapper<Orderitem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orderitem::getId, id);
        return new Result(orderitemService.getOne(queryWrapper));
    }

    @Override
    public Result getOrderitemByPage(Integer current, Integer size) {
        IPage<Orderitem> page = new Page<>(current, size);
        orderitemService.page(page);
        return new Result(page);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = orderitemService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getByUserId(Long id) {
        LambdaQueryWrapper<Orderitem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orderitem::getUserId, id);
        List<Orderitem> orderitems = orderitemService.list(queryWrapper);
        return new Result(orderitems);
    }

    @Override
    public Result getByProId(Long id) {
        LambdaQueryWrapper<Orderitem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orderitem::getProId, id);
        List<Orderitem> orderitems = orderitemService.list(queryWrapper);
        return new Result(orderitems);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddOrderitemVo.class, BeanCopyUtils.copyBeanList(
                orderitemService.list(), AddOrderitemVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddOrderitemVo.class, new OrderitemListener());
    }

    @Override
    public Result getByCustomerId(Long id) {
        LambdaQueryWrapper<Orderitem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orderitem::getUserId, id);
        List<CustomerOrderitemVo> collect = orderitemService.list(queryWrapper).stream().parallel().map((orderitem -> {
            LambdaQueryWrapper<Product> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Product::getProId, orderitem.getProId());
            Product one = productService.getOne(queryWrapper1);
            CustomerOrderitemVo customerOrderitemVo = BeanCopyUtils.copyBean(orderitem, CustomerOrderitemVo.class);
            //将商品中的其他属性绑定上去
//            customerOrderitemVo.setCateId(one.getCateId());
//            customerOrderitemVo.setName(one.getName());
//            customerOrderitemVo.setSubtitle(one.getName());
//            customerOrderitemVo.setSubimages(one.getSubimages());
//            customerOrderitemVo.setDetail(one.getDetail());
//            customerOrderitemVo.setPrice(one.getPrice());
            BeanUtils.copyProperties(one, customerOrderitemVo);
            return customerOrderitemVo;
        })).collect(Collectors.toList());
        return new Result(collect);
    }
}
