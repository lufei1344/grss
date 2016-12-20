package com.grss.jlsx.core.pages;

public class SystemContext {
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageNum = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageOffset = new ThreadLocal<Integer>();
	/**
	 * 升序还是降序
	 */
	private static ThreadLocal<String> order = new ThreadLocal<String>();
	/**
	 * 根据那个字段排序
	 */
	private static ThreadLocal<String> sort = new ThreadLocal<String>();
	
	
	public static String getOrder() {
		return order.get();
	}

	public static void setOrder(String _order) {
		order.set(_order);
	}
	
	public static void removeOrder() {
		order.remove();
	}

	public static String getSort() {
		return sort.get();
	}

	public static void setSort(String _sort) {
		sort.set(_sort);
	}
	
	public static void removeSort() {
		sort.remove();
	}

	public static int getPageOffset() {
		return pageOffset.get();
	}

	public static void setPageOffset(int _pageOffset) {
		pageOffset.set(_pageOffset);
	}
	
	public static void removePageOffset() {
		pageOffset.remove();
	}

	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}
	
	public static int getPageSize() {
		return pageSize.get();
	}
	
	public static void removePageSize() {
		pageSize.remove();
	}
	
	public static void setPageNum(int _pageNum) {
		pageNum.set(_pageNum);
	}
	
	public static int getPageNum() {
		return pageNum.get();
	}
	
	public static void removePageNum() {
		pageNum.remove();
	}
}
