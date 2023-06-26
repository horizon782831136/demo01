package com.liuwei.service;

import com.liuwei.domain.Menu;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限表(Menu)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:57:22
 */
@Service
public interface MenuService extends IService<Menu>{


    Result addMenu(Menu menu);

    Result updateMenu(Menu menu);

    Result getAll();

    Result deleteById(Long id);

    Result getMenuByPage(Integer current, Integer size);

    Result getById(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;
}
