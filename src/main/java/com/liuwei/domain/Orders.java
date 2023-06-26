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
 * 订单表(TblOrders)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:58:03
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Orders implements Serializable {
    private static final long serialVersionUID = -92911468024774520L;
    /**
     * 订单编号
     */
    @TableId
    private Long orderId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 收货信息编号
     */
    private Long shoppingId;
    /**
     * 实付金额
     */
    private Double payment;
    /**
     * 运费
     */
    private Integer postage;
    /**
     * 订单状态:0-已取，10-未付款， 20-已付款,40-已发货，50-交易成功，60-交易关闭
     */
    private Integer status;

    /**
     * 支付时间
     */
    private Date paymentTime;
    /**
     * 发货时间
     */
    private Date sendTime;
    /**
     * 订单完成时间
     */
    private Date endTime;
    /**
     * 交易关闭时间
     */
    private Date closeTime;
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

