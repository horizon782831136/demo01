package com.liuwei.dao;

import com.liuwei.domain.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 订单表(TblOrders)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:58:03
 */
@Mapper
@Repository
public interface OrdersDao extends BaseMapper<Orders>{

    

}

