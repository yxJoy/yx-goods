package com.yx.common;

import java.io.Serializable;

public class ApiRequest<T> implements Serializable {

    /**
     * 请求数据
     */
    private T data;

    public ApiRequest() {}

    public ApiRequest(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
