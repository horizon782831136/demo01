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
 * 订单明细表(TblOrderitem)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:57:50
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(20)
public class AddOrderitemVo implements Serializable {
    /**
     * 订单编号
     */
    @ExcelProperty(value = "订单编号", index = 0)
    private Long orderId;
    /**
     * 用户编号
     */
    @ExcelProperty(value = "用户编号", index = 1)
    private Long userId;
    /**
     * 产品编号
     */
    @ExcelProperty(value = "产品编号", index = 2)
    private Long proId;
    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称", index = 3)
    private String proname;
    /**
     * 产品图片地址
     */
    @ExcelProperty(value = "产品图片地址", index = 4)
    private String proimage;
    /**
     * 创建订单时的单价
     */
    @ExcelProperty(value = "创建订单时的单价", index = 5)
    private Double currentUnitPrice;
    /**
     * 产品数量
     */
    @ExcelProperty(value = "产品数量", index = 6)
    private Integer quantity;
    /**
     * 产品总价
     */
    @ExcelProperty(value = "产品总价", index = 7)
    private Double totalprice;

    /**
     * 创建者id
     */
    @ExcelProperty(value = "创建者id", index = 8)
    private Long createrId;
    /**
     * 修改者id
     */
    @ExcelProperty(value = "修改者id", index = 9)
    private Long updaterId;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", index = 10)
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间", index = 11)
    private Date updateTime;

}

