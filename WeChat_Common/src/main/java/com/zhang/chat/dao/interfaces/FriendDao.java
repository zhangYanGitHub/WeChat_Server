package com.zhang.chat.dao.interfaces;


import com.zhang.chat.entity.response.Friend;
import com.zhang.chat.entity.sql.UserFriend;

import java.util.List;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/24 22:06
 * @Modified By:
 */
public interface FriendDao {

    /**
     * 根据用户id 查询联系人列表
     *
     * @param user_id
     * @return
     */
    List<Friend> getFiendList(long user_id);

    void addFriend(UserFriend friend);

    UserFriend getFriend(long user_id, long friend_id);
}
