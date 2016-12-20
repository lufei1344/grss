package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssAdminUser;

public interface GrssAdminUserDao {
    int deleteById(String id);

    int insertByGrssAdminUser(GrssAdminUser adminUser);

    GrssAdminUser selectById(String id);

    int updateByGrssAdminUser(GrssAdminUser adminUser);

	GrssAdminUser selectGrssAdminUserByPhone(@Param("phone") String phone);

	List<GrssAdminUser> selectGrssAdminUserList(Map<String, Object> pageParam);

	int selectGrssAdminUserCount(Map<String, Object> params);

	GrssAdminUser selectGrssAdminUserById(@Param("id") String id);
}