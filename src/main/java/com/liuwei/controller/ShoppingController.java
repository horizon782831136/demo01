package com.liuwei.controller;

import com.liuwei.domain.Shopping;
import com.liuwei.service.ShoppingService;
import com.liuwei.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 收货信息表(Shopping)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:16:13
 */
@RestController
@RequestMapping("shopping")
public class ShoppingController {
    /**
     * 服务对象
     */
    @Resource
    private ShoppingService shoppingService;

    //添加收获信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PostMapping
    public Result addShopping(@RequestBody Shopping shopping){
        return shoppingService.addShopping(shopping);
    }

    //变更收获信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PutMapping
    public Result updateShopping(@RequestBody Shopping shopping){
        return shoppingService.updateShopping(shopping);
    }

    //查询收获信息
    //根据用户id来查询收获信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{id}")
    public Result getShoppingById(@PathVariable Long id){
        return shoppingService.getShoppingById(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getUserByPage(@PathVariable Integer current, @PathVariable Integer size){
        return shoppingService.getShoppingByPage(current, size);
    }

    //查询所有收获信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping
    public Result getAll(){
        return shoppingService.getAll();
    }

    //根据收货id逻辑删除收获信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return shoppingService.deleteById(id);
    }

    //下载数据
    //@PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        shoppingService.doDownLoad(response);
    }

    //上传数据
    //@PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        shoppingService.doUpload(file);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    public Result getUserAddress(@PathVariable Long id){
        return shoppingService.getUserAddress(id);
    }
}

