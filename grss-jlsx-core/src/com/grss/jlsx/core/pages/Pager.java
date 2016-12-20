package com.grss.jlsx.core.pages;

import java.util.List;

public class Pager<T> {
	/**
	 * 每页显示条数
	 */
	private int pageSize;
	/**
	 * 分页的起始页
	 */
	private int pageNum;
	/**
	 * 查询的总数
	 */
	private long totalCount;
	
	private String orderField;
	
	private String orderDirection;
	/**
	 * 分页的结果数据
	 */
	private List<T> datas;
	
	public Pager(){}
	
	public Pager(int total,List<T> datas) {
		this.totalCount = total;
		this.datas = datas;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	
	
}
