package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssUserEx;
import com.grss.jlsx.core.dao.GrssUserExDao;
import com.grss.jlsx.core.service.GrssUserExService;
@Service
public class GrssUserExServiceImpl implements GrssUserExService {
	@Resource
	private GrssUserExDao grssUserExDao;
	
	@Override
	public int deleteById(String userId) {
		return grssUserExDao.deleteById(userId);
	}

	@Override
	public int addGrssUserEx(GrssUserEx userEx) {
		return grssUserExDao.insertGrssUserEx(userEx);
	}

	@Override
	public GrssUserEx findById(String userId) {
		return grssUserExDao.selectById(userId);
	}

	@Override
	public int updateByGrssUserEx(GrssUserEx userEx) {
		return grssUserExDao.updateByGrssUserEx(userEx);
	}

}
