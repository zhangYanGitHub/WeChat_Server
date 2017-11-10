package com.zhang.chat.entity.response;


import com.zhang.chat.entity.sql.User;
import com.zhang.chat.entity.sql.UserFriend;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/24 22:44
 * @Modified By:
 */
public class Friend {

    private long user_id;
    private String user_name;
    private int user_sex = -1;
    private String user_desc;
    private String user_phone;
    private String user_account;
    private String user_img_face_path;

    /**
     * (国家ID)    外键
     */
    private String u_NationID;

    /**
     * （省份ID）    外键
     */
    private String U_Province;
    /**
     * （城市ID）    外键
     */
    private String U_City;
    /**
     * 备注昵称    Null
     */
    private String f_friend_type_id;
    /**
     * (所属分组ID)    外键
     */
    private long f_friend_groups_id;

    /**
     * 是否 验证通过
     */
    private boolean friend_state = true;

    public void setFriend_state(boolean friend_state) {
        this.friend_state = friend_state;
    }

    public boolean isFriend_state() {
        return friend_state;
    }

    public Friend(long user_id, String user_name, int user_sex, String user_desc, String user_phone,
                  String user_img_face_path, String f_friend_type_id, long f_friend_groups_id
            , String U_NationID, String U_Province, String U_City, boolean friend_state) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_sex = user_sex;
        this.user_desc = user_desc;
        this.user_phone = user_phone;
        this.user_img_face_path = user_img_face_path;
        this.f_friend_type_id = f_friend_type_id;
        this.f_friend_groups_id = f_friend_groups_id;
        this.u_NationID = U_NationID;
        this.U_Province = U_Province;
        this.U_City = U_City;
        this.friend_state = friend_state;
    }

    public Friend(UserFriend user, User friend) {
        user_id = friend.getM_id();
        user_name = friend.getUser_name();
        user_sex = friend.getUser_sex();
        user_desc = friend.getUser_desc();
        user_img_face_path = friend.getUser_img_face_path();
        user_phone = friend.getUser_phone();
        f_friend_type_id = friend.getUser_name();
        f_friend_groups_id = user.getF_friend_groups_id();
        this.u_NationID = friend.getU_NationID();
        this.U_Province = friend.getU_Province();
        this.U_City = friend.getU_City();
        this.friend_state = user.getFriend_state();
    }

    @Override
    public String toString() {
        return "Friend{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_sex=" + user_sex +
                ", user_desc='" + user_desc + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_account='" + user_account + '\'' +
                ", user_img_face_path='" + user_img_face_path + '\'' +
                ", u_NationID='" + u_NationID + '\'' +
                ", U_Province='" + U_Province + '\'' +
                ", U_City='" + U_City + '\'' +
                ", f_friend_type_id='" + f_friend_type_id + '\'' +
                ", f_friend_groups_id=" + f_friend_groups_id +
                '}';
    }

    public String getU_NationID() {
        return u_NationID;
    }

    public void setU_NationID(String u_NationID) {
        this.u_NationID = u_NationID;
    }

    public String getU_Province() {
        return U_Province;
    }

    public void setU_Province(String u_Province) {
        U_Province = u_Province;
    }

    public String getU_City() {
        return U_City;
    }

    public void setU_City(String u_City) {
        U_City = u_City;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(int user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_desc() {
        return user_desc;
    }

    public void setUser_desc(String user_desc) {
        this.user_desc = user_desc;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_img_face_path() {
        return user_img_face_path;
    }

    public void setUser_img_face_path(String user_img_face_path) {
        this.user_img_face_path = user_img_face_path;
    }

    public String getF_friend_type_id() {
        return f_friend_type_id;
    }

    public void setF_friend_type_id(String f_friend_type_id) {
        this.f_friend_type_id = f_friend_type_id;
    }

    public long getF_friend_groups_id() {
        return f_friend_groups_id;
    }

    public void setF_friend_groups_id(long f_friend_groups_id) {
        this.f_friend_groups_id = f_friend_groups_id;
    }


}
