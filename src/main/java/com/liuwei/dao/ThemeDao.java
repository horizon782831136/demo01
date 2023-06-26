package com.liuwei.dao;

import com.liuwei.domain.Theme;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 主题表(Theme)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 16:04:49
 */
@Mapper
@Repository
public interface ThemeDao extends BaseMapper<Theme>{

    

}

