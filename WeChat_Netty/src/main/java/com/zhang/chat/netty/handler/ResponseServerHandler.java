package com.zhang.chat.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Date;

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
public class ResponseServerHandler extends ChannelHandlerAdapter {


//    /***
//     * 这里我们覆盖了chanelRead()事件处理方法。
//     * 每当从客户端收到新的数据时，
//     * 这个方法会在收到消息时被调用，
//     * 这个例子中，收到的消息的类型是ByteBuf
//     * @param ctx 通道处理的上下文信息
//     * @param msg 接收的消息
//     */
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//
//        ctx.write(msg);
//    }
//
//    @Override
//    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        ctx.flush();
//        super.channelReadComplete(ctx);
//    }
//
//    /**
//     * ctx.write(Object)方法不会使消息写入到通道上
//     * 他被缓冲在了内部，你需要调用ctx.flush()方法来把缓冲区中数据强行输出。
//     * 或者你可以在channelRead方法中用更简洁的cxt.writeAndFlush(msg)以达到同样的目的
//     *
//     * @param ctx
//     * @throws Exception
//     */
//
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//
//        cause.printStackTrace();
//        ctx.close();
//        super.exceptionCaught(ctx, cause);
//    }
    /**
     * A thread-safe Set  Using ChannelGroup, you can categorize Channels into a meaningful group.
     * A closed Channel is automatically removed from the collection,
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {  // (2)
        Channel incoming = ctx.channel();
        System.out.println("---------------handlerAdded");
        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {  // (3)
        Channel incoming = ctx.channel();
        System.out.println("---------------handlerRemoved");
        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 离开\n");

        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object s) throws Exception { // (4)
        System.out.println("---------------channelRead0 have received : " + s + "  ");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception { // (5)
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:---"+incoming.remoteAddress()+"---在线");
        //通知您已经链接上客户端
        String str = "您已经开启与服务端链接"+" "+new Date()+" "+ctx.channel().localAddress();
        ctx.writeAndFlush(str);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception { // (6)
        Channel incoming = ctx.channel();

        System.out.println("SimpleChatClient:---"+incoming.remoteAddress()+"---掉线");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Channel incoming = ctx.channel();
        System.out.println("SimpleChatClient:---"+incoming.remoteAddress()+"---异常");
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }
}
