package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Product;
import com.liuwei.domain.Role;
import com.liuwei.service.ProductService;
import com.liuwei.service.RoleService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddProductVo;
import com.liuwei.vo.AddRoleVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
@Component
public class ProductListener extends AnalysisEventListener<AddProductVo> {
    @Resource
    private ProductService productService;

    public static ProductService tempProductService;
    List<AddProductVo> addRoleMenus = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempProductService = this.productService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductListener.class);
    @Override
    public void invoke(AddProductVo data, AnalysisContext context) {
        addRoleMenus.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Product> products = BeanCopyUtils.copyBeanList(addRoleMenus, Product.class);
        products.stream().parallel().forEach(product -> {
            tempProductService.addProduct(product);
        });
        LOGGER.info(JSON.toJSONString(products));
    }
}
