package com.liuwei.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 购物车表(Cart)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:53:40
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    private static final long serialVersionUID = 898069130207639182L;
    /**
     * 购物车编号
     */
    @TableId
    private Long cartId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 产品编号
     */
    private Long proId;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 是否勾选
     */
    private Integer checked;
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

