package com.zhang.chat.netty.listener;
/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/2 19:14
 * @Modified By:
 */

import com.zhang.chat.netty.NettyConfig;
import com.zhang.chat.netty.NettyConfigImpl;
import com.zhang.chat.netty.service.Service;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class NettyListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private NettyConfig config;
    private Thread thread;
    private Service service;

    // Public constructor is required by servlet spec
    public NettyListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed).
         You can initialize servlet context related data here.
      */

        System.err.println("nettyListener Startup!");
        thread = new Thread() {
            @Override
            public void run() {
                try {
                    service = new Service();
                    service.initAndStart();
                    config = new NettyConfigImpl();
                    config.setParentGroup(1);
                    config.setChildGroup();
                    config.setChannel(NioServerSocketChannel.class);
                    config.setHandler();
                    config.bind(9000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        System.err.println("nettyListener end!");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context
         (the Web application) is undeployed or
         Application Server shuts down.
      */
        service.shutDown();
        config.shutdown();

    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
      /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
      /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
