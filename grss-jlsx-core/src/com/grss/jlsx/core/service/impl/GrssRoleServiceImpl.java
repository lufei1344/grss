package com.grss.jlsx.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssRole;
import com.grss.jlsx.core.dao.GrssRoleDao;
import com.grss.jlsx.core.service.GrssRoleService;

@Service
public class GrssRoleServiceImpl implements GrssRoleService {
	
	@Resource
	private GrssRoleDao grssRoleDao;
	
	
	@Override
	public List<GrssRole> findGrssRoleList() {
		return grssRoleDao.selectGrssRoleList();
	}

}
