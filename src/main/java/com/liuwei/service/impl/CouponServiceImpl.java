package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.CouponDao;
import com.liuwei.domain.Coupon;
import com.liuwei.listener.CouponListener;
import com.liuwei.service.CouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddCouponVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 购物券(Coupon)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:57:06
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponDao, Coupon> implements CouponService {
    @Resource
    CouponService couponService;

    @Override
    public Result addCoupon(Coupon coupon) {
        coupon.setUserId(Default.DEFAULT_USERID);
        coupon.setDelFlag(Default.DEFAULT_DELETE);
        coupon.setStatus(Default.DEFAULT_STATUS);
        boolean flag = couponService.save(coupon);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateCoupon(Coupon coupon) {
        boolean flag = couponService.updateById(coupon);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getCouponById(Long id) {
        LambdaQueryWrapper<Coupon> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Coupon::getId, id);
        return new Result(couponService.getOne(queryWrapper));
    }

    @Override
    public Result getCouponByPage(Integer current, Integer size) {
        IPage<Coupon> page = new Page<>(current, size);
        couponService.page(page);
        return new Result(page);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = couponService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result giveOutCoupon(Long userId, Long couponId) {
        LambdaUpdateWrapper<Coupon> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Coupon::getId, couponId)
                .set(Coupon::getUserId, userId);
        boolean flag = couponService.update(updateWrapper);
        return ResultUtils.update(flag);
    }

    @Override
    public Result recycleCoupon(Long couponId) {
        LambdaUpdateWrapper<Coupon> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Coupon::getId, couponId)
                .set(Coupon::getUserId, Default.DEFAULT_USERID);
        boolean flag = couponService.update(updateWrapper);
        return ResultUtils.update(flag);
    }

    @Override
    public Result usingCoupon(Long couponId, Long ordersId) {
        LambdaUpdateWrapper<Coupon> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Coupon::getId, couponId)
                .set(Coupon::getOrdersId, ordersId);
        boolean flag = couponService.update(updateWrapper);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getByUserId(Long id) {
        LambdaUpdateWrapper<Coupon> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Coupon::getUserId, id);
        List<Coupon> coupons = couponService.list(updateWrapper);
        return new Result(coupons);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddCouponVo.class, BeanCopyUtils.copyBeanList(
                couponService.list(), AddCouponVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddCouponVo.class, new CouponListener());
    }

    @Override
    public Result deleteByBatch(List<Coupon> coupons) {
        List<Long> ids = coupons.stream().parallel().map(coupon -> coupon.getId()).collect(Collectors.toList());
        boolean flag = couponService.removeByIds(ids);
        return ResultUtils.delete(flag);
    }

}
