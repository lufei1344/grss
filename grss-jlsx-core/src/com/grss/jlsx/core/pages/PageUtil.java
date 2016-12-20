package com.grss.jlsx.core.pages;

public class PageUtil {
	private final static int PAGE_SHOW_COUNT = 20;
	
	
	public static int getOffset(){
		int offset = SystemContext.getPageNum() > 0 ? SystemContext.getPageNum() -1 : 0;
		return offset * (SystemContext.getPageSize() == 0 ? PAGE_SHOW_COUNT : SystemContext.getPageSize());
	}
	
	public static int getPageSize(){
		return SystemContext.getPageSize();
	}
	
	public static int getPageNum(){
		return SystemContext.getPageNum() == 0 ? 1 : SystemContext.getPageNum();
	}
	
}
