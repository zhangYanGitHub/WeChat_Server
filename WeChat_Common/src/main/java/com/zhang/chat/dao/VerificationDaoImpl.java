package com.zhang.chat.dao;

import com.zhang.chat.base.BaseDao;
import com.zhang.chat.dao.interfaces.VerificationDao;
import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.UserFriend;
import com.zhang.chat.entity.sql.Verification;
import com.zhang.chat.utils.ListUtil;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/17 19:36
 * @Modified By:
 */

@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("verificationDao") //进行注入
public class VerificationDaoImpl extends BaseDao implements VerificationDao {
    @Override
    public ResList<Verification> getList(long user_id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select  new Verification(" +
                "v.m_id,v.friend_user_id,v.user_friend_id,v.m_state) from Verification  v where " +
                " v.friend_user_id = ? or v.user_friend_id = ?")
                .setParameter(0, user_id)
                .setParameter(1, user_id);
        List<Verification> list = query.list();

        ResList<Verification> resList = new ResList<Verification>();
        resList.setList(list);
        return resList;
    }

    @Override
    public void addVerification(Verification verification) {
        List<Verification> list = (List<Verification>) sessionFactory.getCurrentSession().createQuery("select new Verification(v.m_id) from Verification v" +
                " where v.user_friend_id = ? and v.friend_user_id = ?").setParameter(0, verification.getUser_friend_id()).setParameter(1, verification.getFriend_user_id());
        if (ListUtil.isEmpty(list)) {
            sessionFactory.getCurrentSession().save(verification);
        } else {
            verification.setM_id(list.get(0).getM_id());
            update(verification);
        }
    }

    @Override
    public void update(Verification deserialize) {
        sessionFactory.getCurrentSession().merge(deserialize);
    }

    @Override
    public void addFriend(UserFriend userFriend) {
        sessionFactory.getCurrentSession().save(userFriend);
    }

    @Override
    public User getUser(long friend_user_id) {
        List<User> list = sessionFactory.getCurrentSession().createQuery("select new User(u.m_Id," +
                "u.user_name,u.user_sex,u.user_desc,u.user_phone" +
                ",u.user_img_face_path,u.u_NationID,u.u_Province,u.u_City) from User u where u.m_Id = ?").setParameter(0, friend_user_id).list();
        if (ListUtil.isEmpty(list)) return null;
        User user = list.get(0);
        return user;
    }
}
