package com.zhang.chat.service.interfaces;

import com.zhang.chat.entity.sql.Header;

public interface HeaderService {

    /**
     * 根据主键 M_id 查询token
     *
     * @param header
     * @return
     */
    Header selectByM_id(Header header);

    void add(Header header);
}
