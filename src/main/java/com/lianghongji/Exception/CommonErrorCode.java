package com.lianghongji.Exception;

/**
 * 错误码
 *
 * @author deng.huaiyu
 */
public class CommonErrorCode {
	public static final CommonErrorCode UNKOWN = new CommonErrorCode(100, "系统内部错误");
	public static final CommonErrorCode PRAMERROR = new CommonErrorCode(101, "参数错误");
	
	public CommonErrorCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	private int code;
	private String message;

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
