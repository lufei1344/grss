package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssVideo;

public interface GrssVideoDao {
    int deleteById(String id);

    int insertByGrssVideo(GrssVideo grssVideo);

    GrssVideo selectById(String id);

    int updateByGrssVideo(GrssVideo grssVideo);

	List<GrssVideo> selectGrssVideoByCatIdList(@Param("catId") String catId);

	List<GrssVideo> selectVideoList(Map<String, Object> pageParam);

	Integer selectVideoListCount(Map<String, Object> params);

	List<GrssVideo> selectGrssVideoByCatNameAndOrderId(Map<String, Object> params);
}