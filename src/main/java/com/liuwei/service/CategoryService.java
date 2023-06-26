package com.liuwei.service;

import com.liuwei.domain.Category;
import com.liuwei.utils.Result;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * (Category)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:56:39
 */
@Service
public interface CategoryService extends IService<Category>{


    Result addCategory(Category category);

    Result updateCategory(Category category);

    Result getCategoryById(Long id);

    Result getCategoryByPage(Integer current, Integer size);

    Result deleteById(Long id);

    Result getRootCategory();

    Result getCategoriesByParentId(Long id);

    void doDownLoad(HttpServletResponse response) throws IOException;

    void doUpload(MultipartFile file) throws IOException;

    Result getSubCategories();
}
