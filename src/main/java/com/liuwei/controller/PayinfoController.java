package com.liuwei.controller;

import com.liuwei.domain.Payinfo;
import com.liuwei.service.PayinfoService;
import com.liuwei.utils.Result;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付信息表(Payinfo)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:15:28
 */
@RestController
@RequestMapping("payinfo")
public class PayinfoController {
    /**
     * 服务对象
     */
    @Resource
    private PayinfoService payinfoService;

    //根据支付信息新增支付
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PostMapping
    public Result addPayInfo(@RequestBody Payinfo payinfo){
        return payinfoService.addPayInfo(payinfo);
    }

    //选择对要变更的支付信息进行变更
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @PutMapping
    public Result updatePayInfo(@RequestBody Payinfo payinfo){
        return payinfoService.updatePayInfo(payinfo);
    }

    //根据用户id逻辑删除支付信息
    @PreAuthorize("hasRole('ROLE_admin')")
    @DeleteMapping
    public Result deleteById(@PathVariable Long id){
        return payinfoService.deleteById(id);
    }

    //根据支付id来查询支付信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{id}")
    public Result getPayinfById(@PathVariable Long id){
        return payinfoService.getPayinfoId(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{current}/{size}")
    public Result getPayinfoByPage(@PathVariable Integer current, @PathVariable Integer size){
        return payinfoService.getPayinfoByPage(current, size);
    }
    //查询所有支付信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping
    public Result getAll(){
        return new Result(payinfoService.list());
    }

    //根据用户id查询支付信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/user/{id}")
    public Result getByUserId(@PathVariable Long id){
        return payinfoService.getByUserId(id);
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        payinfoService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        payinfoService.doUpload(file);
    }
}

