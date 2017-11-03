package com.zhang.chat.entity.sql;


import javax.persistence.*;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/3 11:29
 * @Modified By:
 */
@Entity
@Table(name ="u_message_info")
public class Message  {
    /**
     * (消息ID)    主键，自增
     */
    private long M_ID;
    /**
     * 消息内容
     */
    private  String M_PostMessages;
    /**
     * 1  发送中
     * 2  发送成功
     * 3  发送失败
     * 4  发送成功 离线未读
     */
    private  int M_status;
    /**
     * (发送时间)    默认值
     */
    private  String M_Time;
    /**
     * (消息类型ID)    外键
     */
    private  int M_MessagesTypeID;
    /**
     * (发送者ID)指向用户表    外键
     */
    private  long M_ToUserID;
    /**
     * (接收者ID)指向用户表    外键
     */
    private  long M_FromUserID;


    public Message() {
    }

    public Message(long m_ID, String m_PostMessages, int m_status, String m_Time, int m_MessagesTypeID, long m_ToUserID, long m_FromUserID) {
        M_ID = m_ID;
        M_PostMessages = m_PostMessages;
        M_status = m_status;
        M_Time = m_Time;
        M_MessagesTypeID = m_MessagesTypeID;
        M_ToUserID = m_ToUserID;
        M_FromUserID = m_FromUserID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getM_ID() {
        return M_ID;
    }

    public void setM_ID(long m_ID) {
        M_ID = m_ID;
    }

    public String getM_PostMessages() {
        return M_PostMessages;
    }

    public void setM_PostMessages(String m_PostMessages) {
        M_PostMessages = m_PostMessages;
    }

    public int getM_status() {
        return M_status;
    }

    public void setM_status(int m_status) {
        M_status = m_status;
    }

    public String getM_Time() {
        return M_Time;
    }

    public void setM_Time(String m_Time) {
        M_Time = m_Time;
    }

    public int getM_MessagesTypeID() {
        return M_MessagesTypeID;
    }

    public void setM_MessagesTypeID(int m_MessagesTypeID) {
        M_MessagesTypeID = m_MessagesTypeID;
    }

    public long getM_ToUserID() {
        return M_ToUserID;
    }

    public void setM_ToUserID(long m_ToUserID) {
        M_ToUserID = m_ToUserID;
    }

    public long getM_FromUserID() {
        return M_FromUserID;
    }

    public void setM_FromUserID(long m_FromUserID) {
        M_FromUserID = m_FromUserID;
    }
}