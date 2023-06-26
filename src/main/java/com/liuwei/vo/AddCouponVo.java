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
 * 购物券(Coupon)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:57:06
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(20)
public class AddCouponVo implements Serializable {

    /**
     * 购物券名
     */
    @ExcelProperty(value = "购物券名", index = 0)
    private String name;
    /**
     * 购物券id
     */
    @ExcelProperty(value = "购物券id", index = 1)
    private Long userId;
    /**
     * 订单id
     */
    @ExcelProperty(value = "订单id", index = 2)
    private Long ordersId;
    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间", index = 3)
    private Date startTime;
    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间", index = 4)
    private Date endTime;
    /**
     * 价值
     */
    @ExcelProperty(value = "价值", index = 5)
    private Double value;
    /**
     * 使用门槛，如大于20元可使用
     */
    @ExcelProperty(value = "使用门槛", index = 6)
    private Double least;
    /**
     * 指定商品类型(1-2-3:表示-水果-电影票-蔬菜)
     */
    @ExcelProperty(value = "指定商品类型", index = 7)
    private String categories;
    /**
     * 状态(1-可用, 0-禁用, 2-过期)
     */
    @ExcelProperty(value = "状态", index = 8)
    private Integer status;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 9)
    private String remark;


}

