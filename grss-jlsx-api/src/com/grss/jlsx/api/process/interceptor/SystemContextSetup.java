package com.grss.jlsx.api.process.interceptor;

import java.util.Map;

import com.grss.jlsx.core.pages.SystemContext;
import com.grss.jlsx.core.utils.StringUtil;

public class SystemContextSetup {
	/**
	 * 
	 * @comment 设置分页参数
	 * 2015年12月25日
	 * 下午5:28:38
	 */
	public static void setPagerParams(Map<String, String[]> reqMap){
		String[] offsetArray = (String[])reqMap.get("page");
		String[] pageSizeArray = (String[]) reqMap.get("num");
		int offset = 1;
		int pageSize = 10;
		if(offsetArray != null){
			offset = Integer.parseInt(StringUtil.empty(offsetArray[0]) ? offset+"" : offsetArray[0]);
		}
		if(pageSizeArray != null){
			pageSize = Integer.parseInt(StringUtil.empty(pageSizeArray[0]) ? pageSize+"" : pageSizeArray[0]);
		}
		SystemContext.setPageNum(offset);
		SystemContext.setPageSize(pageSize);
	}
}
