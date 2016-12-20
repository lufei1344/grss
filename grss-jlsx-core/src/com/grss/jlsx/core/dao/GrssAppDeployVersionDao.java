package com.grss.jlsx.core.dao;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssAppDeployVersion;

public interface GrssAppDeployVersionDao {

	public void insertByGrssAppDeployVersion(GrssAppDeployVersion grssAppDeployVersion);
	
	public GrssAppDeployVersion selectGrssAppDeployVersionByAppType(@Param("appType") int appType);
	
}
