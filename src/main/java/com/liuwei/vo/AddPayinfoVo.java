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
public class AddPayinfoVo implements Serializable {

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
     * 支付平台
     */
    @ExcelProperty(value = "支付平台", index = 2)
    private Integer payplatform;
    /**
     * 支付流水号
     */
    @ExcelProperty(value = "支付流水号", index = 3)
    private Long platformNumber;
    /**
     * 支付状态
     */
    @ExcelProperty(value = "支付状态", index = 4)
    private Integer platformStatus;
    /**
     * 创建者id
     */
    @ExcelProperty(value = "创建者id", index = 5)
    private Long createrId;
    /**
     * 修改者id
     */
    @ExcelProperty(value = "修改者id", index = 6)
    private Long updaterId;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", index = 7)
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间", index = 8)
    private Date updateTime;

}
