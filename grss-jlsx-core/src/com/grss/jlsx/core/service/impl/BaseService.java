package com.grss.jlsx.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.grss.jlsx.core.pages.PageUtil;

public class BaseService {
	protected Map<String,Object> getPageParam(){
		Map<String, Object> params = new HashMap<>();
		params.put("offset", PageUtil.getOffset());
		params.put("pageSize", PageUtil.getPageSize());
		return params;
	}
	
	protected Map<String,Object> getPageParam(Map<String, Object> pms){
		Map<String, Object> params = new HashMap<>();
		params.put("offset", PageUtil.getOffset());
		params.put("pageSize", PageUtil.getPageSize());
		params.putAll(pms);
		return params;
	}
}
