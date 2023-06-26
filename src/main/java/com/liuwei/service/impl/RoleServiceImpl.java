package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.RoleDao;
import com.liuwei.domain.Menu;
import com.liuwei.domain.Role;
import com.liuwei.listener.RoleListener;
import com.liuwei.listener.ShoppingListener;
import com.liuwei.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddRoleVo;
import com.liuwei.vo.AddShoppingVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


/**
 * 角色表(TblRole)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:58:38
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Resource
    private RoleService roleService;
    @Override
    public Result addRole(Role role) {
        role.setCreateTime(new Date());
        role.setUpdateTime(null);
        role.setDelFlag(Default.DEFAULT_DELETE);
        boolean flag = roleService.save(role);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateRole(Role role) {
        role.setUpdateTime(new Date());
        role.setCreateTime(null);
        boolean flag = roleService.updateById(role);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getRoleById(Long id) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Role::getRoleId, id);
        return new Result(roleService.getOne(queryWrapper));
    }

    @Override
    public Result getRoleByPage(Integer current, Integer size) {
        IPage<Role> page = new Page<>(current, size);
        roleService.page(page);
        return new Result(page);
    }

    @Override
    public Result getAll() {
        return new Result(roleService.list());
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddRoleVo.class,
                BeanCopyUtils.copyBeanList(roleService.list(), AddRoleVo.class));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddRoleVo.class, new RoleListener());
    }
}
