package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssSysConfig;

public interface GrssSysConfigDao {
	
	int deleteById(String id);

    int insert(GrssSysConfig grssSysConfig);

    GrssSysConfig find(GrssSysConfig grssSysConfig);

    int update(GrssSysConfig grssSysConfig);
    
    
    
}
