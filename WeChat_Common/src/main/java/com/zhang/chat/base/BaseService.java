package com.zhang.chat.base;

import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.utils.Constant;

public class BaseService<T> {

    protected BaseFeed<T> feed = new BaseFeed<T>();

    /**
     * 参数不齐
     *
     * @return
     */
    protected BaseFeed<T> Parameter_irregularity() {
        feed.setInfo("参数不齐");
        feed.setData(null);
        feed.setCode(Constant.RESPONSE_ERROR_CODE_100);
        return feed;
    }
}
