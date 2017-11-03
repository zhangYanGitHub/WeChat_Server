package com.zhang.chat.dao;

import com.zhang.chat.base.BaseDao;
import com.zhang.chat.dao.interfaces.UserDao;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.UserFriend;
import com.zhang.chat.utils.Constant;
import com.zhang.chat.utils.ListUtil;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("userDao") //进行注入
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User selectByUu_idAndPassword(User user) {
        List<User> list = sessionFactory.getCurrentSession()
                .createQuery(" from com.zhang.chat.entity.sql.User user where user.uu_id = ? ")
                .setParameter(0, user.getUu_id()).list();
        if (ListUtil.isEmpty(list)) return null;
        User user1 = list.get(0);
        return (User) sessionFactory.getCurrentSession().get(User.class, user1.getM_Id());

    }

    @Override
    public User register(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public User get(User user) {
        return (User) sessionFactory.getCurrentSession().get(User.class, user.getM_Id());

    }

    @Override
    public User update(User user) {
        return (User) sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public BaseFeed<ResList<User>> search(long user_id, String searchKey) {
        List<UserFriend> list1 = sessionFactory.getCurrentSession().createQuery("select new UserFriend(uf.f_firend_id) " +
                "from UserFriend uf where uf.f_user_id = ?")
                .setParameter(0, user_id).list();
        List<User> list = sessionFactory.getCurrentSession().createQuery("select new User(u.m_Id," +
                "u.user_name,u.user_sex,u.user_desc,u.user_phone" +
                "                ,u.user_img_face_path,u.u_NationID,u.u_Province,u.u_City) from User u where u.user_name like ? " +
                "or u.user_real_name like ? or u.user_phone like ?")
                .setParameter(0, String.valueOf("%" + searchKey + "%"))
                .setParameter(1, String.valueOf("%" + searchKey + "%"))
                .setParameter(2, String.valueOf("%" + searchKey + "%")).list();
        for (User user : list) {
            user.setUser_real_name("");
            user.setUser_password("");
            for (UserFriend userFriend:list1){
                if(user.getM_Id() == userFriend.getF_firend_id()){
                    list.remove(user);
                }
            }
        }
        ResList<User> userResList = new ResList<User>();
        userResList.setList(list);
        BaseFeed<ResList<User>> ok = new BaseFeed<ResList<User>>("OK", Constant.RESPONSE_CODE_200, userResList);
        return ok;
    }
}
