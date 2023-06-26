package com.liuwei.controller;

import com.liuwei.domain.Menu;
import com.liuwei.service.MenuService;
import com.liuwei.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限表(Menu)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:13:52
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    //根据权限信息添加权限
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PostMapping
    public Result addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    //选择对要变更的权限信息进行变更
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PutMapping
    public Result updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    //根据权限id来查询权限信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){
        return menuService.getById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/{current}/{size}")
    public Result getMenuByPage(@PathVariable Integer current, @PathVariable Integer size){
        return menuService.getMenuByPage(current, size);
    }
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping
    public Result getAll(){
        return menuService.getAll();
    }

    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return menuService.deleteById(id);
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        menuService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        menuService.doUpload(file);
    }

}

