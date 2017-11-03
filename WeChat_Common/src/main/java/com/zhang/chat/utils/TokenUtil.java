package com.zhang.chat.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;


public class TokenUtil {

	public static String genRandomToken() {
		int pwd_len = 36;
		// 35是因为数组是从0开始的，26个字母+10个数字
		final int maxNum = 36;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// 生成随机数，取绝对值，防止生成负数，

			i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}
	
	/**accessToken是否合法，合法：true*/
	public static boolean isValit(String accessToken){
		return (!isNull(accessToken) && !accessToken.equals("-1")) ? true : false; 
	}
	/**
	 * 判断字符串是否为空，包括null和""
	 * 
	 * @param str
	 *            待判断的字符。
	 * @return
	 */
	public static boolean isNull(String str) {
		return ((str == null) || (str.trim().length() == 0));
	}
	

}
