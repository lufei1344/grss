package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.Map;

import com.grss.jlsx.core.utils.StringUtil;

public class MessageVO {
	
	private String title;
	
	
	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Map<String, Object> getParamsMap(){
		Map<String, Object> params = new HashMap<>();
		if(this.title != null && !StringUtil.empty(this.title)){
			params.put("title", this.title);
		}
		return params;
	}
}
