package com.liuwei.dao;

import com.liuwei.domain.Orderitem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 订单明细表(TblOrderitem)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:57:50
 */
@Mapper
@Repository
public interface OrderitemDao extends BaseMapper<Orderitem>{

    

}

