package com.liuwei.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品表(TblProduct)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:58:27
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = -55673827245120270L;
    /**
     * 商品编号
     */
    @TableId
    private Long proId;
    /**
     * 商品类型编号
     */
    private Long cateId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品副标题
     */
    private String subtitle;
    /**
     * 商品图片
     */
    private String subimages;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 商品价格，保留两位小数
     */
    private Double price;
    /**
     * 商品库存数量
     */
    private Integer stock;
    /**
     * 商品价格1-在售，0-下架
     */
    private Integer status;
    /**
     * 创建者id
     */
    private Long createrId;
    /**
     * 修改者id
     */
    private Long updaterId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 0-可用， 1-删除
     */
    private Integer delFlag;

}

