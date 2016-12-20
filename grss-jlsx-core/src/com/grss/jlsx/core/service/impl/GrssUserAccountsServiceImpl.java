package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssUserAccounts;
import com.grss.jlsx.core.dao.GrssUserAccountsDao;
import com.grss.jlsx.core.service.GrssUserAccountsService;

@Service
public class GrssUserAccountsServiceImpl implements GrssUserAccountsService {
	@Resource
	private GrssUserAccountsDao grssUserAccountDao;

	@Override
	public int deleteById(String id) {
		return grssUserAccountDao.deleteById(id);
	}

	@Override
	public int addByGrssUserAccounts(GrssUserAccounts record) {
		return grssUserAccountDao.insertByGrssUserAccounts(record);
	}

	@Override
	public GrssUserAccounts findAccountsById(String id) {
		return grssUserAccountDao.selectById(id);
	}

	@Override
	public int updateByGrssUserAccounts(GrssUserAccounts record) {
		return grssUserAccountDao.updateByGrssUserAccounts(record);
	}

	@Override
	public GrssUserAccounts findAccountsByUserId(String userId) {
		return grssUserAccountDao.selectByUserId(userId);
	}

}
