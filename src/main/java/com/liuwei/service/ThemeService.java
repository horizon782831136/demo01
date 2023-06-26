package com.liuwei.service;

import com.liuwei.domain.Theme;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主题表(Theme)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 16:04:49
 */
@Service
public interface ThemeService extends IService<Theme>{


    Result addTheme(Theme theme);

    Result updateTheme(Theme theme);

    Result getThemeById(Long id);

    Result getThemeByPage(Integer current, Integer size);

    Result deleteById(Long id);

    Result getCurrentTheme();

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;
}
