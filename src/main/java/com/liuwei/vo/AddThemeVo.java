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
public class AddThemeVo implements Serializable {
    /**
     * 主题名
     */
    @ExcelProperty(value = "主题名", index = 0)
    private String name;
    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间", index = 1)
    private Date startTime;
    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间", index = 2)
    private Date endTime;
    /**
     * 背景图片
     */
    @ExcelProperty(value = "背景图片地址", index = 3)
    private String backgroundImage;
    /**
     * 头部样式
     */
    @ExcelProperty(value = "头样式", index = 4)
    private String headerStyle;
    /**
     * 主体样式
     */
    @ExcelProperty(value = "主体样式", index = 5)
    private String contentStyle;
    /**
     * 页脚样式
     */
    @ExcelProperty(value = "页脚样式", index = 6)
    private String footerStyle;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 7)
    private String remark;

}
