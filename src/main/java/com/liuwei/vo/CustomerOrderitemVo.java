package com.liuwei.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerOrderitemVo implements Serializable {
    /**
     * 子订单编号
     */
    @TableId
    private Long id;
    /**
     * 订单编号
     */
//    private Long orderId;
//    /**
//     * 用户编号
//     */
//    private Long userId;
//    /**
//     * 产品编号
//     */
//    private Long proId;
    /**
     * 产品名称
     */
    private String proname;
    /**
     * 产品图片地址
     */
    private String proimage;
    /**
     * 创建订单时的单价
     */
    private Double currentUnitPrice;
    /**
     * 产品数量
     */
    private Integer quantity;
    /**
     * 产品总价
     */
    private Double totalprice;

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



}
