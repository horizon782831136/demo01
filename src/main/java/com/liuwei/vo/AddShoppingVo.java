package com.liuwei.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(20)
public class AddShoppingVo implements Serializable {

    /**
     * 用户编号
     */
    @ExcelProperty(value = "用户ID", index = 0)
    private Long userId;
    /**
     * 订单编号
     */
    @ExcelProperty(value = "订单ID", index = 1)
    private Long orderId;
    /**
     * 收货人姓名
     */
    @ExcelProperty(value = "收件人", index = 2)
    private String receiverName;
    /**
     * 收货人手机号
     */
    @ExcelProperty(value = "收件人电话", index = 3)
    private String receiverPhone;
    /**
     * 收货人电话
     */
    @ExcelProperty(value = "收件人手机号", index = 4)
    private String receiverMobile;
    /**
     * 收货人省份
     */
    @ExcelProperty(value = "省份", index = 5)
    private String receiverProvince;
    /**
     * 收货人市
     */
    @ExcelProperty(value = "城市", index = 6)
    private String receiverCity;
    /**
     * 收货人区/县
     */
    @ExcelProperty(value = "县/区", index = 7)
    private String receiverDistrict;
    /**
     * 收货人详细地址
     */
    @ExcelProperty(value = "详细地址", index = 8)
    private String receiverAddress;

}
