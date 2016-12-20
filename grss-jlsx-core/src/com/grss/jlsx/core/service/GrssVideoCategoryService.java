package com.grss.jlsx.core.service;

import java.util.List;

import com.grss.jlsx.core.bean.GrssVideoCategory;
import com.grss.jlsx.core.pages.Pager;

public interface GrssVideoCategoryService {

	List<GrssVideoCategory> findParentCategoryList(Integer programaId);

	List<GrssVideoCategory> findCategoryByParentIdList(String parentId);

	Pager<GrssVideoCategory> findCatsList(String catName);

	List<GrssVideoCategory> findParentCats();

	String findMaxCateCodeByPid(String parentId);

	int addVideoCategory(GrssVideoCategory videoCategory);

	GrssVideoCategory findCatById(String parentId);

	int updateVideoCategory(GrssVideoCategory videoCategory);

	int deleteVideoCategory(String id);

	List<GrssVideoCategory> findAllVideoCats();
}
