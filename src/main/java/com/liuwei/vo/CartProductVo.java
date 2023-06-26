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
public class CartProductVo implements Serializable {
    /**
     * 购物车编号
     */
    @TableId
    private Long cartId;
    /**
     * 用户编号
     */
    private Long userId;
    /**
     * 产品编号
     */
    private Long proId;
    /**
     * 商品数量
     */
    private Integer quantity;
    /**
     * 是否勾选
     */
    private Integer checked;

    /**
     * 商品类型编号
     */
    private Long cateId;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品副标题
     */
    private String subtitle;
    /**
     * 商品图片
     */
    private String subimages;
    /**
     * 商品详情
     */
    private String detail;
    /**
     * 商品价格，保留两位小数
     */
    private Double price;
    /**
     * 商品库存数量
     */
    private Integer stock;
    /**
     * 商品价格1-在售，0-下架
     */
    private Integer status;

    private Date createTime;

}
