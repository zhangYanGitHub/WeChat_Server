package com.zhang.chat.dao.interfaces;


import com.zhang.chat.entity.sql.Message;

import java.util.List;

public interface MessageDao {

    List<Message> getMessage(long user_id);

    void updateMessage(List<Message> list);


    void addMessage(Message message);
}
