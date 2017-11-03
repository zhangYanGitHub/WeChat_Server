package com.zhang.chat.entity.request;

import com.zhang.chat.entity.sql.User;

public class RequestUser extends User{
    public static final int UPDATE_NAME = 1;
    public static final int UPDATE_ACCOUNT = 2;
    public static final int UPDATE_SEX = 3;
    public static final int UPDATE_IMGFACEPATH = 4;
    public static final int UPDATE_PHONE = 5;
    public static final int UPDATE_PASSWORD = 6;
    public static final int UPDATE_DESC = 7;
    public static final int UPDATE_ADDRESS = 8;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
