package com.liuwei.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SubCategoryVo implements Serializable {
    /**
     * 类别编号
     */
    @TableId
    private Long cateId;
    /**
     * 父类别编号，为0表示根节点
     */
    private Long parentId;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 类别状态：1-可用；0-已弃用
     */
    private Integer status;
    /**
     * 类别排序
     */
    private Integer sortorder;
    /**
     * 商品详情
     */
    private String detail;

}
