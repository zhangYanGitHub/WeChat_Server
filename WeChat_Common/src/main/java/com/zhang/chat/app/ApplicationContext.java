package com.zhang.chat.app;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContext implements ApplicationContextAware {
    private static org.springframework.context.ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {

        this.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static org.springframework.context.ApplicationContext  getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     *
     * @param name
     * @return Object
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
}
