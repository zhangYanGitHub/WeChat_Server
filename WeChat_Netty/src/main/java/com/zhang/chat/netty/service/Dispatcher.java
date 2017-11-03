package com.zhang.chat.netty.service;

import com.zhang.chat.netty.protocol.MessageHolder;
import com.zhang.chat.netty.protocol.ProtocolHeader;
import com.zhang.chat.utils.LogUtils;
import io.netty.channel.Channel;
import io.netty.util.ReferenceCountUtil;

/**
 * 业务分发器.
 *
 * @author Yohann.
 */
public class Dispatcher {

    public static void dispatch(MessageHolder messageHolder) {

        if (messageHolder.getSign() != ProtocolHeader.REQUEST) {
            // 请求错误
            response(messageHolder.getChannel(), messageHolder.getSign());
            return;
        }

        LogUtils.info(Dispatcher.class, messageHolder.toString() + "");
        LogUtils.info(Dispatcher.class, messageHolder.toString() + "");
        switch (messageHolder.getType()) {
            case ProtocolHeader.PERSON_MESSAGE:
                PersonMessage.newInstance().deal(messageHolder);
                break;
            case ProtocolHeader.USER_MESSAGE:  //初始化
                HeaderData.newInstance().deal(messageHolder);
                break;
            case ProtocolHeader.ADD_FRIEND:  //添加朋友
                AddFriend.newInstance().deal(messageHolder);
                break;
            // 请求错误
            default:
                response(messageHolder.getChannel(), messageHolder.getSign());
                break;
        }

        // 释放buffer
        ReferenceCountUtil.release(messageHolder);
    }

    /**
     * 请求错误响应
     *
     * @param channel
     * @param sign
     */
    private static void response(Channel channel, byte sign) {
        MessageHolder messageHolder = new MessageHolder();
        messageHolder.setSign(ProtocolHeader.RESPONSE);
        messageHolder.setType(sign);
        messageHolder.setStatus(ProtocolHeader.REQUEST_ERROR);
        messageHolder.setBody("");
        channel.writeAndFlush(messageHolder);
    }
}
