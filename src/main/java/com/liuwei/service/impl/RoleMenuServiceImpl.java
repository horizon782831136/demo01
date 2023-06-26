package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.RoleMenuDao;
import com.liuwei.domain.RoleMenu;
import com.liuwei.listener.RoleMenuListener;
import com.liuwei.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddRoleMenuVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * 角色-权限关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 16:04:16
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu> implements RoleMenuService {
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public Result addRoleMenu(RoleMenu roleMunu) {
        roleMunu.setDelFlag(Default.DEFAULT_DELETE);
        roleMunu.setCreateTime(new Date());
        roleMunu.setUpdateTime(null);
        boolean flag = roleMenuService.save(roleMunu);
        return ResultUtils.add(flag);
    }

    @Override
    public Result deleteRoleMunu(Long id) {
        boolean flag = roleMenuService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getRoleMunuById(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getId, id);
        RoleMenu one = roleMenuService.getOne(queryWrapper);
        return new Result(one);
    }

    @Override
    public Result getRoleMunuByPage(Integer current, Integer size) {
        IPage<RoleMenu> page = new Page<>(current, size);
        roleMenuService.page(page);
        return new Result(page);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddRoleMenuVo.class, BeanCopyUtils.copyBeanList(
                roleMenuService.list(), AddRoleMenuVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException {
        ExcelUtils.upload(file, AddRoleMenuVo.class, new RoleMenuListener());
    }
}
