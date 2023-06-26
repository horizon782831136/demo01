package com.liuwei.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 音乐表(TblMusic)实体类
 *
 * @author makejava
 * @since 2022-11-06 15:57:36
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(20)
public class AddMusicVo implements Serializable {

    /**
     * 音乐名
     */
    @ExcelProperty(value = "音乐名", index = 0)
    private String name;
    /**
     * 音乐路径
     */
    @ExcelProperty(value = "音乐路径", index = 1)
    private String musicLoc;
    @ExcelProperty(value = "状态", index = 2)
    private Integer status;
    /**
     * 备注
     */
    @ExcelProperty(value = "备注", index = 3)
    private String remark;


}

