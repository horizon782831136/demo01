package com.liuwei.service;

import com.liuwei.domain.Cart;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 购物车表(Cart)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:53:40
 */
@Service
public interface CartService extends IService<Cart>{


    Result addCart(Cart cart);

    Result updateCart(Cart cart);

    Result getCartById(Long id);

    Result getCartByPage(Integer current, Integer size);

    Result getAll();

    Result deleteById(Long id);

    Result settleAccount(List<Cart> carts, Long id);

    Result getByUserId(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result deleteByBatch(List<Long> list);
}
