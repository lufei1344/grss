package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssPostsAdmire;

public interface GrssPostsAdmireService {

	void addGrssPostsAdmire(GrssPostsAdmire admire);

	int findPostsAdmireByUserIdAndPostsId(String userId, String postsId);

	void deleteGrssPostsAdmireByKey(GrssPostsAdmire admire);

}
