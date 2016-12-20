package com.grss.jlsx.core.dao;

import java.util.List;

import com.grss.jlsx.core.bean.GrssRole;

public interface GrssRoleDao {
    int deleteById(String roleId);

    int insertByGrssRole(GrssRole grssRole);

    GrssRole selectByRoleId(String roleId);

    int updateByGrssRole(GrssRole grssRole);

	List<GrssRole> selectGrssRoleList();
}