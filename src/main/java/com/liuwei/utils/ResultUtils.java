package com.liuwei.utils;

public class ResultUtils {
    //添加
    public static Result add(boolean flag){
        return flag ? new Result(Code.ADD_SUCCESS.getKey(), Code.ADD_SUCCESS.getMsg()) :
                new Result(Code.ADD_FAILED.getKey(), Code.ADD_FAILED.getMsg());
    }

    //修改
    public static Result update(boolean flag){
        return flag ? new Result(Code.UPDATE_SUCCESS.getKey(), Code.UPDATE_SUCCESS.getMsg()) :
                new Result(Code.UPDATE_FAILED.getKey(), Code.UPDATE_FAILED.getMsg());
    }

    //修改
    public static Result delete(boolean flag){
        return flag ? new Result(Code.DELETE_SUCCESS.getKey(), Code.DELETE_SUCCESS.getMsg()) :
                new Result(Code.DELETE_FAILED.getKey(), Code.DELETE_FAILED.getMsg());
    }
}
