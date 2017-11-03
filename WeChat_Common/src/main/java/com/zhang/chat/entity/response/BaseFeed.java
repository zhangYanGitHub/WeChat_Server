package com.zhang.chat.entity.response;

public class BaseFeed<T> {
    private String info;
    private int code;
    private T data;

    public BaseFeed(String info, int code, T t) {

        this.info = info;
        this.code = code;
        this.data = t;
    }

    public BaseFeed() {

    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
