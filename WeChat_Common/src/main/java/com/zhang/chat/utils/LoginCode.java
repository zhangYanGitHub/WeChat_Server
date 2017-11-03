package com.zhang.chat.utils;

/**
 *@author create by zhangYan 
 *
 * 2017年9月2日  下午1:25:53
 *
 */
public class LoginCode {

	public static final int   illegality_code = 0x1;
	public static final String   illegality_info = "用户名非法";
	

	public static final int   empty_code = 0x2;
	public static final String   empty_info = "该用户不存在";
	

	public static final int   error_code = 0x3;
	public static final String   error_info = "密码错误";
	
	public static String getInfo(int code){
		String info = null;
		switch(code){
			case illegality_code:
				info = illegality_info;
				break;
			case empty_code:
				info = empty_info;
				break;
			case error_code:
				info = error_info;
				break;
		}
		
		return info;
	}
}

