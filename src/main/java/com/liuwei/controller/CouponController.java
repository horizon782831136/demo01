package com.liuwei.controller;

import com.liuwei.domain.Coupon;
import com.liuwei.service.CouponService;
import com.liuwei.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 购物券(Coupon)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:13:40
 */
@RestController
@RequestMapping("coupon")
public class CouponController {
    /**
     * 服务对象
     */
    @Resource
    private CouponService couponService;

    //根据购物券信息添加购物券
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PostMapping
    public Result addCoupon(@RequestBody Coupon coupon){
        return couponService.addCoupon(coupon);
    }

    //选择对要变更的购物券信息进行变更
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PutMapping
    public Result updateCoupon(@RequestBody Coupon coupon){
        return couponService.updateCoupon(coupon);
    }

    //根据购物券id来查询购物券信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/{id}")
    public Result getCouponById(@PathVariable Long id){
        return couponService.getCouponById(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getCouponByPage(@PathVariable Integer current, @PathVariable Integer size){
        return couponService.getCouponByPage(current, size);
    }
    //查询所有购物券信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping
    public Result getAll(){
        return new Result(couponService.list());
    }

    //根据用户id逻辑删除购物券信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return couponService.deleteById(id);
    }

    //将用户id绑定到购物券上,传入用户id和购物券id
    @PutMapping("/{userId}/{couponId}")
    public Result giveOutCoupon(@PathVariable Long userId, @PathVariable Long couponId){
        return couponService.giveOutCoupon(userId, couponId);
    }

    //将购物券用户id清空
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PutMapping("/recycle/{couponId}")
    public Result recycleCoupon(@PathVariable Long couponId){
        return couponService.recycleCoupon(couponId);
    }

    //将订单id绑定到购物券商
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PutMapping("/using/{couponId}/{ordersId}")
    public Result usingCoupon(@PathVariable Long couponId, @PathVariable Long ordersId){
        return couponService.usingCoupon(couponId, ordersId);
    }

    //根据用户id查询购物券
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Long id){
        return couponService.getByUserId(id);
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        couponService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        couponService.doUpload(file);
    }

    //批量删除
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/batch")
    public Result deleteByBatch(@RequestBody List<Coupon> coupons){
        return couponService.deleteByBatch(coupons);
    }
}

