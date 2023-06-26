package com.liuwei.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.ThemeDao;
import com.liuwei.domain.Theme;
import com.liuwei.filter.FreezeAndFilter;
import com.liuwei.listener.ThemeListener;
import com.liuwei.listener.UserListener;
import com.liuwei.service.ThemeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddThemeVo;
import com.liuwei.vo.AddUserVo;
import com.liuwei.vo.CurrentThemeVo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;


/**
 * 主题表(Theme)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 16:04:49
 */
@Service
public class ThemeServiceImpl extends ServiceImpl<ThemeDao, Theme> implements ThemeService {
    @Resource
    private ThemeService themeService;

    @Override
    public Result addTheme(Theme theme) {
        theme.setDelFlag(Default.DEFAULT_DELETE);
        theme.setStatus(Default.DEFAULT_STATUS);
        boolean flag = themeService.save(theme);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateTheme(Theme theme) {
        boolean flag = themeService.updateById(theme);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getThemeById(Long id) {
        LambdaQueryWrapper<Theme> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Theme::getId, id);
        return new Result(themeService.getOne(queryWrapper));
    }

    @Override
    public Result getThemeByPage(Integer current, Integer size) {
        IPage<Theme> page = new Page<>(current, size);
        themeService.page(page);
        return new Result(page);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = themeService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getCurrentTheme() {
        Date current = new Date();
        LambdaQueryWrapper<Theme> queryWrapper = new LambdaQueryWrapper<>();
        //以钱买你的字段名为中心，查询的字段值小于或等于输入的值
        queryWrapper.le(Theme::getStartTime, current);
        queryWrapper.gt(Theme::getEndTime, current);
        List<CurrentThemeVo> list = BeanCopyUtils.copyBeanList(themeService.list(queryWrapper), CurrentThemeVo.class);
        return new Result(list);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddThemeVo.class,
                BeanCopyUtils.copyBeanList(themeService.list(), AddThemeVo.class));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddThemeVo.class, new ThemeListener());
    }
}
