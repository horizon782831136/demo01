package com.liuwei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liuwei.dao.ProductDao;
import com.liuwei.domain.Category;
import com.liuwei.domain.Product;
import com.liuwei.listener.ProductListener;
import com.liuwei.listener.RoleMenuListener;
import com.liuwei.service.CartService;
import com.liuwei.service.CategoryService;
import com.liuwei.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuwei.utils.*;
import com.liuwei.vo.AddProductVo;
import com.liuwei.vo.AddRoleMenuVo;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * 商品表(TblProduct)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:58:27
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Resource
    private ProductService productService;
    @Resource
    private CategoryService categoryService;

    @Override
    public Result updateProduct(Product product) {
        product.setUpdateTime(new Date());
        product.setCreateTime(null);
        boolean flag = productService.updateById(product);
        return ResultUtils.update(flag);
    }

    @Override
    public Result addProduct(Product product) {
        product.setCreateTime(new Date());
        product.setUpdateTime(null);
        product.setDelFlag(Default.DEFAULT_DELETE);
        boolean flag = productService.save(product);
        return ResultUtils.add(flag);
    }

    @Override
    public Result getProductById(Long id) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProId, id);
        return new Result(productService.getOne(queryWrapper));
    }

    @Override
    public Result getProductByPage(Integer current, Integer size) {
        IPage<Product> page = new Page<>(current, size);
        return new Result(productService.page(page));
    }

    @Override
    public Result deleteById(Long id) {
        boolean flag = productService.removeById(id);
        return ResultUtils.delete(flag);
    }

    @Override
    public Result getProductByCateId(Long id) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getCateId, id);
        //商品在售
        queryWrapper.eq(Product::getStatus, Default.DEFAULT_STATUS);
        List<Product> collect = productService.list(queryWrapper).parallelStream().map(product -> {
            //获取该产品种类
            Category category = getRootCategory(product);
            //在路径的前面加上相对路径
            product = updateLocation(category, product);
            //返回
            return product;
        }).collect(Collectors.toList());
        return new Result(collect);
    }

    @Override
    public void doDownLoad(HttpServletResponse response) throws IOException {
        ExcelUtils.downLoad(response, AddProductVo.class, BeanCopyUtils.copyBeanList(
                productService.list(), AddProductVo.class
        ));
    }

    @Override
    public void doUpload(MultipartFile file) throws IOException {
        ExcelUtils.upload(file, AddProductVo.class, new ProductListener());
    }

    @Override
    public Result getByCustomerInProductId(Long id) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProId, id);
        Product product = productService.getOne(queryWrapper);
        //查询父种类，再根据该种类查询根种类
        Category category = getRootCategory(product);
        //再根据转换函数完成地址的修改
        product = updateLocation(category, product);
        return new Result(product);
    }

    @Override
    public Result getBySearch(String like) {
        //先根据产品模糊查询
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Product::getName, like).or()
                .like(Product::getSubtitle, like);
        List<Product> list = productService.list(queryWrapper);
        //再根据商品类型搜
        LambdaQueryWrapper<Category> queryWrapperSub = new LambdaQueryWrapper<>();
        queryWrapperSub.like(Category::getName, like);
        //不为根种类
        queryWrapperSub.gt(Category::getParentId, 0);
        List<List<Product>> collect = categoryService.list(queryWrapperSub).parallelStream().map(category -> {
            LambdaQueryWrapper<Product> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Product::getCateId, category.getCateId());
            List<Product> list1 = productService.list(queryWrapper1);
            return list1;
        }).collect(Collectors.toList());

        //再查询根种类
        LambdaQueryWrapper<Category> queryWrapperRoot = new LambdaQueryWrapper<>();
        queryWrapperRoot.eq(Category::getParentId, 0);
        queryWrapperRoot.like(Category::getName, like);
        List<List<Product>> collect1 = categoryService.list(queryWrapperRoot).parallelStream().map(category -> {
            LambdaQueryWrapper<Product> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Product::getCateId, category.getCateId());
            List<Product> list1 = productService.list(queryWrapper1);
            return list1;
        }).collect(Collectors.toList());

        List<Product> returnProduct = Lists.newArrayList();
        list.addAll(list);
        //添加不为根的种类结果
        collect.forEach(products -> list.addAll(products));
        //添加为根的种类结果
        collect1.forEach(products -> list.addAll(products));
        //去掉重复id
        List<Product> result = list.parallelStream().filter(distinctByKey(Product::getProId)).collect(Collectors.toList());
        //更改路径
        result = result.parallelStream().map(product -> {
            Category category = getRootCategory(product);
            product = updateLocation(category, product);
            return product;
        }).collect(Collectors.toList());
        return new Result(result);
    }

    @Override
    public Result getSlideShow() {
        List<Product> list = productService.list();
        //选取四组，每组四个，随机
        List<List<Product>> rst = Lists.newArrayList();
        Random random = new Random();
        int count = 0;
        for (int i = 0; i < 4; i++) {
            List<Product> row = Lists.newArrayList();
            for (int j = 0; j < 4; j++, count++) {
                int n = random.nextInt(list.size());
                Product product = list.get(count);
                //获取根种类
                Category category = getRootCategory(product);
                //更改路径
                product = updateLocation(category, product);
                row.add(product);
            }

            //
            rst.add(row);
        }
        return new Result(rst);
    }

    @Override
    public Result getRecommend() {
        List<Product> list = productService.list();
        //随机获取八个
        List<Product> rst = Lists.newArrayList();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int n = random.nextInt(list.size());
            Product product = list.get(n);
            //获取根种类
            Category category = getRootCategory(product);
            product = updateLocation(category, product);
            rst.add(product);
        }
        return new Result(rst);
    }

    @Override
    public Result getAllByCustomer() {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getStatus, Default.DEFAULT_STATUS);
        List<Product> products = productService.list(queryWrapper).parallelStream().map(product -> {
            Category category = getRootCategory(product);
            product = updateLocation(category, product);
            return product;
        }).collect(Collectors.toList());
        return new Result(products);
    }

    public  Product updateLocation(Category category, Product product) {
        if (category.getCateId() == 1594665156612595713L) {
            product.setSubimages("../../../src/assets/images/men/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158353231873L) {
            product.setSubimages("../../../src/assets/images/women/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158353231874L) {
            product.setSubimages("../../../src/assets/images/slipper/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158353231875L) {
            product.setSubimages("../../../src/assets/images/bag/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158420340737L) {
            product.setSubimages("../../../src/assets/images/jewllery/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158420340738L) {
            product.setSubimages("../../../src/assets/images/boot/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158420340739L) {
            product.setSubimages("../../../src/assets/images/children/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158420340740L) {
            product.setSubimages("../../../src/assets/images/digital/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158487449602L) {
            product.setSubimages("../../../src/assets/images/cleaner/".concat(product.getSubimages()));
        } else if (category.getCateId() == 1594665158487449603L) {
            product.setSubimages("../../../src/assets/images/food/".concat(product.getSubimages()));
        }
        return product;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public  Category getRootCategory(Product product){
        LambdaQueryWrapper<Category> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(Category::getCateId, product.getCateId());
        Category one = categoryService.getOne(queryWrapper1);
        LambdaQueryWrapper<Category> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Category::getCateId, one.getParentId());
        Category category = categoryService.getOne(queryWrapper2);
        return category;
    }
}

