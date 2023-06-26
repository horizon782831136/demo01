package com.liuwei.controller;

import com.liuwei.domain.Orderitem;
import com.liuwei.service.OrderitemService;
import com.liuwei.utils.Result;
import com.liuwei.utils.ResultUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单明细表(Orderitem)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:15:01
 */
@RestController
@RequestMapping("orderitem")
public class OrderitemController {
    /**
     * 服务对象
     */
    @Resource
    private OrderitemService orderitemService;

    //根据订单信息添加订单明细
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PostMapping
    public Result addOrderitem (@RequestBody Orderitem orderitem){
        return orderitemService.addOrderitem(orderitem);
    }

    //选择对要变更的订单明细进行变更
    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping
    public Result updateOrderitem (@RequestBody Orderitem orderitem){
        return orderitemService.updateOrderitem(orderitem);
    }

    //根据订单明细id来查询权限信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/{id}")
    public Result getOrderitemById(@PathVariable Long id){
        return orderitemService.getOrderitemById(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getOrderitemByPage(@PathVariable Integer current, @PathVariable Integer size){
        return orderitemService.getOrderitemByPage(current, size);
    }

    //查询所有订单明细信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping
    public Result getAll(){
        return new Result(orderitemService.list());
    }

    //根据订单详情id逻辑删除订单明细
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return orderitemService.deleteById(id);
    }

    //根据用户id查询订单明细
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Long id){
        return orderitemService.getByUserId(id);
    }

    //根据商品id查询订单明细
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/pro/{id}")
    public Result getByProId(@PathVariable Long id){
        return orderitemService.getByProId(id);
    }

    //根据用户id查询该用户的所有订单明细，包括部分商品信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/customer/{id}")
    public Result getByCustomerId(@PathVariable Long id){
        return orderitemService.getByCustomerId(id);
    }
    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        orderitemService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        orderitemService.doUpload(file);
    }
}

