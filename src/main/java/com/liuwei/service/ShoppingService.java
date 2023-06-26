package com.liuwei.service;
import com.liuwei.domain.Shopping;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 收货信息表(Shopping)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 16:04:33
 */
@Service
public interface ShoppingService extends IService<Shopping>{


    Result addShopping(Shopping shopping);

    Result updateShopping(Shopping shopping);

    Result getShoppingById(Long id);

    Result getShoppingByPage(Integer current, Integer size);

    Result getAll();

    Result deleteById(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result getUserAddress(Long id);
}
