package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.Map;

import com.grss.jlsx.core.utils.StringUtil;

public class DrawMoneyVO {
	private Integer state;
	private String startDate;
	private String endDate;
	public Map<String,Object> getParamsMap(){
		Map<String,Object> params=new HashMap<String, Object>();
		if(null!=getState())
			params.put("state", getState());
		if(!StringUtil.empty(getStartDate()))
			params.put("startDate", getStartDate());
		if(!StringUtil.empty(getEndDate()))
			params.put("endDate", getEndDate());
		return params;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
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
