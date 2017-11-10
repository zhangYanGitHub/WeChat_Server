package com.zhang.chat.utils;

/**
 * @Author: ZhangYan
 * @Description:
 * @Date Create In: 2017/9/23 0:50
 * @Modified By:
 */
public class Constant {

    /**
     * 操作成功返回码
     */
    public final static int RESPONSE_CODE_200 = 200;
    /**
     * 参数错误
     */
    public final static int RESPONSE_ERROR_CODE_101 = 101;
    /**
     * 参数不齐
     */
    public final static int RESPONSE_ERROR_CODE_100 = 100;

    public final static int RESPONSE_ERROR_CODE_102 = 102;

    public final static int RESPONSE_ERROR_CODE_103 = 103;

    /**
     * token 过期
     */
    public final static int RESPONSE_ERROR_CODE_1000 = 1000;
    /**
     * User_id == null
     */
    public static final int RESPONSE_ERROR_CODE_1001 = 1001;

    /**
     * 用户头像文件夹
     */
    public final static String USER_IMG_FACE_DIR = "/resources/image/face/";

    /**
     * 用户头像文件名
     */
    public final static String USER_IMG_FACE_FILE_NAME = "USER_FACE_IMG_";
    /**
     * 用户朋友表名
     */
    public final static String USER_FRIEND_TABLE_NAME = "user_friend_t_";

}
