package com.liuwei.dao;

import com.liuwei.domain.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 商品表(TblProduct)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:58:26
 */
@Mapper
@Repository
public interface ProductDao extends BaseMapper<Product>{

    

}

