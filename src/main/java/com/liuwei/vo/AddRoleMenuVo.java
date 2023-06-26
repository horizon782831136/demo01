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
public class AddRoleMenuVo implements Serializable {
    /**
     * 角色id
     */
    @ExcelProperty(value = "角色ID", index = 0)
    private Long roleId;
    @ExcelProperty(value = "权限ID", index = 1)
    private Long menuId;
    @ExcelProperty(value = "创建者ID", index = 2)
    private Long createrId;
    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间", index = 3)
    private Date createTime;

}
