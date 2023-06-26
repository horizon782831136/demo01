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
 * 收货信息表(Shopping)实体类
 *
 * @author makejava
 * @since 2022-11-06 16:04:33
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Shopping implements Serializable {
    private static final long serialVersionUID = 856996870539181602L;
    /**
     * 收货信息编号
     */
    @TableId
    private Long shoppingId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 订单编号
     */
    private Long orderId;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人手机号
     */
    private String receiverPhone;
    /**
     * 收货人电话
     */
    private String receiverMobile;
    /**
     * 收货人省份
     */
    private String receiverProvince;
    /**
     * 收货人市
     */
    private String receiverCity;
    /**
     * 收货人区/县
     */
    private String receiverDistrict;
    /**
     * 收货人详细地址
     */
    private String receiverAddress;
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

