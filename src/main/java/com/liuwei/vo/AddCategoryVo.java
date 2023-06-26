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
 * (Category)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:56:39
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(20)
public class AddCategoryVo implements Serializable {

    /**
     * 父类别编号，为0表示根节点
     */
    @ExcelProperty(value = "父类别编号", index = 0)
    private Long parentId;
    /**
     * 类别名称
     */
    @ExcelProperty(value = "类别名称", index = 1)
    private String name;
    /**
     * 类别状态：1-可用；0-已弃用
     */
    @ExcelProperty(value = "类别状态", index = 2)
    private Integer status;
    /**
     * 类别排序
     */
    @ExcelProperty(value = "类别排序", index = 3)
    private Integer sortorder;
    /**
     * 类别详情
     */
    @ExcelProperty(value = "类别详情", index = 4)
    private String detail;
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

