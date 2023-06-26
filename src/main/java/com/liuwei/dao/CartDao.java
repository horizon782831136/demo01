package com.liuwei.dao;

import com.liuwei.domain.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 购物车表(Cart)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:53:40
 */
@Mapper
@Repository
public interface CartDao extends BaseMapper<Cart>{

    

}

