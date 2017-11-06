package com.zhang.chat.entity.sql;

import com.zhang.chat.entity.request.RequestUser;

import javax.persistence.*;

@Entity
@Table(name = "user_info_t")
public class User {
    private long M_Id;
    private long uu_id;
    private String user_name;
    private int user_sex = -1;
    private String user_real_name;
    private String user_password;
    private String user_desc;
    private String user_phone;
    private String user_account;
    private String user_email;
    /**
     * 头像路径
     */
    private String user_img_face_path;
    /**
     * 用户注册日期
     */
    private long user_register_date;
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
     * 好友策略ID    外键
     */
    private int U_FriendshipPolicy;
    /**
     * (用户状态ID)    外键
     */
    private int U_UserState;

    public User() {
    }

    public User(long m_Id) {
        M_Id = m_Id;
    }

    public User(long user_id, String user_name, int user_sex, String user_desc, String user_phone,
                String user_img_face_path, String u_NationID, String U_Province, String U_City) {
        this.M_Id = user_id;
        this.user_name = user_name;
        this.user_sex = user_sex;
        this.user_desc = user_desc;
        this.user_phone = user_phone;
        this.user_img_face_path = user_img_face_path;
        this.u_NationID = u_NationID;
        this.U_Province = U_Province;
        this.U_City = U_City;
    }

    public User(int user_sex,String user_phone,String user_real_name,String user_password, String user_name) {
        this.user_sex = user_sex;
        this.user_phone = user_phone;
        this.user_real_name = user_real_name;
        this.user_password = user_password;
        this.user_name = user_name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getM_Id() {
        return M_Id;
    }

    public void setM_Id(long m_Id) {
        M_Id = m_Id;
    }

    public long getUu_id() {
        return uu_id;
    }

    public void setUu_id(long uu_id) {
        this.uu_id = uu_id;
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

    public String getUser_real_name() {
        return user_real_name;
    }

    public void setUser_real_name(String user_real_name) {
        this.user_real_name = user_real_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
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

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_img_face_path() {
        return user_img_face_path;
    }

    public void setUser_img_face_path(String user_img_face_path) {
        this.user_img_face_path = user_img_face_path;
    }

    public long getUser_register_date() {
        return user_register_date;
    }

    public void setUser_register_date(long user_register_date) {
        this.user_register_date = user_register_date;
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

    public int getU_FriendshipPolicy() {
        return U_FriendshipPolicy;
    }

    public void setU_FriendshipPolicy(int u_FriendshipPolicy) {
        U_FriendshipPolicy = u_FriendshipPolicy;
    }

    public int getU_UserState() {
        return U_UserState;
    }

    public void setU_UserState(int u_UserState) {
        U_UserState = u_UserState;
    }


    public static final int UPDATE_NAME = 1;
    public static final int UPDATE_ACCOUNT = 2;
    public static final int UPDATE_SEX = 3;
    public static final int UPDATE_IMGFACEPATH = 4;
    public static final int UPDATE_PHONE = 5;
    public static final int UPDATE_PASSWORD = 6;
    public static final int UPDATE_DESC = 7;
    public static final int UPDATE_ADDRESS = 8;

    private int type;

    @Transient
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "User{" +
                "M_Id=" + M_Id +
                ", uu_id=" + uu_id +
                ", user_name='" + user_name + '\'' +
                ", user_sex=" + user_sex +
                ", user_real_name='" + user_real_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_desc='" + user_desc + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_account='" + user_account + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_img_face_path='" + user_img_face_path + '\'' +
                ", user_register_date=" + user_register_date +
                ", u_NationID='" + u_NationID + '\'' +
                ", U_Province='" + U_Province + '\'' +
                ", U_City='" + U_City + '\'' +
                ", U_FriendshipPolicy=" + U_FriendshipPolicy +
                ", U_UserState=" + U_UserState +
                ", type=" + type +
                '}';
    }
}
