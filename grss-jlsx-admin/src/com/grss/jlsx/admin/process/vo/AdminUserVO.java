package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.Map;

import com.grss.jlsx.core.utils.StringUtil;

public class AdminUserVO {

	private String name;
	
	private String phone;
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Map<String, Object> getParamsMap(){
		Map<String, Object> params = new HashMap<>();
		if (!StringUtil.empty(name)) {
			params.put("name", name);
		}
		if (!StringUtil.empty(phone)) {
			params.put("phone", phone);
		}
		return params;
	}
}
