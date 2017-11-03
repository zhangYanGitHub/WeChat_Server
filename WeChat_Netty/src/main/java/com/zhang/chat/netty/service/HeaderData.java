package com.zhang.chat.netty.service;

import com.zhang.chat.dao.interfaces.MessageDao;
import com.zhang.chat.entity.sql.Header;
import com.zhang.chat.netty.pool.ConnPool;
import com.zhang.chat.netty.protocol.MessageHolder;
import com.zhang.chat.netty.serializer.Serializer;
import com.zhang.chat.utils.LogUtils;
import io.netty.channel.Channel;

public class HeaderData extends BaseNettyService {
    private static volatile HeaderData instance;

    public static HeaderData newInstance() {
        if (instance == null) {
            synchronized (PersonMessage.class) {
                if (instance == null) {
                    instance = new HeaderData();
                    return instance;
                }
            }
        }
        return instance;
    }

    public void deal(MessageHolder messageHolder) {
        Channel channel = messageHolder.getChannel();
        Header deserialize = Serializer.deserialize(messageHolder.getBody(), Header.class);
        ConnPool.add(deserialize, messageHolder.getChannel());
        MessageDao messageDao = (MessageDao) getBean("messageDao");
        LogUtils.info(this.getClass(), "" + messageDao);
//        Future future = sendResponse(ProtocolHeader.RESPONSE, Serializer.serialize(list),
//                ProtocolHeader.PERSONAL_MESSAGE_LIST, channel);
//
//        future.addListener(new FutureListener() {
//            @Override
//            public void success() {
//                LogUtils.info(HeaderData.class, "离线消息发送成功");
//            }
//
//            @Override
//            public void error() {
//                LogUtils.info(HeaderData.class, "离线消息发送失败");
//            }
//        });
    }


}
