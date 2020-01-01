package com.lianghongji.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 带分页的通用结果
 * 
 * @author liang.hongji
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageCommResult<T> extends CommonResult<T> {
	private Page page = new Page();

	public static <T> PageCommResult<T> failResult() {
		return new PageCommResult<T>();
	}

	public static <T> PageCommResult<T> successResult() {
		PageCommResult<T> successResult = new PageCommResult<T>();
		successResult.setResultCode(0);
		successResult.setSuccess(true);
		return successResult;
	}

	/**
	 * 返回列表成功请求
	 *
	 * @return
	 */
	public static <T> PageCommResult<T> successPageResult(List<T> resultObjects, int currentPage, int currentPageCount,
		long total) {
		PageCommResult<T> result = new PageCommResult<T>();
		result.setData(resultObjects);
		result.setMsg("请求成功");
		result.setResultCode(0);
		result.setSuccess(true);
		result.setPage(new Page(currentPage, currentPageCount, total));
		return result;
	}
}
