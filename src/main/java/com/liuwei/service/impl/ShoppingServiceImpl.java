package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.ShoppingDao;
import com.liuwei.domain.Shopping;
import com.liuwei.listener.ShoppingListener;
import com.liuwei.service.ShoppingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddShoppingVo;
import com.liuwei.vo.UserAddressVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 收货信息表(Shopping)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 16:04:33
 */
@Service
public class ShoppingServiceImpl extends ServiceImpl<ShoppingDao, Shopping> implements ShoppingService {
    @Resource
    private ShoppingService shoppingService;

    @Override
    public Result addShopping(Shopping shopping) {
        shopping.setDelFlag(Default.DEFAULT_DELETE);
        shopping.setCreateTime(new Date());
        shopping.setUpdateTime(null);
        shopping.setOrderId(Default.DEFAULT_USERID);
        boolean flag = shoppingService.save(shopping);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateShopping(Shopping shopping) {
        shopping.setUpdateTime(new Date());
        shopping.setCreateTime(null);
        boolean flag = shoppingService.updateById(shopping);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getShoppingById(Long id) {
        LambdaQueryWrapper<Shopping> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shopping::getUserId, id);
        List<Shopping> shoppings = shoppingService.list(queryWrapper);
        return new Result(shoppings);
    }

    @Override
    public Result getShoppingByPage(Integer current, Integer size) {
        Page<Shopping> page = new Page<>(current, size);
        shoppingService.page(page);
        return new Result(page);
    }

    @Override
    public Result getAll() {
        List<Shopping> list = shoppingService.list();
        return new Result(list);
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = shoppingService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddShoppingVo.class,
                BeanCopyUtils.copyBeanList(shoppingService.list(), AddShoppingVo.class));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddShoppingVo.class, new ShoppingListener());
    }

    @Override
    public Result getUserAddress(Long id) {
        LambdaQueryWrapper<Shopping> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Shopping::getUserId, id);
        List<UserAddressVo> userAddresses = shoppingService.list(queryWrapper).stream().parallel().sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime())).map((shopping -> {
            UserAddressVo userAddressVo = BeanCopyUtils.copyBean(shopping, UserAddressVo.class);
            return userAddressVo;
        })).collect(Collectors.toList());

        return new Result(userAddresses);
    }
}
