package com.lianghongji.controller;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用返回结果
 *
 * @author  liang.hongji
 * @param <T>返回对象的泛型
 *
 */

@Data
public class CommonResult<T> {

	private boolean success = false;
	private int resultCode = -1;
	private String msg = "";
	private List<T> data = new ArrayList<T>();
	
	public static  <T> CommonResult<T> failResult(){
		return new CommonResult<T>();
	}
	
	public static <T> CommonResult<T> successResult(){
		CommonResult<T> successResult = new CommonResult<T>();
		successResult.setResultCode(0);
		successResult.setSuccess(true);
		successResult.setMsg("请求成功");
		return successResult;
	}
}
