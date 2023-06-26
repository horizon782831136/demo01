package com.liuwei.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserAddressVo implements Serializable {
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

}
