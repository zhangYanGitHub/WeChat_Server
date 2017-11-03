package com.zhang.chat.netty.service;

import com.zhang.chat.app.ApplicationContext;
import com.zhang.chat.netty.protocol.MessageHolder;
import com.zhang.chat.netty.protocol.ProtocolHeader;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;


public abstract class BaseNettyService {

    public abstract void deal(MessageHolder messageHolder);

    protected Object getBean(String bean){
       return ApplicationContext.getBean(bean);
    }
    protected Future sendResponse(byte status, String body, byte type, Channel channel) {
        MessageHolder messageHolder = new MessageHolder();
        messageHolder.setSign(ProtocolHeader.RESPONSE);
        messageHolder.setType(type);
        messageHolder.setStatus(status);
        messageHolder.setBody(body);
        return channel.writeAndFlush(messageHolder);
    }
}
