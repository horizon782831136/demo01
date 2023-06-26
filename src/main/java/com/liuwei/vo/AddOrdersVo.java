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
@ColumnWidth(20)
public class AddOrdersVo implements Serializable {

    /**
     * 用户编号
     */
    @ExcelProperty(value = "用户编号", index = 0)
    private Long userId;
    /**
     * 收货信息编号
     */
    @ExcelProperty(value = "收货信息编号", index = 1)
    private Long shoppingId;
    /**
     * 实付金额
     */
    @ExcelProperty(value = "实付金额", index = 2)
    private Double payment;
    /**
     * 运费
     */
    @ExcelProperty(value = "运费", index = 3)
    private Integer postage;
    /**
     * 订单状态:0-已取，10-未付款， 20-已付款,40-已发货，50-交易成功，60-交易关闭
     */
    @ExcelProperty(value = "订单状态", index = 4)
    private Integer status;

    /**
     * 支付时间
     */
    @ExcelProperty(value = "支付时间", index = 5)
    private Date paymentTime;
    /**
     * 发货时间
     */
    @ExcelProperty(value = "发货时间", index = 6)
    private Date sendTime;
    /**
     * 订单完成时间
     */
    @ExcelProperty(value = "订单完成时间", index = 7)
    private Date endTime;
    /**
     * 交易关闭时间
     */
    @ExcelProperty(value = "交易关闭时间", index = 8)
    private Date closeTime;
    /**
     * 创建者id
     */
    @ExcelProperty(value = "创建者id", index = 9)
    private Long createrId;
    /**
     * 修改者id
     */
    @ExcelProperty(value = "修改者id", index = 10)
    private Long updaterId;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", index = 11)
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间", index = 12)
    private Date updateTime;


}

