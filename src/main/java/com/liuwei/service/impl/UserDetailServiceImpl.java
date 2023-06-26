package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuwei.domain.*;
import com.liuwei.service.MenuService;
import com.liuwei.service.RoleMenuService;
import com.liuwei.service.RoleService;
import com.liuwei.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//该类的主要目的是用于获取用户，如果连账号都找不到
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    private UserService userService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private MenuService menuService;
    @Resource
    private RoleService roleService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询信息
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getTelphone, username);
        User user = userService.getOne(lambdaQueryWrapper);
        //如果查询不到数据就通过抛出异常来给出信息
        if(Objects.isNull(user))
            throw new RuntimeException("电话号码或密码错误！");
        //TODO 根据用户查询权限信息，添加到LoginUser中
//        LambdaQueryWrapper<RoleMenu> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
//        lambdaQueryWrapper1.eq(RoleMenu::getRoleId, user.getRole());
        LambdaQueryWrapper<Role> queryWrapperRole = new LambdaQueryWrapper<>();
        //获取用户角色
        queryWrapperRole.eq(Role::getRoleId, user.getRole());
        Role role = roleService.getOne(queryWrapperRole);
        //角色列表
        List<String> menus = new ArrayList<>();
        //将角色加到权限表中
        menus.add("ROLE_" + role.getName());
//        roleMenuService.list(lambdaQueryWrapper1).stream()
//                .forEach(roleMenu -> {
//                    LambdaQueryWrapper<Menu> menuLambdaQueryWrapper = new LambdaQueryWrapper<>();
//                    menuLambdaQueryWrapper.eq(Menu::getMenuId, roleMenu.getMenuId());
//                    menus.add(menuService.getOne(menuLambdaQueryWrapper).getName());
//                });

        //根据当前用户的roleId去权限角色关联表中将对应的权限id查询出来，最后再将所有权限封装给list
        return new LoginUser(user, menus);
    }
}
