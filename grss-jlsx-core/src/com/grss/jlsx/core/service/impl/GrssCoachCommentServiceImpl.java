package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssCoachComment;
import com.grss.jlsx.core.dao.GrssCoachCommentDao;
import com.grss.jlsx.core.service.GrssCoachCommentService;

@Service
public class GrssCoachCommentServiceImpl extends BaseService implements GrssCoachCommentService {
	@Resource
	private GrssCoachCommentDao grssCoachCommentDao;
	
	@Override
	public int deleteById(Integer id) {
		return grssCoachCommentDao.deleteById(id);
	}

	@Override
	public int addCoachComment(GrssCoachComment coachComment) {
		return grssCoachCommentDao.insertCoachComment(coachComment);
	}

	@Override
	public GrssCoachComment findById(Integer id) {
		return grssCoachCommentDao.selectById(id);
	}

	@Override
	public int updateCoachComment(GrssCoachComment coachComment) {
		return grssCoachCommentDao.updateCoachComment(coachComment);
	}

	@Override
	public List<GrssCoachComment> findCoachCommentList(String coachId) {
		Map<String,Object> params=getPageParam();
		params.put("coachId", coachId);
		return grssCoachCommentDao.selectCommentList(params);
	}

}
