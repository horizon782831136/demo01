package com.liuwei.dao;

import com.liuwei.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 权限表(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:57:22
 */
@Mapper
@Repository
public interface MenuDao extends BaseMapper<Menu>{

    

}

