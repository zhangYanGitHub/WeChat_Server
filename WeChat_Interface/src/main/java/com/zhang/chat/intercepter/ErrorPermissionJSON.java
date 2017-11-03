package com.zhang.chat.intercepter;

import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.utils.Constant;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/24 15:14
 * @Modified By: 处理 全局拦截器返回数据
 */
public class ErrorPermissionJSON {

    private BaseFeed<Object> baseFeed;

    public void setBaseFeed(BaseFeed<Object> baseFeed) {
        this.baseFeed = baseFeed;
    }

    public BaseFeed<Object> getBaseFeed() {
        return baseFeed;
    }

    public String noUser_id() throws Exception {
        baseFeed = new BaseFeed<Object>("参数不齐", Constant.RESPONSE_ERROR_CODE_1001, null);
        return "success";
    }

    public String noToken() throws Exception {
        baseFeed = new BaseFeed<Object>("token 过期", Constant.RESPONSE_ERROR_CODE_1000, null);
        return "success";
    }
}
