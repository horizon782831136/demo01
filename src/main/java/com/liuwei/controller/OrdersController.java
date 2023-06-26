package com.liuwei.controller;

import com.liuwei.domain.Orderitem;
import com.liuwei.domain.Orders;
import com.liuwei.service.OrdersService;
import com.liuwei.utils.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单表(Orders)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:15:14
 */
@RestController
@RequestMapping("orders")
public class OrdersController {
    /**
     * 服务对象
     */
    @Resource
    private OrdersService ordersService;

    //根据订单信息新增订单
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PostMapping
    public Result addOrders(@RequestBody Orders orders){
        return ordersService.addOrders(orders);
    }

    //选择对要变更的订单信息进行变更
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PutMapping
    public Result updateOrders (@RequestBody Orders orders){
        return ordersService.updateOrders(orders);
    }

    //根据订单id来查询订单信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{id}")
    public Result getOrdersById(@PathVariable Long id){
        return ordersService.getOrdersById(id);
    }

    //用户查询订单
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/customer/{id}")
    public Result getCustomerOrders(@PathVariable Long id){
        return ordersService.getCustomerOrders(id);
    }
    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getMenuByPage(@PathVariable Integer current, @PathVariable Integer size){
        return ordersService.getOrdersByPage(current, size);
    }
    //根据用户id查询订单信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Long id){
        return ordersService.getByUserId(id);
    }

    //查询所有订单信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping
    public Result getAll(){
        return new Result(ordersService.list());
    }

    //根据订单id逻辑删除订单信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return ordersService.deleteById(id);
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ordersService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        ordersService.doUpload(file);
    }

    //商品直接购买
    @PostMapping("/directly/{userId}/{addressId}/{platform}")
    public Result buyProductDirectly(@RequestBody Orderitem orderitem, @PathVariable Long userId, @PathVariable Long addressId, @PathVariable Long platform){
        return ordersService.buyProductDirectly(orderitem, userId, addressId, platform);
    }

    //客户关闭交易
    @PutMapping("/close/{id}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    public Result closeTradeByOrderId(@PathVariable Long id){
        return ordersService.closeTradeByOrderId(id);
    }
}

