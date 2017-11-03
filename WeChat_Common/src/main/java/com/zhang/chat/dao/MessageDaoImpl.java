package com.zhang.chat.dao;


import com.zhang.chat.base.BaseDao;
import com.zhang.chat.dao.interfaces.MessageDao;
import com.zhang.chat.entity.sql.Message;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("messageDao") //进行注入
public class MessageDaoImpl extends BaseDao implements MessageDao {


    @Override
    public List<Message> getMessage(long user_id) {
        List<Message> list = sessionFactory.getCurrentSession().createQuery("select new Message(m.m_ID,m.m_PostMessages,m.m_status,m.m_Time,m.m_MessagesTypeID," +
                "m.m_ToUserID,m.m_FromUserID) " +
                "from Message m where m.m_status = ? and m.m_FromUserID = ?")
                .setParameter(0, 4)
                .setParameter(1, user_id).list();
        updateMessage(list);
        return list;

    }

    @Override
    public void updateMessage(List<Message> list) {

        Session currentSession = sessionFactory.getCurrentSession();
        for (Message message : list) {
            message.setM_status(1);
            currentSession.update(message);
        }
    }

    @Override
    public List<Message> getMessageRecord(long user_id) {
        return null;
    }

    @Override
    public void addMessage(Message message) {
        sessionFactory.getCurrentSession().save(message);
    }


}
