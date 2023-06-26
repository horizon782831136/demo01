package com.liuwei.service;

import com.liuwei.domain.Coupon;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 购物券(Coupon)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:57:06
 */
@Service
public interface CouponService extends IService<Coupon>{


    Result addCoupon(Coupon coupon);

    Result updateCoupon(Coupon coupon);

    Result getCouponById(Long id);

    Result getCouponByPage(Integer current, Integer size);

    Result deleteById(Long id);

    Result giveOutCoupon(Long userId, Long couponId);

    Result recycleCoupon(Long couponId);

    Result usingCoupon(Long couponId, Long ordersId);

    Result getByUserId(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result deleteByBatch(List<Coupon> coupons);
}
