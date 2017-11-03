package com.zhang.chat.entity.sql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "token_info_t")
public class Header {
    private long m_id;
    private String token;

    @Id
    public long getM_id() {
        return m_id;
    }

    public void setM_id(long m_id) {
        this.m_id = m_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
