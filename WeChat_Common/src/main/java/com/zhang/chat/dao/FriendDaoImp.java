package com.zhang.chat.dao;

import com.zhang.chat.base.BaseDao;
import com.zhang.chat.dao.interfaces.FriendDao;
import com.zhang.chat.entity.response.Friend;
import com.zhang.chat.entity.sql.UserFriend;
import com.zhang.chat.utils.ListUtil;
import org.hibernate.classic.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/24 22:06
 * @Modified By:
 */
@Transactional(rollbackFor = Exception.class)
//出现Exception异常回滚
@Repository("friendDao") //进行注入
public class FriendDaoImp extends BaseDao implements FriendDao {


    public List<Friend> getFiendList(long user_id) {

        Session session = sessionFactory.openSession();
        ArrayList<Friend> friendsList = new ArrayList<Friend>();
        List<Object[]> name = session.createSQLQuery("SELECT t.m_id ,t.user_name,t.user_sex " +
                ",t.user_desc,t.user_phone,t.user_img_face_path, f.f_friend_type_id,f.f_friend_groups_id " +
                ",t.u_NationID,t.U_Province,t.U_City ,f.friend_State from user_friend_t f" +
                " LEFT JOIN  user_info_t t ON  f.f_firend_id=t.m_id" +
                "  where f.f_user_id = ? ")
                .setParameter(0, user_id).list();
        for (Object[] obj : name) {
            Friend friend = new Friend(Long.parseLong(obj[0] + ""), (String) obj[1], (Integer) obj[2], (String) obj[3],
                    (String) obj[4], (String) obj[5], (String) obj[6], Long.parseLong(obj[7] + ""),
                    (String) obj[8], (String) obj[9], (String) obj[10], (Boolean) obj[11]);
            friendsList.add(friend);
        }
        session.close();
        return friendsList;
    }

    public void addFriend(UserFriend friend) {
    }

    @Override
    public UserFriend getFriend(long user_id, long friend_id) {
        Session session = sessionFactory.openSession();
        List<UserFriend> list = session.createQuery("select new UserFriend(u.f_friend_type_id) from UserFriend u where u.f_user_id = ? and u.f_firend_id = ?")
                .setParameter(0, user_id)
                .setParameter(1, friend_id).list();

        if (ListUtil.isEmpty(list)) return null;
        return list.get(0);
    }
}
