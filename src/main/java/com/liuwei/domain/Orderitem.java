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
 * 订单明细表(TblOrderitem)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:57:50
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Orderitem implements Serializable {
    private static final long serialVersionUID = -92078615193843705L;
    /**
     * 子订单编号
     */
    @TableId
    private Long id;
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 产品编号
     */
    private Long proId;
    /**
     * 产品名称
     */
    private String proname;
    /**
     * 产品图片地址
     */
    private String proimage;
    /**
     * 创建订单时的单价
     */
    private Double currentUnitPrice;
    /**
     * 产品数量
     */
    private Integer quantity;
    /**
     * 产品总价
     */
    private Double totalprice;

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

}

