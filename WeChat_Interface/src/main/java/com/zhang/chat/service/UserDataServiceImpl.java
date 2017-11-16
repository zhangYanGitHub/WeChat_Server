package com.zhang.chat.service;

import com.zhang.chat.base.BaseService;
import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.dao.interfaces.FriendDao;
import com.zhang.chat.dao.interfaces.MessageDao;
import com.zhang.chat.dao.interfaces.UserDao;
import com.zhang.chat.dao.interfaces.VerificationDao;
import com.zhang.chat.entity.response.Friend;
import com.zhang.chat.entity.response.MainData;
import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.Message;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.UserFriend;
import com.zhang.chat.entity.sql.Verification;
import com.zhang.chat.service.interfaces.UserDataService;
import com.zhang.chat.utils.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

//注入服务
@Service("userDataService")
public class UserDataServiceImpl extends BaseService<MainData> implements UserDataService {

    @Resource
    FriendDao friendDao;
    @Resource
    VerificationDao verificationDao;
    @Resource
    MessageDao messageDao;
    @Resource
    UserDao userDao;


    @Override
    public BaseFeed<MainData> getUserData(User requestUser) {
        long m_id = requestUser.getM_id();
        User friend = new User();

        User user = userDao.get(requestUser);
        List<Friend> fiendList = friendDao.getFiendList(m_id);
        ResList<Verification> list = verificationDao.getList(m_id);
        List<Message> message = messageDao.getMessage(m_id);
        List<Message> messageRecord = messageDao.getMessageRecord(m_id);
        int number = 0;
        ArrayList<MainData.MessageList> messageLists = new ArrayList<MainData.MessageList>();
        for (Message message1 : messageRecord) {
            MainData.MessageList messageList = new MainData.MessageList();
            messageList.setMessage(message1);
//            if (message1.getM_ToUserID() == m_id) {
//                friend.setM_id(message1.getM_FromUserID());
//
//            } else {
//                friend.setM_id(message1.getM_FromUserID());
//            }
//            User user1 = userDao.get(friend);
//
//            messageList.setFriend_id(user1.getM_id());
//            UserFriend friend1 = friendDao.getFriend(m_id, user1.getM_id());
//            messageList.setFriend_nick_name(friend1.getF_friend_type_id());
//            messageList.setImg_face_path(user1.getUser_img_face_path());
            for (Message message2 : message) {
                if (message1 == null || message2 == null) continue;
                if (message1.getM_FromUserID() == message2.getM_FromUserID()
                        && message1.getM_ToUserID() == message2.getM_ToUserID()) {
                    number++;
                }
            }
            messageList.setNumber(number);
            messageLists.add(messageList);
        }
        user.setUser_password("**********");
        MainData mainData = new MainData();
        mainData.setFriends(fiendList);
        mainData.setMessageList(message);
        mainData.setVerifications(list.getList());
        mainData.setLatestMessage(messageLists);
        mainData.setUser(user);

        feed.setData(mainData);
        feed.setCode(Constant.RESPONSE_CODE_200);
        feed.setInfo("获取成功");
        return feed;
    }
}
