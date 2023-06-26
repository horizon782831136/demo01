package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.PayinfoDao;
import com.liuwei.domain.Orders;
import com.liuwei.domain.Payinfo;
import com.liuwei.listener.PayinfoListener;
import com.liuwei.listener.ProductListener;
import com.liuwei.service.OrdersService;
import com.liuwei.service.PayinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddPayinfoVo;
import com.liuwei.vo.AddProductVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * 支付信息表(TblPayinfo)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:58:15
 */
@Service
public class PayinfoServiceImpl extends ServiceImpl<PayinfoDao, Payinfo> implements PayinfoService {
    @Resource
    private PayinfoService payinfoService;
    @Resource
    private OrdersService ordersService;
    @Override
    public Result addPayInfo(Payinfo payinfo) {
        payinfo.setUpdateTime(null);
        payinfo.setCreateTime(new Date());
        payinfo.setDelFlag(Default.DEFAULT_DELETE);
        payinfo.setPlatformStatus(Default.DEFAULT_PLATFORM_STATUS);
        //生成随机数
        Date date = new Date();
        long time = date.getTime();
        Random random = new Random(time + (int)(Math.random() * 1e10));
        //生成支付码
        long currentPayCode = random.nextLong();
        //将支付码绑定到支付信息中
        payinfo.setPlatformNumber(currentPayCode);
        boolean flag = payinfoService.save(payinfo);
        if(flag){
            //根据支付信息，调整订单状况
            LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Orders::getOrderId, payinfo.getOrderId());
            Orders temp = ordersService.getOne(queryWrapper);
            temp.setUpdateTime(new Date());
            temp.setStatus(Code.ORDER_PAYED.getKey());
            if(!ordersService.updateById(temp)){
                flag = false;
            }
        }
        return ResultUtils.add(flag);
    }

    @Override
    public Result updatePayInfo(Payinfo payinfo) {
        payinfo.setUpdateTime(new Date());
        payinfo.setCreateTime(null);
        boolean flag = payinfoService.updateById(payinfo);
        return ResultUtils.update(flag);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = payinfoService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getPayinfoId(Long id) {
        LambdaQueryWrapper<Payinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Payinfo::getPayId, id);
        return new Result(payinfoService.getOne(queryWrapper));
    }

    @Override
    public Result getPayinfoByPage(Integer current, Integer size) {
        IPage<Payinfo> page = new Page<>(current, size);
        payinfoService.page(page);
        return new Result(page);
    }

    @Override
    public Result getByUserId(Long id) {
        LambdaQueryWrapper<Payinfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Payinfo::getUserId, id);
        List<Payinfo> payinfos = payinfoService.list(queryWrapper);
        return new Result(payinfos);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddPayinfoVo.class, BeanCopyUtils.copyBeanList(
                payinfoService.list(), AddPayinfoVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddPayinfoVo.class, new PayinfoListener());
    }
}
