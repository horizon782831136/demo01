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
public class AddProductVo implements Serializable {
    /**
     * 商品类型编号
     */
    @ExcelProperty(value = "商品类型编号", index = 0)
    private Long cateId;
    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称", index = 1)
    private String name;
    /**
     * 商品副标题
     */
    @ExcelProperty(value = "商品副标题", index = 2)
    private String subtitle;
    /**
     * 商品图片
     */
    @ExcelProperty(value = "商品图片", index = 3)
    private String subimages;
    /**
     * 商品详情
     */
    @ExcelProperty(value = "商品详情", index = 4)
    private String detail;
    /**
     * 商品价格，保留两位小数
     */
    @ExcelProperty(value = "商品价格", index = 5)
    private Double price;
    /**
     * 商品库存数量
     */
    @ExcelProperty(value = "商品库存数量", index = 6)
    private Integer stock;
    /**
     * 商品价格1-在售，0-下架
     */
    @ExcelProperty(value = "商品价格", index = 7)
    private Integer status;
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
