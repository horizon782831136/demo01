package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.RoleMenu;
import com.liuwei.service.RoleMenuService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddRoleMenuVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

@Component
public class RoleMenuListener extends AnalysisEventListener<AddRoleMenuVo> {
    @Resource
    private RoleMenuService roleMenuService;

    public static RoleMenuService tempRoleMenuService;
    List<AddRoleMenuVo> addRoleMenus = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempRoleMenuService = this.roleMenuService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleMenuListener.class);

    @Override
    public void invoke(AddRoleMenuVo data, AnalysisContext context) {
        addRoleMenus.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<RoleMenu> roleMenus = BeanCopyUtils.copyBeanList(addRoleMenus, RoleMenu.class);
        roleMenus.stream().parallel().forEach(roleMenu -> {
            tempRoleMenuService.addRoleMenu(roleMenu);
        });
        LOGGER.info(JSON.toJSONString(roleMenus));
    }
}
