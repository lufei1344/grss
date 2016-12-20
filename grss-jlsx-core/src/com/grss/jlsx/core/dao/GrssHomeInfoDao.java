package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssHomeInfo;

public interface GrssHomeInfoDao {
    int deleteById(Integer id);

    int insertByGrssHomeInfo(GrssHomeInfo grssHomeInfo);

    GrssHomeInfo selectById(Integer id);

    int updateByGrssHomeInfo(GrssHomeInfo grssHomeInfo);

	List<GrssHomeInfo> selectGrssHomeInfoByType(@Param("homeTypes") List<String> homeTypes);
	
	//首页轮播
	List<GrssHomeInfo> homePage(@Param("homeType")String homeType );

	List<GrssHomeInfo> selectHomeInfoList(Map<String, Object> pageParam);

	Integer selectHomeInfoListCount(Map<String, Object> params);

}