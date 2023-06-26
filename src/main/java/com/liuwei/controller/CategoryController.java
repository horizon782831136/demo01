package com.liuwei.controller;

import com.liuwei.domain.Category;
import com.liuwei.service.CategoryService;
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
 * (Category)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:13:27
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    //根据商品类别信息添加商品类别
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PostMapping
    public Result addCategory (@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    //选择对要变更的商品类别信息进行变更
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PutMapping
    public Result updateCategory (@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

    //根据商品类别id来查询商品类别信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{id}")
    public Result getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{current}/{size}")
    public Result getCategoryByPage(@PathVariable Integer current, @PathVariable Integer size){
        return categoryService.getCategoryByPage(current, size);
    }

    //查询所有商品类别信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping
    public Result getAll(){
        return new Result(categoryService.list());
    }

    //根据用户id逻辑删除商品类别信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
       return categoryService.deleteById(id);
    }


    //获取所有根种类
    //不需要任何权限
    @GetMapping("/root")
    public Result getRootCategory(){
        return categoryService.getRootCategory();
    }

    //根据父节点获取所有孩子
    //不需要任何权限
    @GetMapping("/root/{id}")
    public Result getCategoriesByParentId(@PathVariable Long id){
        return categoryService.getCategoriesByParentId(id);
    }

    //获取所有子节点
    //不需要任何权限
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @GetMapping("/subCategories")
    public Result getSubCategories(){
        return categoryService.getSubCategories();
    }
    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        categoryService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        categoryService.doUpload(file);
    }
}

