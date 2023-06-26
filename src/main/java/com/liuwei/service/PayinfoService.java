package com.liuwei.service;

import com.liuwei.domain.Payinfo;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付信息表(TblPayinfo)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:58:15
 */
@Service
public interface PayinfoService extends IService<Payinfo>{


    Result addPayInfo(Payinfo payinfo);

    Result updatePayInfo(Payinfo payinfo);

    Result deleteById(Long id);

    Result getPayinfoId(Long id);

    Result getPayinfoByPage(Integer current, Integer size);

    Result getByUserId(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;
}
