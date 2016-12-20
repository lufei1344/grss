package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssPostsRemark;
import com.grss.jlsx.core.dao.GrssPostsRemarkDao;
import com.grss.jlsx.core.service.GrssPostsRemarkService;
@Service
public class GrssPostsRemarkServiceImpl extends BaseService implements GrssPostsRemarkService{
	
	@Resource
	private GrssPostsRemarkDao grssPostsRemarkDao;
	
	@Override
	public List<GrssPostsRemark> findGrssPostsRemarkByParams(Map<String, Object> params) {
		return grssPostsRemarkDao.selectGrssPostsRemarkByParams(getPageParam(params));
	}

	@Override
	public void addGrssPostsRemark(GrssPostsRemark grssPostsRemark) {
		grssPostsRemarkDao.insertByGrssPostsRemark(grssPostsRemark);
	}

}
