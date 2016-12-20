package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.Map;

import com.grss.jlsx.core.utils.StringUtil;


public class CarouselParamsVO {
	private String type;
	private String title;
	private String isShow;
	public Map<String,Object> getParamsMap(){
		Map<String,Object> params=new HashMap<String, Object>();
		if(!StringUtil.empty(getType()))
			params.put("type", getType());
		if(!StringUtil.empty(getTitle()))
			params.put("title", getTitle());
		if(!StringUtil.empty(getIsShow()))
			params.put("isShow", getIsShow());
		return params;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
}
