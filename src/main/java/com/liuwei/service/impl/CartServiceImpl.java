package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.CartDao;
import com.liuwei.domain.Cart;
import com.liuwei.domain.Orderitem;
import com.liuwei.domain.Orders;
import com.liuwei.domain.Product;
import com.liuwei.listener.CartListener;
import com.liuwei.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddCartVo;
import com.liuwei.vo.CartProductVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 购物车表(Cart)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:53:40
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartDao, Cart> implements CartService {
    @Resource
    private CartService cartService;
    @Resource
    private OrderitemService orderitemService;
    @Resource
    private PayinfoService payinfoService;
    @Resource
    private ProductService productService;
    @Resource
    private OrdersService ordersService;
    //总和
    double totalPrice = 0;
    @Override
    public Result addCart(Cart cart) {
        cart.setCreateTime(new Date());
        cart.setDelFlag(Default.DEFAULT_DELETE);
        cart.setChecked(Default.DEFAULT_CHECKED);
        cart.setUpdateTime(null);
        boolean flag = cartService.save(cart);
        return ResultUtils.add(flag);
    }

    @Override
    public Result updateCart(Cart cart) {
        cart.setUpdateTime(new Date());
        cart.setCreateTime(null);
        boolean flag = cartService.updateById(cart);
        return ResultUtils.update(flag);
    }

    @Override
    public Result getCartById(Long id) {
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getCartId, id);
        return new Result(cartService.getOne(queryWrapper));
    }

    @Override
    public Result getCartByPage(Integer current, Integer size) {
        IPage<Cart> page = new Page<>(current, size);
        cartService.page(page);
        return new Result(page);
    }

    @Override
    public Result getAll() {
        return new Result(cartService.list());
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = cartService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result settleAccount(List<Cart> carts, Long id) {
        //购物车 -> 订单明细 -> 订单
        //将对象列表转化为流
        List<Cart> newCarts = carts.stream()
                //筛选出已选中的的商品
                .filter(cart -> cart.getChecked() == 1)
                .collect(Collectors.toList());
        //生成订单明细表
        List<Orderitem> orderitems = new ArrayList<>();
        newCarts.forEach((cart) -> {
            //利用购物车中的信,创建订单明细表
            //1.查询商品信息
            LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Product::getProId, cart.getProId());
            Product product = productService.getOne(queryWrapper);
            //将商品信息绑定到订单明细上
            Orderitem orderitem = new Orderitem();
            orderitem.setProimage(product.getSubimages());
            orderitem.setProname(product.getName());
            orderitem.setCreateTime(new Date());
            orderitem.setCreaterId(cart.getUserId());
            orderitem.setQuantity(cart.getQuantity());
            orderitem.setProId(cart.getProId());
            orderitem.setCurrentUnitPrice(product.getPrice());
            orderitem.setUserId(id);
            //求当前总价
            totalPrice += orderitem.getQuantity() * product.getPrice();
            orderitem.setTotalprice(totalPrice);
            //将订单明细写入数据库中
            orderitemService.save(orderitem);
            //将订单明细存入变量中
            orderitems.add(orderitem);
            //将价钱累加
        });

        //生成订单表
        Orders orders = new Orders();
        orders.setDelFlag(Default.DEFAULT_DELETE);
        orders.setCreateTime(new Date());
        //默认未支付
        orders.setStatus(Code.ORDER_NONPAYMENT.getKey());
        //如果carts不为空,从中获取用户id
//        if(Objects.nonNull(carts)){
//            //将用户绑定到订单上
//            orders.setCreaterId(carts.get(0).getUserId());
//            orders.setUserId((carts.get(0).getUserId()));
//        }
        orders.setUserId(id);
        orders.setCreaterId(id);
        //商品总价+邮费
        totalPrice += Default.DEFAULT_POSTAGE;
        orders.setPayment(totalPrice);
        //提交订单表
        boolean flag = ordersService.save(orders);
        //将订单id绑定到订单明细上
        orderitems.forEach(orderitem -> {
            orderitem.setOrderId(orders.getOrderId());
        });

        //删除已勾选的购物车
        List<Long> list = newCarts.parallelStream().map(cart -> cart.getCartId()).collect(Collectors.toList());
        cartService.removeByIds(list);
        return ResultUtils.add(flag);
    }

    @Override
    public Result getByUserId(Long id) {
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUserId, id);
        List<CartProductVo> collect = cartService.list(queryWrapper).stream().parallel().map(cart -> {
            //查询当前商品
            LambdaQueryWrapper<Product> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Product::getProId, cart.getProId());
            //将购物车属性绑定给CartProduct
            Product one = productService.getOne(queryWrapper1);
            CartProductVo cartProductVo = BeanCopyUtils.copyBean(cart, CartProductVo.class);
            //再将商品属性绑定给CartProduct
            BeanUtils.copyProperties(one, cartProductVo);
            return cartProductVo;
        }).sorted((o1, o2) -> o2.getCreateTime().compareTo(o1.getCreateTime())).collect(Collectors.toList());

        return new Result(collect);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddCartVo.class, BeanCopyUtils.copyBeanList(
                cartService.list(), AddCartVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException{
        ExcelUtils.upload(file, AddCartVo.class, new CartListener());
    }

    @Override
    public Result deleteByBatch(List<Long> list) {
        boolean flag = cartService.removeByIds(list);
        return ResultUtils.delete(flag);
    }
}
