package com.zhang.chat.entity.sql;

import javax.persistence.*;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/24 20:29
 * @Modified By:
 */
@Entity
@Table(name = "user_friend_t")
public class UserFriend {
    /**
     * 主键ID    主键
     */
    private long f_id;
    /**
     * 朋友的ID    外键
     */
    private long f_firend_id;
    /**
     * 自己的ID    外键
     */
    private long f_user_id;
    /**
     * 备注昵称    Null
     */
    private String f_friend_type_id;
    /**
     * (所属分组ID)    外键
     */
    private long f_friend_groups_id;

    /**
     * 是否 验证通过
     */
    private boolean friend_state = true;


    public UserFriend() {
    }

    public UserFriend(User user, User friend) {
        this.f_firend_id = friend.getM_id();
        f_user_id = user.getM_id();
        f_friend_type_id = friend.getUser_name();
        f_friend_groups_id = 1;
    }

    public UserFriend(long f_firend_id) {
        this.f_firend_id = f_firend_id;
    }

    public void setFriend_state(boolean friend_state) {
        this.friend_state = friend_state;
    }

    public boolean getFriend_state() {
        return friend_state;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getF_id() {
        return f_id;
    }

    public void setF_id(long f_id) {
        this.f_id = f_id;
    }


    public long getF_firend_id() {
        return f_firend_id;
    }

    public void setF_firend_id(long f_firend_id) {
        this.f_firend_id = f_firend_id;
    }

    public long getF_user_id() {
        return f_user_id;
    }

    public void setF_user_id(long f_user_id) {
        this.f_user_id = f_user_id;
    }

    public String getF_friend_type_id() {
        return f_friend_type_id;
    }

    public void setF_friend_type_id(String f_friend_type_id) {
        this.f_friend_type_id = f_friend_type_id;
    }

    public long getF_friend_groups_id() {
        return f_friend_groups_id;
    }

    public void setF_friend_groups_id(long f_friend_groups_id) {
        this.f_friend_groups_id = f_friend_groups_id;
    }
}
