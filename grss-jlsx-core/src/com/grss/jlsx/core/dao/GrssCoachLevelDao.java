package com.grss.jlsx.core.dao;

import org.apache.ibatis.annotations.Param;

public interface GrssCoachLevelDao {
	Integer selectCoachLevelByOrderCount(@Param("orderCount")Integer orderCount);
}