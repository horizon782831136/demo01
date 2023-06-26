package com.liuwei.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerOrderVo implements Serializable {
    @TableId
    private Long orderId;
    /**
     * 用户编号
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
     * 创建时间
     */
    private Date createTime;

    List<CustomerOrderitemVo> product;

}
