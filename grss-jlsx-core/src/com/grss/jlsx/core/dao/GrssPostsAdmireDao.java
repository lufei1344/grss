package com.grss.jlsx.core.dao;

import java.util.Map;

import com.grss.jlsx.core.bean.GrssPostsAdmire;

public interface GrssPostsAdmireDao {
	
    int deleteByKey(GrssPostsAdmire key);

    int insertByGrssPostsAdmire(GrssPostsAdmire grssPostsAdmire);

	int selectPostsAdmireByUserIdAndPostsId(Map<String, Object> params);

	void deleteByPostsId(String postsId);
}