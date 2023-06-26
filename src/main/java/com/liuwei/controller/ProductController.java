package com.liuwei.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.domain.Product;
import com.liuwei.service.ProductService;
import com.liuwei.utils.Result;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品表(Product)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:15:39
 */
@RestController
@RequestMapping("product")
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping("/status")
    //对商品的状态进行锁定
    public Result updateProductStatus(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping
    //根据商品信息添加商品
    public Result addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @PutMapping
    //选择对要变更的商品信息进行变更
    public Result updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    //根据商品id来查询商品信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{id}")
    public Result getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }


    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{current}/{size}")
    public Result getProductByPage(@PathVariable Integer current, @PathVariable Integer size){
        return productService.getProductByPage(current, size);
    }

    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping
    public Result getAll(){
        return new Result(productService.list());
    }

    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return productService.deleteById(id);
    }
    //无需权限
    @GetMapping("/cate/{id}")
    public Result getProductByCateId(@PathVariable Long id){
       return productService.getProductByCateId(id);
    }

    //用户根据产品id获取产品信息，图片路径处理
    //无需权限
    @GetMapping("/customer/{id}")
    public Result getByCustomerInProductId(@PathVariable Long id){
        return productService.getByCustomerInProductId(id);
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        productService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        productService.doUpload(file);
    }
    //全局搜索商品
    @GetMapping("/search/{like}")
    public Result getBySearch(@PathVariable String like){
        return productService.getBySearch(like);
    }

    //商品轮播图
    @GetMapping("/slidesshow")
    public Result getSlideShow(){
        return productService.getSlideShow();
    }

    //商品推荐
    @GetMapping("/recommend")
    public Result getRecommend(){
        return productService.getRecommend();
    }

    //用户查看所有商品
    @GetMapping("/customer/all")
    public Result getAllByCustomer(){
        return productService.getAllByCustomer();
    }
}

