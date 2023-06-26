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
 * 支付信息表(TblPayinfo)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:58:15
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Payinfo implements Serializable {
    private static final long serialVersionUID = -52580170740209952L;
    /**
     * 子订单编号
     */
    @TableId
    private Long payId;
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 支付平台
     */
    private Integer payplatform;
    /**
     * 支付流水号
     */
    private Long platformNumber;
    /**
     * 支付状态
     */
    private Integer platformStatus;
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

