package com.lianghongji.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串辅助类
 * 
 * @author deng.huaiyu
 *
 */
public class Strings {

	/**
	 * 将驼峰命名变为下画线命名 如： helloWorld -> hello_world
	 * 
	 * @param name
	 * @return
	 */
	public static String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		boolean firstLetter = true;
		if (StringUtils.isNotBlank(name)) {
			for (char chart : name.toCharArray()) {
				if (Character.isUpperCase(chart) && !firstLetter) {
					result.append("_");
				}
				result.append(Character.toLowerCase(chart));
				firstLetter = false;
			}
		}
		return result.toString();
	}
}
