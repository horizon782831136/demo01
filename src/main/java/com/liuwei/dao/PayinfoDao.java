package com.liuwei.dao;

import com.liuwei.domain.Payinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * 支付信息表(TblPayinfo)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:58:15
 */
@Mapper
@Repository
public interface PayinfoDao extends BaseMapper<Payinfo>{

    

}

