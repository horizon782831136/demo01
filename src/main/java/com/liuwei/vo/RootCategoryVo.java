package com.liuwei.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.liuwei.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class RootCategoryVo implements Serializable {
    /**
     * 类别编号
     */
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
    /**
     * 创建者id
     */
    private Long createrId;
    /**
     * 修改者id
     */
    private Long updaterId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 0-可用， 1-删除
     */
    private Integer delFlag;
    //子种类
    List<Category> categoryVos;
}
