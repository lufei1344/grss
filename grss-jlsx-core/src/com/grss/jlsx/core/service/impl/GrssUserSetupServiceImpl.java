package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssUserSetup;
import com.grss.jlsx.core.dao.GrssUserSetupDao;
import com.grss.jlsx.core.service.GrssUserSetupService;

@Service
public class GrssUserSetupServiceImpl implements GrssUserSetupService {
	@Resource GrssUserSetupDao grssUserSetupDao;
	
	
	public int addGrssUserSetup(GrssUserSetup grssUserSetup) {
		return grssUserSetupDao.insertGrssUserSetup(grssUserSetup);
	}

	public GrssUserSetup findGrssUserSetupByUseId(String userId) {
		return grssUserSetupDao.selectGrssUserSetupByUseId(userId);
	}

	public int updateByGrssUserSetup(GrssUserSetup grssUserSetup) {
		return grssUserSetupDao.updateByGrssUserSetup(grssUserSetup);
	}

}
