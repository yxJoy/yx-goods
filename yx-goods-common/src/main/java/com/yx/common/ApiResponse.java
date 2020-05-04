package com.yx.common;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {

    /**
     * 返回结果状态码
     */
    private int code;

    /**
     * 一般存储错误信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    public ApiResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.code == ResponseConstant.SUCCESS;
    }

    public boolean isFailed() {
        return !isSuccess();
    }

    public static ApiResponse ofSuccess() {
        return new ApiResponse(ResponseConstant.SUCCESS, ResponseConstant.SUCCESS_MSG, null);
    }

    public static ApiResponse ofSuccess(Object data) {
        return new ApiResponse(ResponseConstant.SUCCESS, ResponseConstant.SUCCESS_MSG, data);
    }

    public static ApiResponse ofFail(String msg) {
        return new ApiResponse(ResponseConstant.FAILED, msg, null);
    }

}
