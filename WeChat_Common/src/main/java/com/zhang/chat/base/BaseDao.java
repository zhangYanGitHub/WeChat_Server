package com.zhang.chat.base;

import org.hibernate.SessionFactory;

import javax.annotation.Resource;

public class BaseDao {

    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;
}
