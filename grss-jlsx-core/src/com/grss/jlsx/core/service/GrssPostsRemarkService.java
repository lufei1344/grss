package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssPostsRemark;

public interface GrssPostsRemarkService {

	List<GrssPostsRemark> findGrssPostsRemarkByParams(Map<String, Object> params);

	void addGrssPostsRemark(GrssPostsRemark grssPostsRemark);

}
