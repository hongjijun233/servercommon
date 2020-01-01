package com.lianghongji.util;

import com.alibaba.fastjson.JSON;

/**
 * JSON转换辅助类
 * 
 * @author deng.huaiyu
 *
 */
public class JsonUtils {
	
	/**
	 * 对象转换为字符串
	 * 
	 * @param object
	 * @return
	 */
	public  static String toJson(Object object){
		return JSON.toJSONString(object);
	}
	
	/**
	 * 字符串转为对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String jsonStr, Class<T> clazz){
		return JSON.parseObject(jsonStr, clazz);
	}
}
