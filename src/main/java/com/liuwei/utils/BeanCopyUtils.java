package com.liuwei.utils;

import com.liuwei.domain.Category;
import com.liuwei.vo.RootCategoryVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    /**
     * 转换一个对象
     * @param source
     * @param target
     * @param <O>
     * @param <V>
     * @return
     */
    public static <O, V> V copyBean(O source, Class<V> target){
        //创建目标对象
        V result = null;
        try{
            result = target.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回结果
        return result;
    }

    /**
     * 转换一个列表
     * @param list
     * @param target
     * @param <O>
     * @param <V>
     * @return
     */
    public static <O, V> List<V> copyBeanList(List<O> list, Class<V> target){
        return list.stream()
                .map(o -> copyBean(o, target))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Category category = new Category();
        category.setParentId(1l);
        category.setName("libai");
        RootCategoryVo rootCategoryVo = copyBean(category, RootCategoryVo.class);
        System.out.println(rootCategoryVo);

    }
}
