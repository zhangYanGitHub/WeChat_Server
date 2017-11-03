package com.zhang.chat.utils;

public class StrUtil {
	/**
     * 判断这个字符串是否为空
     * @return
     */
    public static boolean isBlank(String str) {
        if (null != str) {
            if (str.trim().length() == 0) {
                return true;
            } else {
                
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 判断这个字符串是否不为空
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
    
    
    
    public static boolean  equals(String token,String taken1){
    	if(isBlank(token) || isBlank(taken1)) return false;
    	
    	if(token.equals(taken1)) return true;
    	return false;
    }
}
