package com.zhang.chat.entity.sql;

import javax.persistence.*;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/3 11:37
 * @Modified By:
 */

@Entity
@Table(name = "u_message_type")
public class MessageType {

    /**
     * MT_ID    Int    (类型ID)    主键
     * MT_Name    Varchar（20）    类型名称
     */
    private int MT_ID;
    private String MT_Name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMT_ID() {
        return MT_ID;
    }

    public void setMT_ID(int MT_ID) {
        this.MT_ID = MT_ID;
    }

    public String getMT_Name() {
        return MT_Name;
    }

    public void setMT_Name(String MT_Name) {
        this.MT_Name = MT_Name;
    }
}
