package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssFeedback;
import com.grss.jlsx.core.dao.GrssFeedbackDao;
import com.grss.jlsx.core.service.GrssFeedbackService;

@Service
public class GrssFeedbackServiceImpl implements GrssFeedbackService {
	@Resource
	private GrssFeedbackDao grssFeedbackDao;
	
	@Override
	public int deleteById(String id) {
		return grssFeedbackDao.deleteById(id);
	}

	@Override
	public int addByGrssFeedback(GrssFeedback grssFeedback) {
		return grssFeedbackDao.insertByGrssFeedback(grssFeedback);
	}

	@Override
	public GrssFeedback findById(String id) {
		return grssFeedbackDao.selectById(id);
	}

	@Override
	public int updateByGrssFeedback(GrssFeedback grssFeedback) {
		return grssFeedbackDao.updateByGrssFeedback(grssFeedback);
	}

}
