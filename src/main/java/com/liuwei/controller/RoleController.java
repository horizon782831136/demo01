package com.liuwei.controller;

import com.liuwei.domain.Menu;
import com.liuwei.domain.Role;
import com.liuwei.service.RoleService;
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
 * 角色表(Role)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:15:54
 */
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    //添加角色信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping
    public Result addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    //选择对要变更的角色信息进行变更
    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping
    public Result updateRole(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    //根据角色id来查询角色信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{id}")
    public Result getRoleById(@PathVariable Long id){
        return roleService.getRoleById(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getRoleByPage(@PathVariable Integer current, @PathVariable Integer size){
        return roleService.getRoleByPage(current, size);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping
    public Result getAll(){
        return  roleService.getAll();
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        roleService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        roleService.doUpload(file);
    }
}

