package com.lianghongji.util;

import com.lianghongji.Exception.BusinessRuntimeException;
import com.lianghongji.Exception.CommonErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * bean对象的复制
 *
 * @author  liang.hongji
 */
public class BeanUtils {

	private static final Logger logger = LoggerFactory.getLogger(BeanUtils.class);

	/**
	 * 复制单个对象
	 */
	public static <S, D> D copyProperties(S source, D destination) {
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(destination, source);
			return destination;
		} catch (Exception e) {
			logger.error(" copy bean error ", e);
			throw new BusinessRuntimeException(CommonErrorCode.UNKOWN);
		}
	}

	/**
	 * 将src list中的数据复制到 dest list中
	 *
	 */
	public static <S, D> List<D> copyListProperties(List<S> source, List<D> destination, Class<D> clazz){
		for(S src: source){
			D des = ReflectUtils.newInstance(clazz);
			copyProperties(src, des);
			destination.add(des);
		}
		return destination;
	}
}
