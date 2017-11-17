package com.zhang.chat.entity.sql;


import javax.persistence.*;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/3 11:29
 * @Modified By:
 */
@Entity
@Table(name = "u_message_info")
public class Message implements Comparable<Message> {
    /**
     * (消息ID)    主键，自增
     */
    private long M_ID;
    /**
     * 消息内容
     */
    private String M_PostMessages;
    /**
     * 1  发送中
     * 2  发送成功
     * 3  发送失败
     * 4  发送成功 离线未读
     */
    private int M_status;
    /**
     * (发送时间)    默认值
     */
    private String M_Time;
    /**
     * (消息类型ID)    外键
     */
    private int M_MessagesTypeID;
    /**
     * (发送者ID)指向用户表    外键
     */
    private long M_ToUserID;
    /**
     * (接收者ID)指向用户表    外键
     */
    private long M_FromUserID;

    private boolean isLatest;

    public Message() {
    }

    public Message(long M_ID, String M_PostMessages, int M_status, String m_Time, int m_MessagesTypeID, long m_ToUserID, long m_FromUserID) {
       this.M_ID = M_ID;
       this.M_PostMessages = M_PostMessages;
       this.M_status = M_status;
       this.M_Time = m_Time;
       this.M_MessagesTypeID = m_MessagesTypeID;
       this.M_ToUserID = m_ToUserID;
       this.M_FromUserID = m_FromUserID;
    }

    public void setLatest(boolean latest) {
        isLatest = latest;
    }


    public boolean isLatest() {
        return isLatest;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "M_ID")
    public long getM_ID() {
        return M_ID;
    }

    public void setM_ID(long m_ID) {
        M_ID = m_ID;
    }

    @Column(name = "M_PostMessages")
    public String getM_PostMessages() {
        return M_PostMessages;
    }

    public void setM_PostMessages(String m_PostMessages) {
        M_PostMessages = m_PostMessages;
    }

    @Column(name = "M_status")
    public int getM_status() {
        return M_status;
    }

    public void setM_status(int m_status) {
        M_status = m_status;
    }

    @Column(name = "M_Time")
    public String getM_Time() {
        return M_Time;
    }

    public void setM_Time(String m_Time) {
        M_Time = m_Time;
    }

    @Column(name = "M_MessagesTypeID")
    public int getM_MessagesTypeID() {
        return M_MessagesTypeID;
    }

    public void setM_MessagesTypeID(int m_MessagesTypeID) {
        M_MessagesTypeID = m_MessagesTypeID;
    }

    @Column(name = "M_ToUserID")
    public long getM_ToUserID() {
        return M_ToUserID;
    }

    public void setM_ToUserID(long m_ToUserID) {
        M_ToUserID = m_ToUserID;
    }

    @Column(name = "M_FromUserID")
    public long getM_FromUserID() {
        return M_FromUserID;
    }

    public void setM_FromUserID(long m_FromUserID) {
        M_FromUserID = m_FromUserID;
    }

    @Override
    public int compareTo(Message o) {
        if (o == null) return -1;
        return (int) (Long.parseLong(this.M_Time) - Long.parseLong(o.getM_Time()));
    }
}
