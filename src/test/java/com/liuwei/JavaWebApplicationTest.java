package com.liuwei;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liuwei.domain.Category;
import com.liuwei.domain.Coupon;
import com.liuwei.domain.Product;
import com.liuwei.domain.User;
import com.liuwei.service.*;
import com.liuwei.utils.BeanCopyUtils;
import com.liuwei.utils.Default;
import com.liuwei.vo.RootCategoryVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class JavaWebApplicationTest {
    @Resource
    private UserService userService;
    @Resource
    private ThemeService themeService;
    @Resource
    private ShoppingService shoppingService;
    @Resource
    private RoleService roleService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private ProductService productService;
    @Resource
    private PayinfoService payinfoService;
    @Resource
    private OrdersService ordersService;
    @Resource
    private OrderitemService orderitemService;
    @Resource
    private MusicService musicService;
    @Resource
    private MenuService menuService;
    @Resource
    private CouponService couponService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private CartService cartService;

    @Test
    public void userServiceTest(){
        System.out.println(userService.list());
        User user = new User();
        user.setUsername("liuwei");
        user.setTelphone("15607136003");
        user.setPassword("123");
        user.setDelFlag(0);
        userService.save(user);
        System.out.println(userService.list());
    }

    @Test
    public void themeServiceTest(){
        System.out.println(themeService.list());
    }

    @Test
    public void shoppingServiceTest(){
        System.out.println(shoppingService.list());
    }

    @Test
    public void roleServiceTest(){
        System.out.println(roleService.list());
    }

    @Test
    public void roleMenuServiceTest(){
        System.out.println(roleMenuService.list());
    }

    @Test
    public void productServiceTest(){
//        System.out.println(productService.list());
        for (int i = 0; i < 20; i++) {
            Product product = new Product();
            product.setCateId(1594665156612595713l);
            product.setName("苹果" + i + "号");
            product.setPrice(Math.random() * 10);
            product.setSubtitle("水果特卖");
            product.setStock(100);
            product.setStatus(Default.DEFAULT_STATUS);
            productService.addProduct(product);
        }
    }

    @Test
    public void payinfoServiceTest(){
        System.out.println(payinfoService.list());
    }

    @Test
    public void ordersServiceTest(){
        System.out.println(ordersService.list());
    }

    @Test
    public void orderitemServiceTest(){
        System.out.println(orderitemService.list());
    }

    @Test
    public void musicServiceTest(){
        System.out.println(musicService.list());
    }

    @Test
    public void menuServiceTest(){
//        System.out.println(menuService.list());

    }

    @Test
    public void couponServiceTest(){
//        System.out.println(couponService.list());
        for (int i = 0; i < 100; i++) {
            Coupon coupon = new Coupon();
            coupon.setLeast(20.0);
            coupon.setName("水果购物券");
            coupon.setCategories("水果");
            coupon.setRemark("水果专用");
            coupon.setStartTime(new Date());
            coupon.setEndTime(new Date(2023-1-1));
            couponService.addCoupon(coupon);
        }
    }

    @Test
    public void categoryServiceTest(){
        System.out.println(categoryService.list());
        for (long i = 20; i < 40; i++) {
            Category category = new Category();
            category.setParentId(1594665158487449604l);
            category.setName("水果" + i);
            category.setDetail("水果新鲜");
            categoryService.addCategory(category);
        }


    }

    @Test
    public void cartServiceTest(){
        System.out.println(cartService.list());
    }
}
