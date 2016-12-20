package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssVideo;
import com.grss.jlsx.core.pages.Pager;

public interface GrssVideoService {
 
	List<GrssVideo> findGrssVideoByCatIdList(String catId);

	Pager<GrssVideo> findVideosList(Map<String, Object> params);

	List<GrssVideo> findGrssVideoByCatNameAndOrderId(String orderId, String catName_level3);

	int addVideo(GrssVideo grssVideo);

	GrssVideo findVideoById(String id);

	int updateVideo(GrssVideo grssVideo);

	int deleteVideo(String id);
 
}
