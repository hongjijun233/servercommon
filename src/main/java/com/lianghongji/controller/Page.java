package com.lianghongji.controller;

import lombok.Data;

/**
 * 分页结果
 * 
 * @author deng.huaiyu
 *
 */
@Data
public class Page {
	//当前为第几页，从0开始
	private int current = 0;
	
	//总页数
	private long totalPage = 0;
	
	//总元素数
	private long total = 0;
	
	public Page(){
	}
	
	public Page(int current, int pageCount, long total){
		if (total == 0) {
			totalPage = 0;
		} else {
			totalPage = total / (pageCount + 1);
		}
		this.total = total;
	}
}
