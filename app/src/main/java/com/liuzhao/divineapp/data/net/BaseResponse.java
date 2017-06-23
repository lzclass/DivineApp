package com.liuzhao.divineapp.data.net;


import java.io.Serializable;

/**
 * 网络返回基类 支持泛型
 */
public class BaseResponse<T> implements Serializable{

    private int error_code;
    private String reason;
    private T result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
    public boolean isOk() {
        return error_code == 0;
    }
}
