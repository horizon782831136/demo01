package com.liuwei.dao;

import com.liuwei.domain.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 角色-权限关联表(RoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 16:04:16
 */
@Mapper
@Repository
public interface RoleMenuDao extends BaseMapper<RoleMenu>{

    

}

