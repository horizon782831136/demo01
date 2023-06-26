package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.liuwei.domain.Shopping;
import com.liuwei.domain.User;
import com.liuwei.service.ShoppingService;
import com.liuwei.service.UserService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddShoppingVo;
import com.liuwei.vo.AddUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
@Component
public class ShoppingListener extends AnalysisEventListener<AddShoppingVo> {
    //2.声明要注入的service
    @Resource
    private ShoppingService shoppingService;
    //3.这里声明一个临时的静态service类型和上面一样
    public static ShoppingService tempShoppingService;
    private List<AddShoppingVo> data = Lists.newArrayList();
    //4.使用PostConstruct来实现初始化(核心)
    @PostConstruct
    public void setTempShoppingService(){
        tempShoppingService = this.shoppingService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingListener.class);
    /**
     * 解析每条数据时都会调用
     */
    @Override
    public void invoke(AddShoppingVo shoppingVo, AnalysisContext context) {
        data.add(shoppingVo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 可以在此处执行业务操作
        //添加到数据库中
        List<Shopping> shoppings = BeanCopyUtils.copyBeanList(data, Shopping.class);
        // 本例就打印到控制台即可，表示读取完成
        shoppings.stream().parallel().forEach(shopping -> {
            //后面使用临时的静态service对象
            tempShoppingService.addShopping(shopping);
        });
        LOGGER.info(JSON.toJSONString(shoppings));
    }
}
