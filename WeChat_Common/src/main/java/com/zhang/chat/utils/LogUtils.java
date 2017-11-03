package com.zhang.chat.utils;

import org.apache.log4j.Logger;

/**
 *@author create by zhangYan 
 *
 * 2017年9月15日  下午2:27:12
 *
 */
public class LogUtils {
	 protected static boolean DEBUG = false;
	 
	 public static void info(Class clazz ,String message){
		 if(DEBUG){
			 return;
		 }
		 Logger logger = Logger.getLogger(clazz);
		 logger.info(message);
	 }
	 public static void error(Class clazz ,String message){
		 if(DEBUG){
			 return;
		 }
		 Logger logger = Logger.getLogger(clazz);
		 logger.error(message);
	 }
	 public static void debug(Class clazz ,String message){
		 if(DEBUG){
			 return;
		 }
		 Logger logger = Logger.getLogger(clazz);
		 logger.debug(message);
	 }
	 public static void warn(Class clazz ,String message){
		 if(DEBUG){
			 return;
		 }
		 Logger logger = Logger.getLogger(clazz);
		 logger.warn(message);
	 }
}

