package com.zhang.chat.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

public class BaseDao {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;
    protected Session session;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
