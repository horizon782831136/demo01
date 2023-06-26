package com.liuwei.controller;

import com.liuwei.domain.Theme;
import com.liuwei.service.ThemeService;
import com.liuwei.utils.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主题表(Theme)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:16:24
 */
@RestController
@RequestMapping("theme")
public class ThemeController {
    @Resource
    private ThemeService themeService;

    //根据主题信息添加权限
    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping
    public Result addTheme (@RequestBody Theme theme){
        return themeService.addTheme(theme);
    }

    //选择对要变更的主题信息进行变更
    @PutMapping
    @PreAuthorize("hasRole('ROLE_admin')")
    public Result updateTheme (@RequestBody Theme theme){
        return themeService.updateTheme(theme);
    }

    //根据主题id来查询主题信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{id}")
    public Result getThemeById(@PathVariable Long id){
        return themeService.getThemeById(id);
    }
    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{current}/{size}")
    public Result getThemeByPage(@PathVariable Integer current, @PathVariable Integer size){
        return themeService.getThemeByPage(current, size);
    }
    //查询所有主题信息
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    public Result getAll(){
        return new Result(themeService.list());
    }
    //根据用户id逻辑删除主题信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return themeService.deleteById(id);
    }

    //获取当前时间内可使用的样式
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/current")
    public Result getCurrentTheme(){
        return themeService.getCurrentTheme();
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        themeService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        themeService.doUpload(file);
    }

}