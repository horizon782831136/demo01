package com.liuwei.dao;

import com.liuwei.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 购物券(Coupon)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:57:06
 */
@Mapper
@Repository
public interface CouponDao extends BaseMapper<Coupon>{

    

}

