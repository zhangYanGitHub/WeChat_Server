package com.zhang.chat.dao;

import com.zhang.chat.base.BaseDao;
import com.zhang.chat.dao.interfaces.HeaderDao;
import com.zhang.chat.entity.sql.Header;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("headerDao") //进行注入
public class HeaderDaoImpl extends BaseDao implements HeaderDao {


    @Override
    public Header selectByM_id(Header header) {
        Header header1 = (Header) sessionFactory.getCurrentSession().get(Header.class, header.getM_id());
        return header1;
    }

    @Override
    public void addHeader(Header header) {
         sessionFactory.getCurrentSession().save(header);
    }

    @Override
    public void update(Header header) {
         sessionFactory.getCurrentSession().merge(header);
    }
}
