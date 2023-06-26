package com.liuwei.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuwei.domain.User;
import com.liuwei.service.UserService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.utils.Result;
import com.liuwei.vo.LoginUserVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
//引入其它模块的实体类需要加入@EntityScan

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    //查看所有用户
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping
    public Result getAll(){
        return userService.getAll();
    }
    //根据id查询
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        return userService.getById(id);
    }

    //分页查询
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getByPage(@PathVariable Integer current, @PathVariable Integer size){
        return userService.getByPage(current, size);
    }

    //添加用户
    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping
    public Result addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    //修改用户(需要密码)
    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping
    public Result updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    //修改用户(不需要密码)
    @PreAuthorize("hasAnyRole('ROLE_admin','ROLE_user')")
    @PutMapping("/basic")
    public Result updateBasicInfo(@RequestBody User user){
        return userService.updateBasicInfo(user);
    }
    //逻辑删除用户
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return userService.deleteById(id);
    }

    //分页查询匹配相似的手机号
    @GetMapping("/page/{tel}/{current}/{size}")
    public Result getLikeByPage(@PathVariable String tel, @PathVariable Integer current, @PathVariable Integer size){
        return userService.getLikeByPage(tel, current, size);
    }

    //下载用户数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        userService.doDownLoad(response);
    }

    //上传用户数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        userService.doUpload(file);
    }

    //批量删除
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/batch")
    public Result deleteByBatch(@RequestBody List<Long> ids){
        return userService.deleteByBatch(ids);
    }

}
