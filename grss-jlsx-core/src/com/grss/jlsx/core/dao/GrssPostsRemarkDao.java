package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssPostsRemark;

public interface GrssPostsRemarkDao {
	int deleteById(String id);

	int insertByGrssPostsRemark(GrssPostsRemark grssPostsRemark);

	GrssPostsRemark selectById(String id);

	int updateByGrssPostsRemark(GrssPostsRemark grssPostsRemark);

	List<GrssPostsRemark> selectGrssPostsRemarkByParams(Map<String, Object> pageParam);

	void deleteByPostsId(String postsId);

}