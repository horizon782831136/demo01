package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.domain.Menu;
import com.liuwei.dao.MenuDao;
import com.liuwei.listener.MenuListener;
import com.liuwei.listener.OrderitemListener;
import com.liuwei.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddMenuVo;
import com.liuwei.vo.AddOrderitemVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * 权限表(Menu)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:57:22
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {
    @Resource
    private MenuService menuService;

    @Override
    public Result addMenu(Menu menu) {
        menu.setDelFlag(Default.DEFAULT_DELETE);
        menu.setCreateTime(new Date());
        menu.setUpdateTime(null);
        boolean flag = menuService.save(menu);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateMenu(Menu menu) {
        menu.setUpdateTime(new Date());
        menu.setCreateTime(null);
        boolean flag = menuService.updateById(menu);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getAll() {
        List<Menu> list = menuService.list();
        return new Result(list);
    }

    @Override
    public Result deleteById(@PathVariable Long id) {
        boolean flag = menuService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getMenuByPage(@PathVariable Integer current, @PathVariable Integer size) {
        IPage<Menu> page = new Page<>();
        menuService.page(page);
        return new Result(page);
    }

    @Override
    public Result getById(Long id) {
        LambdaQueryWrapper<Menu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Menu::getMenuId, id);
        return new Result(menuService.getOne(queryWrapper));
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddMenuVo.class, BeanCopyUtils.copyBeanList(
                menuService.list(), AddMenuVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddMenuVo.class, new MenuListener());
    }
}
