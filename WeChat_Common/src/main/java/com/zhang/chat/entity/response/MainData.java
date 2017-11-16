package com.zhang.chat.entity.response;

import com.zhang.chat.entity.sql.Message;
import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.Verification;

import java.util.List;

public class MainData {
    private List<Friend> friends;
    private List<Message> messageList;
    private List<Verification> verifications;
    private List<MessageList> latestMessage;
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Verification> getVerifications() {
        return verifications;
    }

    public void setVerifications(List<Verification> verifications) {
        this.verifications = verifications;
    }

    public List<MessageList> getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(List<MessageList> latestMessage) {
        this.latestMessage = latestMessage;
    }

    public static class MessageList {
        private Message message;
        private int number;
//        private String img_face_path;
//        private long friend_id;
//        private String friend_nick_name;
//
//        public String getFriend_nick_name() {
//            return friend_nick_name;
//        }
//
//        public void setFriend_nick_name(String friend_nick_name) {
//            this.friend_nick_name = friend_nick_name;
//        }
//
//        public String getImg_face_path() {
//            return img_face_path;
//        }
//
//        public void setImg_face_path(String img_face_path) {
//            this.img_face_path = img_face_path;
//        }
//
//        public long getFriend_id() {
//            return friend_id;
//        }
//
//        public void setFriend_id(long friend_id) {
//            this.friend_id = friend_id;
//        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
