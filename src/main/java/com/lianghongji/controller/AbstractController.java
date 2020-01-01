package com.lianghongji.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象控制器
 *
 * @author liang.hongji
 *
 */
@RestController
public abstract class AbstractController {

	/**
	 * 返回错误结果
	 * 
	 * @param errorMsg
	 * @return
	 */
	public <T> CommonResult<T> failResult(String errorMsg) {
		CommonResult<T> failResult = CommonResult.failResult();
		failResult.setMsg(errorMsg);
		return failResult;
	}

	/**
	 * 返回带分页的错误结果
	 * 
	 * @param errorMsg
	 * @return
	 */
	public <T> PageCommResult<T> failPageResult(String errorMsg) {
		PageCommResult<T> failResult = PageCommResult.failResult();
		failResult.setMsg(errorMsg);
		return failResult;
	}

	/**
	 * 返回列表成功请求
	 *
	 * @return
	 */
	public <T> CommonResult<T> successResult(List<T> resultObjects) {
		CommonResult<T> result = CommonResult.successResult();
		result.setData(resultObjects);
		return result;
	}

	/**
	 * 返回带分页的成功请求
	 *
	 * @return
	 */
	public <T> PageCommResult<T> successPageResult(List<T> resultObjects, int currentPage, int currentPageCount, long total) {
		return PageCommResult.successPageResult(resultObjects, currentPage, currentPageCount, total);
	}

	/**
	 * 返回单个对象成功请求
	 * 
	 * @param resultObject
	 * @return
	 */
	public <T> CommonResult<T> successResult(T resultObject) {
		List<T> resultObjects = new ArrayList<T>();
		resultObjects.add(resultObject);
		return successResult(resultObjects);
	}

	/**
	 * 返回单个对象成功请求
	 * 
	 * @param resultObject
	 * @return
	 */
	public <T> PageCommResult<T> successPageResult(T resultObject) {
		List<T> resultObjects = new ArrayList<T>();
		resultObjects.add(resultObject);
		return successPageResult(resultObjects, 0, 0, 0L);
	}

	/**
	 * 成功操作，返回空[]
	 * 
	 * @return
	 */
	public CommonResult<Object> successResultWithEmptyResult() {
		CommonResult<Object> result = new CommonResult<Object>();
		result.setMsg("请求成功");
		result.setResultCode(0);
		result.setSuccess(true);
		return result;
	}

	/**
	 * 成功操作，返回空[]
	 * 
	 * @return
	 */
	public PageCommResult<Object> successPageResultWithEmptyResult() {
		PageCommResult<Object> result = new PageCommResult<Object>();
		result.setMsg("请求成功");
		result.setResultCode(0);
		result.setSuccess(true);
		return result;
	}
}
