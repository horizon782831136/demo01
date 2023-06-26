package com.liuwei.vo;

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
public class CurrentThemeVo implements Serializable {
    /**
     * 主题id
     */
    @TableId
    private Long id;
    /**
     * 主题名
     */
    private String name;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 背景图片
     */
    private String backgroundImage;
    /**
     * 头部样式
     */
    private String headerStyle;
    /**
     * 主体样式
     */
    private String contentStyle;
    /**
     * 页脚样式
     */
    private String footerStyle;

    /**
     * 备注
     */
    private String remark;

}
