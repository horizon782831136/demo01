package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.liuwei.domain.Theme;
import com.liuwei.domain.User;
import com.liuwei.service.ThemeService;
import com.liuwei.service.UserService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddThemeVo;
import com.liuwei.vo.AddUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
@Component
public class ThemeListener extends AnalysisEventListener<AddThemeVo> {
    //2.声明要注入的service
    @Resource
    private ThemeService themeService;
    //3.这里声明一个临时的静态service类型和上面一样
    public static ThemeService tempThemeService;
    private List<AddThemeVo> data = Lists.newArrayList();
    //4.使用PostConstruct来实现初始化(核心)
    @PostConstruct
    public void setTempUserService(){
        tempThemeService = this.themeService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ThemeListener.class);
    /**
     * 解析每条数据时都会调用
     */
    @Override
    public void invoke(AddThemeVo addThemeVo, AnalysisContext context) {
        data.add(addThemeVo);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 可以在此处执行业务操作
        //添加到数据库中
        List<Theme> themes = BeanCopyUtils.copyBeanList(data, Theme.class);
        // 本例就打印到控制台即可，表示读取完成
        themes.stream().parallel().forEach(theme -> {
            //后面使用临时的静态service对象
            tempThemeService.addTheme(theme);
        });
        LOGGER.info(JSON.toJSONString(themes));
    }
}
