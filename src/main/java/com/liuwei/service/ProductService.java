package com.liuwei.service;

import com.liuwei.domain.Product;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 商品表(TblProduct)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:58:27
 */
@Service
public interface ProductService extends IService<Product>{


    Result updateProduct(Product product);

    Result addProduct(Product product);

    Result getProductById(Long id);

    Result getProductByPage(Integer current, Integer size);

    Result deleteById(Long id);

    Result getProductByCateId(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result getByCustomerInProductId(Long id);

    Result getBySearch(String like);

    Result getSlideShow();

    Result getRecommend();

    Result getAllByCustomer();
}
