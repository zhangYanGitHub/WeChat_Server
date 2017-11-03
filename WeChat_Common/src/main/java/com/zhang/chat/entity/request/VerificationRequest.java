package com.zhang.chat.entity.request;


/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/18 23:03
 * @Modified By:
 */
public class VerificationRequest {

    private long user_id;
    private String search_key;
    private int type;

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setSearch_key(String search_key) {
        this.search_key = search_key;
    }

    public String getSearch_key() {
        return search_key;
    }
}
