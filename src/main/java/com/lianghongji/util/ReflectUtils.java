package com.lianghongji.util;

import com.lianghongji.Exception.BusinessRuntimeException;
import com.lianghongji.Exception.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 处理放射相关的辅助类
 * 
 * @author deng.huaiyu
 *
 */
public class ReflectUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ReflectUtils.class);
	
	/**
	 * 创建对象
	 * 
	 * @param clazz
	 * @return
	 */
	public static  <T> T newInstance(Class<T> clazz){
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			logger.error(" newInstance error ", e);
			throw new BusinessRuntimeException(CommonErrorCode.UNKOWN);
		}
	}
}
