package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.grss.jlsx.core.utils.StringUtil;

public class CommunityVO {
	private String name;
	private String startDate;
	private String endDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Map<String, Object> getParams(){
		Map<String, Object> params = new HashMap<>();
		if(!StringUtil.empty(this.name)){
			params.put("name", this.name);
		}
		if(!StringUtil.empty(this.startDate)){
			params.put("startDate", this.startDate);
		}
		if(!StringUtil.empty(this.endDate)){
			params.put("endDate", this.endDate);
		}
		return params;
	}
	
	
}
