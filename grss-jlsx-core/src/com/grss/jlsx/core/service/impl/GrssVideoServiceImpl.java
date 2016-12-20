package com.grss.jlsx.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssVideo;
import com.grss.jlsx.core.dao.GrssVideoDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssVideoService;
@Service
public class GrssVideoServiceImpl extends BaseService implements GrssVideoService {
	
	@Resource
	private GrssVideoDao grssVideoDao;
	
	@Override
	public List<GrssVideo> findGrssVideoByCatIdList(String catId) {
		return grssVideoDao.selectGrssVideoByCatIdList(catId);
	}

	@Override
	public Pager<GrssVideo> findVideosList(Map<String, Object> params) {
		List<GrssVideo> list = grssVideoDao.selectVideoList(getPageParam(params));
		Integer total = grssVideoDao.selectVideoListCount(params);
		Pager<GrssVideo> pager = new Pager<GrssVideo>(total,list);
		return pager;
	}

	@Override
	public List<GrssVideo> findGrssVideoByCatNameAndOrderId(String orderId, String catName_level3) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		params.put("catName_level3", catName_level3);
		
		return grssVideoDao.selectGrssVideoByCatNameAndOrderId(params);
	}

	@Override
	public int addVideo(GrssVideo grssVideo) {
		return grssVideoDao.insertByGrssVideo(grssVideo);
	}

	@Override
	public GrssVideo findVideoById(String id) {
		return grssVideoDao.selectById(id);
	}

	@Override
	public int updateVideo(GrssVideo grssVideo) {
		return grssVideoDao.updateByGrssVideo(grssVideo);
	}

	@Override
	public int deleteVideo(String id) {
		return grssVideoDao.deleteById(id);
	}

}
