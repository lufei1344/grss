package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssSysConfig;
import com.grss.jlsx.core.dao.GrssSysConfigDao;
import com.grss.jlsx.core.service.GrssSysConfigService;

@Service("grssSysConfigService")
public class GrssSysConfigServiceImp implements GrssSysConfigService {
	
	@Resource
	GrssSysConfigDao grssSysConfigDao;

	@Override
	public GrssSysConfig find(GrssSysConfig grssSysConfig) {
		return grssSysConfigDao.find(grssSysConfig);
	}

	@Override
	public void update(GrssSysConfig grssSysConfig) {
		grssSysConfigDao.update(grssSysConfig);
	}

	@Override
	public void insert(GrssSysConfig grssSysConfig) {
		grssSysConfigDao.insert(grssSysConfig);
	}

	@Override
	public void deleteId(String id) {
		grssSysConfigDao.deleteById(id);
	}

	@Override
	public GrssSysConfig findByName(String name) {
		GrssSysConfig grssSysConfig = new GrssSysConfig();
		grssSysConfig.setName(name);
		return this.find(grssSysConfig);
	}

}
