package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.grss.jlsx.core.utils.StringUtil;

public class PostsVO {
	private String nickName;
	private String idea;
	private String startDate;
	private String endDate;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIdea() {
		return idea;
	}
	public void setIdea(String idea) {
		this.idea = idea;
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
		if(!StringUtil.empty(this.idea)){
			params.put("idea", this.idea);
		}
		if(!StringUtil.empty(this.nickName)){
			params.put("nickName", this.nickName);
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
