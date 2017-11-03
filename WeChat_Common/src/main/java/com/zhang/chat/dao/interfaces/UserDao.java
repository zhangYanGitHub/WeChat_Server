package com.zhang.chat.dao.interfaces;

import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.User;

public interface UserDao {
    /**
     * 登录
     *
     * @param user
     * @return
     */
    User selectByUu_idAndPassword(User user);

    /**
     * 注册
     *
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 根据主键获得User
     *
     * @param user
     * @return
     */
    User get(User user);

    /**
     * 更新
     *
     * @param user
     * @return
     */
    User update(User user);

    BaseFeed<ResList<User>> search(long user_id, String searchKey);
}
