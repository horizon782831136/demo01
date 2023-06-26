package com.liuwei.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 购物券(Coupon)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:57:06
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Coupon implements Serializable {
    private static final long serialVersionUID = 707531213187108796L;
    /**
     * 购物券id
     */
    @TableId
    private Long id;
    /**
     * 购物券名
     */
    private String name;
    /**
     * 购物券id
     */
    private Long userId;
    /**
     * 订单id
     */
    private Long ordersId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 价值
     */
    private Double value;
    /**
     * 使用门槛，如大于20元可使用
     */
    private Double least;
    /**
     * 指定商品类型(1-2-3:表示-水果-电影票-蔬菜)
     */
    private String categories;
    /**
     * 状态(1-可用, 0-禁用, 2-过期)
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 0-可用， 1-删除
     */
    private Integer delFlag;

}

