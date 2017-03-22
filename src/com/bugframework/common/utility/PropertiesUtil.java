package com.bugframework.common.utility;


import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

public class PropertiesUtil {
	private static Properties p = new Properties();
 
	static{
		InputStream is = null;
		try {
			is = PropertiesUtil.class.getClassLoader().getResourceAsStream("sysconfig.properties");
			p.load(is);
		} catch (Exception e) {
			 e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static String get(String key) {
		return  p.getProperty(key);
	}

	/**
	 * 获取指定key的值（返回值为数字）
	 * 
	 * @param key
	 *            参数名
	 * @param defaultNum
	 *            默认返回值
	 * @return
	 */
	public static int getValue(String key, int defaultNum) {
		int num = defaultNum;
		try {
			num = Integer.parseInt(get(key));
		} catch (Exception e) {
			num = defaultNum;
		}
		return num;
	}
	
 

}
