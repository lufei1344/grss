package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssSysConfig;

public interface GrssSysConfigService {
	GrssSysConfig find(GrssSysConfig grssSysConfig);
	
	void update(GrssSysConfig grssSysConfig);
	
	void insert(GrssSysConfig grssSysConfig);
	
	void deleteId(String id);
	
	GrssSysConfig findByName(String name);
}
