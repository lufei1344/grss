package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssAppDeployVersion;
import com.grss.jlsx.core.dao.GrssAppDeployVersionDao;
import com.grss.jlsx.core.service.GrssAppDeployVersionService;
@Service
public class GrssAppDeployVersionServiceImpl implements GrssAppDeployVersionService {

	@Resource
	private GrssAppDeployVersionDao grssAppDeployVersionDao;
	
	@Override
	public GrssAppDeployVersion findGrssAppDeployVersionByAppType(int appType) {
		return grssAppDeployVersionDao.selectGrssAppDeployVersionByAppType(appType);
	}

}
