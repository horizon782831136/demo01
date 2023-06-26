package com.liuwei.service;

import com.liuwei.domain.Orderitem;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 订单明细表(TblOrderitem)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:57:50
 */
@Service
public interface OrderitemService extends IService<Orderitem>{
    Result addOrderitem (@RequestBody Orderitem orderitem);
    Result updateOrderitem (@RequestBody Orderitem orderitem);
    Result getOrderitemById(@PathVariable Long id);
    Result getOrderitemByPage(@PathVariable Integer current, @PathVariable Integer size);
    Result deleteById(@PathVariable Long id);

    Result getByUserId(Long id);

    Result getByProId(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result getByCustomerId(Long id);
}
