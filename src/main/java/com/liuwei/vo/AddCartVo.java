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
 * 购物车表(Cart)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:53:40
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(20)
public class AddCartVo implements Serializable {

    /**
     * 用户编号
     */
    @ExcelProperty(value = "用户编号", index = 0)
    private Long userId;
    /**
     * 产品编号
     */
    @ExcelProperty(value = "产品编号", index = 1)
    private Long proId;
    /**
     * 商品数量
     */
    @ExcelProperty(value = "商品数量", index = 2)
    private Integer quantity;
    /**
     * 是否勾选
     */
    @ExcelProperty(value = "是否勾选", index = 3)
    private Integer checked;
    /**
     * 创建者id
     */
    @ExcelProperty(value = "创建者id", index = 4)
    private Long createrId;
    /**
     * 修改者id
     */
    @ExcelProperty(value = "修改者id", index = 5)
    private Long updaterId;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", index = 6)
    private Date createTime;
    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间", index = 7)
    private Date updateTime;


}

