package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Category;
import com.liuwei.domain.Music;
import com.liuwei.service.CategoryService;
import com.liuwei.service.MusicService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddCategoryVo;
import com.liuwei.vo.AddMusicVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class CategoryListener extends AnalysisEventListener<AddCategoryVo> {
    @Resource
    private CategoryService categoryService;

    public static CategoryService tempCategoryService;
    List<AddCategoryVo> list = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempCategoryService = this.categoryService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);
    @Override
    public void invoke(AddCategoryVo data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Category> categories = BeanCopyUtils.copyBeanList(list, Category.class);
        categories.stream().parallel().forEach(category -> {
            tempCategoryService.addCategory(category);
        });
        LOGGER.info(JSON.toJSONString(categories));
    }
}
