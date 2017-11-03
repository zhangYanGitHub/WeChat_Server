package com.zhang.chat.service;

import com.zhang.chat.entity.response.BaseFeed;
import com.zhang.chat.base.BaseService;
import com.zhang.chat.dao.interfaces.FriendDao;
import com.zhang.chat.dao.interfaces.MessageDao;
import com.zhang.chat.dao.interfaces.UserDao;
import com.zhang.chat.dao.interfaces.VerificationDao;
import com.zhang.chat.entity.request.RequestUser;
import com.zhang.chat.entity.response.Friend;
import com.zhang.chat.entity.response.MainData;
import com.zhang.chat.entity.response.ResList;
import com.zhang.chat.entity.sql.Message;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.Verification;
import com.zhang.chat.service.interfaces.UserDataService;
import com.zhang.chat.utils.Constant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public BaseFeed<MainData> getUserData(RequestUser requestUser) {
        long m_id = requestUser.getM_Id();
        User user = userDao.get(requestUser);
        List<Friend> fiendList = friendDao.getFiendList(m_id);
        ResList<Verification> list = verificationDao.getList(m_id);
        List<Message> message = messageDao.getMessage(m_id);

        MainData mainData = new MainData();
        mainData.setFriends(fiendList);
        mainData.setMessageList(message);
        mainData.setVerifications(list.getList());
        mainData.setUser(user);

        feed.setData(mainData);
        feed.setCode(Constant.RESPONSE_CODE_200);
        feed.setInfo("获取成功");
        return feed;
    }
}
