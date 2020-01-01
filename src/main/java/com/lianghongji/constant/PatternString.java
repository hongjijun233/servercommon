package com.lianghongji.constant;

/**
 * 常用正则表达式
 * 
 * @author deng.huaiyu
 *
 */
public class PatternString {
	//手机号码
	public static final String MOBILE = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";

	//邮箱
	public static final String EMAIL = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
}
