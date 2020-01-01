package com.lianghongji.Exception;

/**
 * 统一异常定义
 * 
 * @author deng.huaiyu
 *
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode = -1;

	public BusinessException(int errorCode, String msg) {
		super(msg);
		this.errorCode = errorCode;
	}
	
	public BusinessException(CommonErrorCode errorCode){
		this(errorCode.getCode(), errorCode.getMessage());
	}

	public int getErrorCode() {
		return errorCode;
	}
}
