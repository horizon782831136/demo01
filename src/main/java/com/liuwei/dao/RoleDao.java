package com.liuwei.dao;

import com.liuwei.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 角色表(TblRole)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:58:38
 */
@Mapper
@Repository
public interface RoleDao extends BaseMapper<Role>{

    

}

