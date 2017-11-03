package com.zhang.chat.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Author: ZhangYan
 * @Description: 服务端处理通道.这里只是打印一下请求的内容，并不对请求进行任何的响应 DiscardServerHandler 继承自 ChannelHandlerAdapter，
 * 这个类实现了ChannelHandler接口，
 * ChannelHandler提供了许多事件处理的接口方法，
 * 然后你可以覆盖这些方法。
 * 现在仅仅只需要继承ChannelHandlerAdapter类而不是你自己去实现接口方法。
 * @Date Create In: 2017/10/2 19:23
 * @Modified By:
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {


    /***
     * 这里我们覆盖了chanelRead()事件处理方法。
     * 每当从客户端收到新的数据时，
     * 这个方法会在收到消息时被调用，
     * 这个例子中，收到的消息的类型是ByteBuf
     * @param ctx 通道处理的上下文信息
     * @param msg 接收的消息
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            ByteBuf in = (ByteBuf) msg;
        } finally {

        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
}
