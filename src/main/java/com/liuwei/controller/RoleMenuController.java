package com.liuwei.controller;

import com.liuwei.domain.RoleMenu;
import com.liuwei.service.RoleMenuService;
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

/**
 * 角色-权限关联表(RoleMenu)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:16:07
 */
@RestController
@RequestMapping("roleMenu")
public class RoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private RoleMenuService roleMenuService;

    //给角色添加权限
    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping
    public Result addRoleMenu (@RequestBody RoleMenu roleMunu){
        return roleMenuService.addRoleMenu(roleMunu);
    }

    //给角色删除权限
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteRoleMunu(@PathVariable Long id){
        return roleMenuService.deleteRoleMunu(id);
    }

    //根据角色权限id来查询权限信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{id}")
    public Result getRoleMunuById(@PathVariable Long id){
        return roleMenuService.getRoleMunuById(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getRoleMunuByPage(@PathVariable Integer current, @PathVariable Integer size){
        return roleMenuService.getRoleMunuByPage(current, size);
    }

    //查询所有角色权限信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping
    public Result getAll(){
        return new Result(roleMenuService.list());
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        roleMenuService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        roleMenuService.doUpload(file);
    }
}

