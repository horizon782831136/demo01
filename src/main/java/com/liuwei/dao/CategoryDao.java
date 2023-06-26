package com.liuwei.dao;

import com.liuwei.domain.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * (Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:56:39
 */
@Mapper
@Repository
public interface CategoryDao extends BaseMapper<Category>{

    

}

