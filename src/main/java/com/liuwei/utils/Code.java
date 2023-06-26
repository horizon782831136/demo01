package com.liuwei.utils;

import lombok.Data;
import lombok.Getter;

@Getter
public enum Code {
    LOGIN_SUCCESS(1001, "登录成功"),
    LOGIN_FAILED(1000, "认证失败，请重新登录"),
    LOGOUT_SUCCESS(200, "退出成功！"),
    NO_PERMISSION(10010, "无访问权限"),
    PERMISSION(10011, "正常访问"),
    CAPTCHA_ERROR(100, "验证码错误"),
    CAPTCHA_CORRECT(101, "验证码正确"),
    REGISTER_SUCCESS(10001, "注册成功"),
    REGISTER_FAILED(10000, "注册失败"),
    ADD_SUCCESS(11001, "添加成功"),
    ADD_FAILED(11000, "添加失败"),
    UPDATE_SUCCESS(11011, "修改成功"),
    UPDATE_FAILED(11010,  "修改失败"),
    DELETE_SUCCESS(11101, "删除成功"),
    DELETE_FAILED(11100, "删除失败"),
    QUERY_SUCCESS(11111, "查询成功"),
    QUERY_FAILED(11110, "查询失败"),
    BUSINESS_ERROR(1010, "业务逻辑层错误"),
    SYSTEM_ERROR(1100, "系统错误"),

    //订单状态
    ORDER_CANCELED(0, "已取消"),
    ORDER_NONPAYMENT(10, "未付款"),
    ORDER_PAYED(20, "已付款"),
    ORDER_UNSHIPPED(30, "未发货"),
    ORDER_SHIPPED(40, "已发货"),
    ORDER_SUCCESSFULLY(50, "交易成功"),
    ORDER_TRADE_COLOSED(60, "交易关闭");




    private final int key;
    private final String msg;
    Code(int key, String msg){
        this.key = key;
        this.msg = msg;
    }
}
