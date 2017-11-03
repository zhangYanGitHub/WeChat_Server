package com.zhang.chat.dao.interfaces;

import com.zhang.chat.entity.sql.Header;

public interface HeaderDao {
    /**
     * 根据主键 M_id 查询token
     *
     * @param header
     * @return
     */
    Header selectByM_id(Header header);

    /**
     * @param header
     */
    void addHeader(Header header);
}
