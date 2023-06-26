package com.liuwei.service;

import com.liuwei.domain.Music;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 音乐表(TblMusic)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:57:36
 */
@Service
public interface MusicService extends IService<Music>{


    Result addMusic(Music music);

    Result updateMusic(Music music);

    Result getMusicById(Long id);

    Result getMusicByPage(Integer current, Integer size);

    Result deleteById(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result getByCustomer();
}
