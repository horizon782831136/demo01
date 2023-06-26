package com.liuwei.controller;

import com.liuwei.domain.Music;
import com.liuwei.service.MusicService;
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
 * 音乐表(Music)表控制层
 *
 * @author makejava
 * @since 2022-11-08 18:14:34
 */
@RestController
@RequestMapping("music")
public class MusicController {
    /**
     * 服务对象
     */
    @Resource
    private MusicService musicService;

    //根据音乐信息添加音乐
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PostMapping
    public Result addMusic (@RequestBody Music music){
        return musicService.addMusic(music);
    }

    //选择对要变更的音乐信息进行变更
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @PutMapping
    public Result updateMusic (@RequestBody Music music){
        return musicService.updateMusic(music);
    }

    //根据音乐id来查询音乐信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{id}")
    public Result getMusicById(@PathVariable Long id){
        return musicService.getMusicById(id);
    }

    //查询所有分页查询,current为当前页, size为每页的条数
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping("/{current}/{size}")
    public Result getMusicByPage(@PathVariable Integer current, @PathVariable Integer size){
        return musicService.getMusicByPage(current, size);
    }
    //查询所有音乐信息
    @PreAuthorize("hasAnyRole('ROLE_admin', 'ROLE_user')")
    @GetMapping
    public Result getAll(){
        return new Result(musicService.list());
    }


    //客户查询音乐信息
    @GetMapping("/show")
    public Result getByCustomer(){
        return musicService.getByCustomer();
    }
    //根据音乐id逻辑删除权限信息
    @PreAuthorize("hasAnyRole('ROLE_admin')")
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id){
        return musicService.deleteById(id);
    }

    //下载数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        musicService.doDownLoad(response);
    }

    //上传数据
//    @PreAuthorize("hasRole('ROLE_admin')")
    @PostMapping("/upload")
    public void doUpload(MultipartFile file) throws IOException{
        musicService.doUpload(file);
    }
}

