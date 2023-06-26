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
// 表示列宽
@ColumnWidth(20)
public class AddUserVo implements Serializable {

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名", index = 0)
    private String username;
    /**
     * 用户密码(加密)
     */
    @ExcelProperty(value = "密码", index = 1)
    private String password;

    @ExcelProperty(value = "性别", index = 2)
    private Integer gender;
    /**
     * 用户电话号码
     */
    @ExcelProperty(value = "电话号码", index = 3)
    private String telphone;
    /**
     * 重置密码的问题
     */
    @ExcelProperty(value = "密保问题", index = 4)
    private String question;
    /**
     * 重置密码的答案
     */
    @ExcelProperty(value = "密保答案", index = 5)
    private String answer;
    /**
     * 用户角色
     */
    @ExcelProperty(value = "用户角色", index = 6)
    private Integer role;

}
