package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.Map;

import com.grss.jlsx.core.utils.StringUtil;

public class CoachVO {
	private String userPhone;
	private Integer infoComplete;
	private Integer status;
	private String startDate;
	private String endDate;
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public Integer getInfoComplete() {
		return infoComplete;
	}
	public void setInfoComplete(Integer infoComplete) {
		this.infoComplete = infoComplete;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
		params.put("userType", "coach");
		if(!StringUtil.empty(this.userPhone)){
			params.put("phone", this.userPhone);
		}
		if(this.infoComplete != null){
			params.put("infoComplete", this.infoComplete);
		}
		if(this.status != null){
			params.put("status", this.status);
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
