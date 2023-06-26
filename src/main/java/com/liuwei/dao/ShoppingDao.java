package com.liuwei.dao;

import com.liuwei.domain.Shopping;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 收货信息表(Shopping)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 16:04:33
 */
@Mapper
@Repository
public interface ShoppingDao extends BaseMapper<Shopping>{

    

}

