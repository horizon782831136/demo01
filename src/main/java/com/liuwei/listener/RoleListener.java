package com.liuwei.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liuwei.domain.Role;
import com.liuwei.domain.RoleMenu;
import com.liuwei.service.RoleMenuService;
import com.liuwei.service.RoleService;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.vo.AddRoleMenuVo;
import com.liuwei.vo.AddRoleVo;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
@Component
public class RoleListener extends AnalysisEventListener<AddRoleVo> {
    @Resource
    private RoleService roleService;

    public static RoleService tempRoleService;
    List<AddRoleVo> addRoleMenus = Lists.newArrayList();
    @PostConstruct
    public void setTempRoleMenuService() {
        tempRoleService = this.roleService;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleListener.class);
    @Override
    public void invoke(AddRoleVo data, AnalysisContext context) {
        addRoleMenus.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        List<Role> roles = BeanCopyUtils.copyBeanList(addRoleMenus, Role.class);
        roles.stream().parallel().forEach(role -> {
            tempRoleService.addRole(role);
        });
        LOGGER.info(JSON.toJSONString(roles));
    }
}
