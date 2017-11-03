package com.zhang.chat.netty;

import com.zhang.chat.netty.exception.NullParamsException;
import com.zhang.chat.netty.handler.AcceptorHandler;
import com.zhang.chat.netty.handler.ResponseServerHandler;
import com.zhang.chat.netty.mesagecoder.ProtocolDecoder;
import com.zhang.chat.netty.mesagecoder.ProtocolEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.apache.log4j.Logger;

/**
 * The implementation of NettyConfig.
 *
 * @author Yohann.
 */
public class NettyConfigImpl implements NettyConfig {
    private static final Logger logger = Logger.getLogger(NettyConfigImpl.class);

    private final ServerBootstrap bootstrap;
    private EventLoopGroup parentGroup;
    private EventLoopGroup childGroup;
    private Class channelClass;

    public NettyConfigImpl() {
        bootstrap = new ServerBootstrap();
    }


    public void setParentGroup() {
        parentGroup = new NioEventLoopGroup();
    }


    public void setParentGroup(int nThreads) {
        parentGroup = new NioEventLoopGroup(nThreads);
    }


    public void setChildGroup() {
        childGroup = new NioEventLoopGroup();
    }


    public void setChildGroup(int nThreads) {
        childGroup = new NioEventLoopGroup(nThreads);
    }


    public void setChannel(Class channelClass) {
        this.channelClass = channelClass;
    }


    @SuppressWarnings("unchecked")
    public void setHandler() {
        validate();
        bootstrap.group(parentGroup, childGroup);
        bootstrap.channel(channelClass);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("ProtocolDecoder", new ProtocolDecoder());
                pipeline.addLast("ProtocolEncoder", new ProtocolEncoder());
                pipeline.addLast("IdleStateHandler", new IdleStateHandler(6, 0, 0));
                pipeline.addLast("AcceptorHandler", new AcceptorHandler());
                pipeline.addLast("ResponseServerHandler", new ResponseServerHandler());
            }
        }).option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }


    public void bind(int port) {
        bind(port, true);
    }


    public void bind(int port, boolean sync) {
        ChannelFuture future = null;

        try {
            future = bootstrap.bind(port).sync();
            logger.info("服务器启动成功 监听端口(" + port + ")");

            if (sync) {
                future.channel().closeFuture().sync();
            } else {
                future.channel().closeFuture();
            }
            logger.info("服务器关闭");

        } catch (InterruptedException e) {
            logger.warn("Netty绑定异常", e);
        } finally {
            shutdown();
        }
    }

    public void shutdown() {
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }

    public void validate() {
        if (parentGroup == null
                || childGroup == null
                || channelClass == null) {
            throw new NullParamsException("parentGroup == null " +
                    "|| childGroup == null " +
                    "|| channelClass == null");
        }
    }
}
