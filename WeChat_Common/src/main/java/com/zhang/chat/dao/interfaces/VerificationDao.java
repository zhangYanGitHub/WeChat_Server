package com.zhang.chat.dao.interfaces;


import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.UserFriend;
import com.zhang.chat.entity.sql.Verification;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/17 19:34
 * @Modified By:
 */
public interface VerificationDao {

    /**
     *
     * @param user_id  接收人ID
     * @return
     */
    ResList<Verification> getList(long user_id);

    void addVerification(Verification deserialize);

    void update(Verification deserialize);

    void addFriend(UserFriend userFriend);

    User getUser(long friend_user_id);

}
