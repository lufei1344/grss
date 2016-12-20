package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.dao.GrssAdminUserDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssAdminUserService;

@Service
public class GrssAdminUserServiceImpl extends BaseService implements GrssAdminUserService {
	
	@Resource
	private GrssAdminUserDao grssAdminUserDao;
	
	@Override
	public GrssAdminUser findGrssAdminUserByPhone(String phone) {
		return grssAdminUserDao.selectGrssAdminUserByPhone(phone);
	}

	@Override
	public Pager<GrssAdminUser> findGrssAdminUserList(Map<String, Object> params) {
		List<GrssAdminUser> adminUserList = grssAdminUserDao.selectGrssAdminUserList(getPageParam(params));
		int total = grssAdminUserDao.selectGrssAdminUserCount(params);
		return new Pager<>(total, adminUserList);
	}

	@Override
	public void saveGrssAdminUser(GrssAdminUser adminUser) {
		grssAdminUserDao.insertByGrssAdminUser(adminUser);
	}

	@Override
	public GrssAdminUser findGrssAdminUserById(String userId) {
		return grssAdminUserDao.selectGrssAdminUserById(userId);
	}

	@Override
	public void deleteGrssAdminUser(String userId) {
		grssAdminUserDao.deleteById(userId);
	}

	@Override
	public void updateGrssAdminUser(GrssAdminUser adminUser) {
		grssAdminUserDao.updateByGrssAdminUser(adminUser);
	}

}
