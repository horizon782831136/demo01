package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.domain.Category;
import com.liuwei.dao.CategoryDao;
import com.liuwei.listener.CategoryListener;
import com.liuwei.listener.OrderitemListener;
import com.liuwei.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * (Category)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:56:39
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {
    @Resource
    private CategoryService categoryService;

    @Override
    public Result addCategory(Category category) {
        category.setCreateTime(new Date());
        category.setUpdateTime(null);
        category.setDelFlag(Default.DEFAULT_DELETE);
        boolean flag = categoryService.save(category);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateCategory(Category category) {
        category.setCreateTime(null);
        category.setUpdateTime(new Date());
        boolean flag = categoryService.updateById(category);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getCategoryById(Long id) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getCateId, id);
        return new Result(categoryService.getOne(queryWrapper));
    }

    @Override
    public Result getCategoryByPage(Integer current, Integer size) {
        IPage<Category> page = new Page<>(current, size);
        categoryService.page(page);
        return new Result(page);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = categoryService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getRootCategory() {
        //状态可用且parentId为0
       return getCategoriesByParentId(0l);
    }

    @Override
    public Result getCategoriesByParentId(Long id) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getParentId, id);
        queryWrapper.eq(Category::getStatus, Default.DEFAULT_STATUS);
        return new Result(categoryService.list(queryWrapper));
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddCategoryVo.class, BeanCopyUtils.copyBeanList(
                categoryService.list(), AddCategoryVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddCategoryVo.class, new CategoryListener());
    }

    @Override
    public Result getSubCategories() {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getParentId, 0);
        List<OptionsVo> optionsVos = categoryService.list(queryWrapper).parallelStream().map(category -> {
            OptionsVo optionsVo = new OptionsVo();
            optionsVo.setValue(category.getCateId());
            optionsVo.setLabel(category.getName());
            LambdaQueryWrapper<Category> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Category::getParentId, category.getCateId());
            List<OptionsVo> collect = categoryService.list(queryWrapper1).parallelStream().map(category1 -> {
                OptionsVo optionsVo1 = new OptionsVo();
                optionsVo1.setValue(category1.getCateId());
                optionsVo1.setLabel(category1.getName());
                return optionsVo1;
            }).collect(Collectors.toList());
            optionsVo.setChildren(collect);
            return optionsVo;
        }).collect(Collectors.toList());
        return new Result(optionsVos);
    }


}
