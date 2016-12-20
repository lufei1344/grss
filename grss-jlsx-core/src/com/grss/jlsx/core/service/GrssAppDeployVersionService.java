package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssAppDeployVersion;

public interface GrssAppDeployVersionService {
	
	public GrssAppDeployVersion findGrssAppDeployVersionByAppType(int appType);
}
