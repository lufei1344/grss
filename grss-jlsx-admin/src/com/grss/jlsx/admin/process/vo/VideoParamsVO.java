package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.Map;

import com.grss.jlsx.core.utils.StringUtil;


public class VideoParamsVO {
	private String videoName;
	private String cateCode;
	private String startDate;
	private String endDate;
	public Map<String,Object> getParamsMap(){
		Map<String,Object> params=new HashMap<String, Object>();
		if(!StringUtil.empty(getVideoName()))
			params.put("videoName", getVideoName());
		if(!StringUtil.empty(getCateCode()))
			params.put("cateCode", getCateCode());
		if(!StringUtil.empty(getStartDate()))
			params.put("startDate", getStartDate());
		if(!StringUtil.empty(getEndDate()))
			params.put("endDate", getEndDate());
		return params;
	}
	
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
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
	
	
}
