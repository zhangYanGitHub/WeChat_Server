package com.zhang.chat.entity.sql;

import javax.persistence.*;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/17 19:06
 * @Modified By:
 */
@Entity
@Table(name = "verfivation_friend_info")
public class Verification {

    /**
     * 主键
     */
    private long m_id;
    /**
     * 添加发起人id
     */
    private long user_friend_id;
    /**
     * 添加接收人id
     */
    private long friend_user_id;

    /**
     * 1 发起添加朋友请求
     * 2 通过朋友验证
     * 3 拒绝添加
     */
    private int m_state;



    public Verification() {
    }

    public Verification(long m_id) {
        this.m_id = m_id;
    }

    public Verification(long m_id, long user_friend_id, long friend_user_id, int m_state
    ) {
        this.m_id = m_id;
        this.user_friend_id = user_friend_id;
        this.friend_user_id = friend_user_id;
        this.m_state = m_state;

    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getM_id() {
        return m_id;
    }

    public void setM_id(long m_id) {
        this.m_id = m_id;
    }

    public long getUser_friend_id() {
        return user_friend_id;
    }

    public void setUser_friend_id(long user_friend_id) {
        this.user_friend_id = user_friend_id;
    }

    public long getFriend_user_id() {
        return friend_user_id;
    }

    public void setFriend_user_id(long friend_user_id) {
        this.friend_user_id = friend_user_id;
    }


    public int getM_state() {
        return m_state;
    }

    /**
     * @param m_state 1 发起添加朋友请求
     *                2 通过朋友验证
     *                3 拒绝添加
     */
    public void setM_state(int m_state) {
        this.m_state = m_state;
    }
}
