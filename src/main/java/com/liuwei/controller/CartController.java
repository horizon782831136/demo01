package com.liuwei.controller;

import com.liuwei.domain.Cart;
import com.liuwei.domain.Orderitem;
import com.liuwei.domain.Payinfo;
import com.liuwei.domain.User;
import com.liuwei.service.CartService;
import com.liuwei.service.OrderitemService;
import com.liuwei.service.PayinfoService;
import com.liuwei.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车表(Cart)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:12:02
 */
@RestController
@RequestMapping("cart")
public class CartController {
    /**
     * 服务对象
     */
    @Resource
    private CartService cartService;

    //添加购物车
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PostMapping
    public Result addCart(@RequestBody Cart cart){
        return cartService.addCart(cart);
    }

    //对购物车信息进行变更，能变更的信息包括商品数量、是否勾选、和商品
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PutMapping
    public Result updateMenu(@RequestBody Cart cart){
        return cartService.updateCart(cart);
    }

    //根据购物车id来查询购物车信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{id}")
    public Result getCartById(@PathVariable Long id){
        return cartService.getCartById(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getCartByPage(@PathVariable Integer current, @PathVariable Integer size){
        return cartService.getCartByPage(current, size);
    }
    //查询所有购物车信息
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    public Result getAll(){
        return cartService.getAll();
    }

    //根据购物车id逻辑删除信息
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    public Result deleteById(@PathVariable Long id){
        return cartService.deleteById(id);
    }

    //批量删除根据id删除购物车信息
    @DeleteMapping("/batch")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    public Result deleteByBatch(@RequestBody List<Long> ids){
        return cartService.deleteByBatch(ids);
    }
    //商品提交订单
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PutMapping("/settle/{userId}")
    public Result settleAccounts(@RequestBody List<Cart> carts, @PathVariable Long userId) {
        return cartService.settleAccount(carts, userId);
    }

    //根据用户id查询
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Long id){
        return cartService.getByUserId(id);
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        cartService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        cartService.doUpload(file);
    }
}

