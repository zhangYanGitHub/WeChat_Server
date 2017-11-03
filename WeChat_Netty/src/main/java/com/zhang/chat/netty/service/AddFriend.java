package com.zhang.chat.netty.service;

import com.zhang.chat.dao.interfaces.VerificationDao;
import com.zhang.chat.entity.response.Friend;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.UserFriend;
import com.zhang.chat.entity.sql.Verification;
import com.zhang.chat.netty.listener.FutureListener;
import com.zhang.chat.netty.pool.ConnPool;
import com.zhang.chat.netty.protocol.MessageHolder;
import com.zhang.chat.netty.protocol.ProtocolHeader;
import com.zhang.chat.netty.serializer.Serializer;
import com.zhang.chat.utils.LogUtils;
import io.netty.channel.Channel;
import io.netty.util.concurrent.Future;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/10/17 19:55
 * @Modified By:
 */
public class AddFriend extends BaseNettyService {
    private static volatile AddFriend instance;
    private final VerificationDao dao;

    public static AddFriend newInstance() {
        if (instance == null) {
            synchronized (PersonMessage.class) {
                if (instance == null) {
                    instance = new AddFriend();
                    return instance;
                }
            }
        }
        return instance;
    }

    private AddFriend() {
        dao = (VerificationDao) getBean("verificationDao");
    }

    @Override
    public void deal(MessageHolder messageHolder) {

        String body = messageHolder.getBody();
        Verification deserialize = Serializer.deserialize(body, Verification.class);
        Channel query = ConnPool.query(String.valueOf(deserialize.getFriend_user_id()));
        Channel query1 = ConnPool.query(String.valueOf(deserialize.getUser_friend_id()));
        if (query == null || query1 == null) {
            offline(messageHolder);
        } else {
            online(messageHolder);
        }

    }

    private void offline(MessageHolder messageHolder) {
        String body = messageHolder.getBody();
        Verification deserialize = Serializer.deserialize(body, Verification.class);
        switch (deserialize.getM_state()) {  // 1 发起添加朋友请求  2 通过朋友验证 3 拒绝添加
            case 1:
                dao.addVerification(deserialize);
                break;
            case 2:
                dao.update(deserialize);
                addFirend(deserialize);
                break;
            case 3:
                dao.update(deserialize);
                break;
        }
    }

    private void online(MessageHolder messageHolder) {

        String body = messageHolder.getBody();
        final Verification deserialize = Serializer.deserialize(body, Verification.class);

        switch (deserialize.getM_state()) {  //1 发起添加朋友请求  2 通过朋友验证 3 拒绝添加
            case 3:
                dao.update(deserialize);
                Future future = sendResponse(ProtocolHeader.RESPONSE, body, ProtocolHeader.ADD_FRIEND,
                        messageHolder.getChannel());
                future.addListener(new FutureListener() {
                    @Override
                    public void success() {
                        LogUtils.info(AddFriend.class, "拒绝添加 转发成功");
                    }

                    @Override
                    public void error() {
                        LogUtils.info(AddFriend.class, "拒绝添加 转发失败");
                    }
                });
                addFirend(deserialize);
                break;
            case 2:
                dao.update(deserialize);
                User user = dao.getUser(deserialize.getFriend_user_id());
                Channel query = ConnPool.query(String.valueOf(deserialize.getUser_friend_id()));
                Future future2 = sendResponse(ProtocolHeader.RESPONSE, body, ProtocolHeader.ADD_FRIEND,
                        query);
                future2.addListener(new FutureListener() {
                    @Override
                    public void success() {
                        LogUtils.info(AddFriend.class, "通过朋友验证 转发成功");
                        addFirend(deserialize);

                    }

                    @Override
                    public void error() {
                        LogUtils.info(AddFriend.class, "通过朋友验证 转发失败");
                    }
                });
                break;
            case 1:
                dao.addVerification(deserialize);
                Channel query1 = ConnPool.query(String.valueOf(deserialize.getFriend_user_id()));
                Future future1 = sendResponse(ProtocolHeader.RESPONSE, body, ProtocolHeader.ADD_FRIEND,
                        query1);
                future1.addListener(new FutureListener() {
                    @Override
                    public void success() {
                        LogUtils.info(AddFriend.class, "发起添加朋友请求 转发成功");
                    }

                    @Override
                    public void error() {
                        LogUtils.info(AddFriend.class, "发起添加朋友请求 转发失败");
                    }
                });
                break;
        }
    }

    private void addFirend(Verification deserialize) {
        //添加接收人
        User user = dao.getUser(deserialize.getFriend_user_id());
        //添加发起人
        User friend = dao.getUser(deserialize.getUser_friend_id());

        UserFriend userFriend = new UserFriend(user, friend);
        UserFriend userFriend1 = new UserFriend(friend, user);
        if (deserialize.getM_state() == 2) {
            userFriend.setFriend_state(false);
            userFriend1.setFriend_state(false);
        }
        dao.addFriend(userFriend);
        dao.addFriend(userFriend1);

        Friend friend1 = new Friend(userFriend, friend);
        Friend friend2 = new Friend(userFriend1, user);

        Channel channel = ConnPool.query(String.valueOf(user.getM_Id()));
        Channel friendchannel = ConnPool.query(String.valueOf(friend.getM_Id()));

        //给发起发送朋友消息
        if (channel != null) {
            sendResponse(ProtocolHeader.RESPONSE, Serializer.serialize(friend1), ProtocolHeader.Add_New_Friend_Info,
                    channel);
        }
        //给接收人发送朋友消息
        if (friendchannel != null) {
            sendResponse(ProtocolHeader.RESPONSE, Serializer.serialize(friend2), ProtocolHeader.Add_New_Friend_Info,
                    friendchannel);
        }
    }
}
