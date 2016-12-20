package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssHomeInfo;
import com.grss.jlsx.core.enums.HomeType;
import com.grss.jlsx.core.pages.Pager;

public interface GrssHomeInfoService {
	
	public List<GrssHomeInfo> findGrssHomeInfoByType(HomeType[] homeTypes);
	
	List<GrssHomeInfo> homePage(@Param("homeType")String homeType );

	Pager<GrssHomeInfo> findHomeInfosList(Map<String, Object> params);

	int addHomeInfo(GrssHomeInfo grssHomeInfo);

    GrssHomeInfo findHomeInfoById(Integer id);

    int updateCarousel(GrssHomeInfo grssHomeInfo);

	int deleteCarousel(Integer id);
}
