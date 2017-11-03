package com.zhang.chat.service.interfaces;

import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.request.RequestUser;
import com.zhang.chat.entity.response.MainData;
import com.zhang.chat.entity.sql.User;

public interface UserService {

    /**
     * 登录
     *
     * @param requestUser
     * @return
     */
    BaseFeed<MainData> login(User requestUser);

    /**
     * 注册
     */
    BaseFeed<User> register(RequestUser requestUser);

    /**
     * 更新用户信息
     */
    BaseFeed<User> update(RequestUser requestUser);
}
