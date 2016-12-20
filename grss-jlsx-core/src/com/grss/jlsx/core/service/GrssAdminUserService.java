package com.grss.jlsx.core.service;

import java.util.Map;

import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.pages.Pager;

public interface GrssAdminUserService {
	
	public GrssAdminUser findGrssAdminUserByPhone(String phone);

	public Pager<GrssAdminUser> findGrssAdminUserList(Map<String, Object> params);

	public void saveGrssAdminUser(GrssAdminUser adminUser);

	public GrssAdminUser findGrssAdminUserById(String userId);

	public void deleteGrssAdminUser(String userId);

	public void updateGrssAdminUser(GrssAdminUser adminUser);
}
