package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Menu;
import com.liuwei.domain.Music;
import com.liuwei.service.MenuService;
import com.liuwei.service.MusicService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddMenuVo;
import com.liuwei.vo.AddMusicVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class MenuListener extends AnalysisEventListener<AddMenuVo> {
    @Resource
    private MenuService menuService;

    public static MenuService tempMenuService;
    List<AddMenuVo> list = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempMenuService = this.menuService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuListener.class);
    @Override
    public void invoke(AddMenuVo data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Menu> menus = BeanCopyUtils.copyBeanList(list, Menu.class);
        menus.stream().parallel().forEach(menu -> {
            tempMenuService.addMenu(menu);
        });
        LOGGER.info(JSON.toJSONString(menus));
    }
}
