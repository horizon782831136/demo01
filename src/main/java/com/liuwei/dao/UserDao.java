package com.liuwei.dao;

import com.liuwei.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-05 21:51:55
 */
@Mapper
@Repository
public interface UserDao extends BaseMapper<User>{

}

