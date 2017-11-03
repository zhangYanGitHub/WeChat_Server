package com.zhang.chat.netty.service;

import com.zhang.chat.dao.interfaces.MessageDao;
import com.zhang.chat.entity.sql.Message;
import com.zhang.chat.netty.pool.ConnPool;
import com.zhang.chat.netty.protocol.MessageHolder;
import com.zhang.chat.netty.protocol.ProtocolHeader;
import com.zhang.chat.netty.serializer.Serializer;
import com.zhang.chat.utils.LogUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.util.concurrent.Future;

/**
 * 个人消息处理
 */


public class PersonMessage extends BaseNettyService {


    private MessageDao messageDao;
    private static volatile PersonMessage instance;

    public static PersonMessage newInstance() {
        if (instance == null) {
            synchronized (PersonMessage.class) {
                if (instance == null) {
                    instance = new PersonMessage();
                    return instance;
                }
            }
        }
        return instance;
    }

    private PersonMessage() {
        messageDao = (MessageDao) getBean("messageDao");


    }

    public void deal(MessageHolder messageHolder) {
        String body = messageHolder.getBody();
        Message message = Serializer.deserialize(body, Message.class);

        //转发消息
        long m_toUserID = message.getM_FromUserID();

        Channel resonpseChanal = ConnPool.query(String.valueOf(m_toUserID));

        if (resonpseChanal == null) {
            offLine(message);
        } else {
            online(resonpseChanal, message);
        }

    }

    /**
     * 转发在线消息
     *
     * @param resonpseChanal
     * @param message
     */
    private void online(Channel resonpseChanal, Message message) {
        //存入数据库
        messageDao.addMessage(message);
        Future future = sendResponse(ProtocolHeader.RESPONSE, Serializer.serialize(message), ProtocolHeader.PERSON_MESSAGE, resonpseChanal);
        LogUtils.error(PersonMessage.class, resonpseChanal.toString());
        future.addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                boolean success = future.isSuccess();
                if (success) {
                    LogUtils.info(PersonMessage.class, "转发成功");
                } else {
                    LogUtils.info(PersonMessage.class, "转发失败");
                }
            }
        });
    }

    /**
     * 保存离线消息
     *
     * @param message
     */
    private void offLine(Message message) {
        message.setM_status(4);
        //存入数据库
        messageDao.addMessage(message);
    }


}
