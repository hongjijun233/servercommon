package com.lianghongji.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 生成token工具类
 * 
 * @author deng.huaiyu
 *
 */
public class TokenUtils {
	
	/**
	 * 将字符串加密生成token
	 * 
	 * @param str
	 * @return
	 */
	public static String generateToken(String str){
		return DigestUtils.sha1Hex(str);
	}
}
