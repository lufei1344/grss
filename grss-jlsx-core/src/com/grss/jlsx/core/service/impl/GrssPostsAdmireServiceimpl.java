package com.grss.jlsx.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssPostsAdmire;
import com.grss.jlsx.core.dao.GrssPostsAdmireDao;
import com.grss.jlsx.core.service.GrssPostsAdmireService;
@Service
public class GrssPostsAdmireServiceimpl implements GrssPostsAdmireService {
	@Resource
	private GrssPostsAdmireDao grssPostsAdmireDao;
	
	@Override
	public void addGrssPostsAdmire(GrssPostsAdmire admire) {
		grssPostsAdmireDao.insertByGrssPostsAdmire(admire);
	}

	@Override
	public int findPostsAdmireByUserIdAndPostsId(String userId, String postsId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("postsId", postsId);
		return grssPostsAdmireDao.selectPostsAdmireByUserIdAndPostsId(params);
	}

	@Override
	public void deleteGrssPostsAdmireByKey(GrssPostsAdmire admire) {
		grssPostsAdmireDao.deleteByKey(admire);
	}

}
