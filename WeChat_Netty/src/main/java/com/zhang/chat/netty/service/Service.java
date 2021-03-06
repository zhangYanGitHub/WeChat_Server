package com.zhang.chat.netty.service;

import com.zhang.chat.netty.protocol.MessageHolder;
import com.zhang.chat.netty.queue.TaskQueue;
import com.zhang.chat.utils.LogUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The implementation of Service.
 *
 * @author Yohann.
 */
public class Service {

    public static AtomicBoolean shutdown = new AtomicBoolean(false);

    // 任务队列
    private BlockingQueue<MessageHolder> taskQueue;
    // 阻塞式地从taskQueue取MessageHolder
    private ExecutorService takeExecutor;
    // 执行业务的线程池
    private ExecutorService taskExecutor;

    public void initAndStart() {
        init();
        start();
    }

    private void init() {
        takeExecutor = Executors.newSingleThreadExecutor();
        taskExecutor = Executors.newFixedThreadPool(10);
        taskQueue = TaskQueue.getQueue();
        LogUtils.info(this.getClass(),"初始化服务完成");
    }

    private void start() {
        takeExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (!shutdown.get()) {
                    try {
                        MessageHolder messageHolder = taskQueue.take();
                        LogUtils.info(this.getClass(),"TaskQueue取出任务: taskQueue=" + taskQueue.size());
                        startTask(messageHolder);
                    } catch (InterruptedException e) {
                        LogUtils.info(Service.class,"receiveQueue take");
                    }
                }
            }

            private void startTask(final MessageHolder messageHolder) {
                taskExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        LogUtils.info(Service.class,"开始执行取出的任务 messageHolder=" + messageHolder);

                        Dispatcher.dispatch(messageHolder);
                    }
                });
            }
        });
        LogUtils.info(Service.class,"启动服务完成");
    }

    public void shutDown() {
        shutdown = new AtomicBoolean(true);
        takeExecutor.shutdown();
    }
}
