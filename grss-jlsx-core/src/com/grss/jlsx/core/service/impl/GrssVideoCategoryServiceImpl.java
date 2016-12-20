package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssVideoCategory;
import com.grss.jlsx.core.dao.GrssVideoCategoryDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssVideoCategoryService;
@Service
public class GrssVideoCategoryServiceImpl extends BaseService implements GrssVideoCategoryService {
	@Resource
	private GrssVideoCategoryDao grssVideoCategoryDao;
	
	@Override
	public List<GrssVideoCategory> findParentCategoryList(Integer programaId) {
		return grssVideoCategoryDao.selectParentCategoryList(programaId);
	}

	@Override
	public List<GrssVideoCategory> findCategoryByParentIdList(String parentId) {
		return grssVideoCategoryDao.selectCategoryByParentIdList(parentId);
	}

	@Override
	public Pager<GrssVideoCategory> findCatsList(String catName) {
		Map<String,Object> map = getPageParam();
		map.put("catName", catName);
		List<GrssVideoCategory> datas=grssVideoCategoryDao.selectCatsList(map);
		Integer total=grssVideoCategoryDao.selectCatsListCount();
		Pager<GrssVideoCategory> pager=new Pager<>(total, datas);
		return pager;
	}

	@Override
	public List<GrssVideoCategory> findParentCats() {
		return grssVideoCategoryDao.selectParentCats();
	}

	@Override
	public String findMaxCateCodeByPid(String parentId) {
		return grssVideoCategoryDao.selectMaxCateCodeByPid(parentId);
	}

	@Override
	public int addVideoCategory(GrssVideoCategory videoCategory) {
		return grssVideoCategoryDao.insertByGrssVideoCategory(videoCategory);
	}

	@Override
	public GrssVideoCategory findCatById(String parentId) {
		return grssVideoCategoryDao.selectById(parentId);
	}

	@Override
	public int updateVideoCategory(GrssVideoCategory videoCategory) {
		return grssVideoCategoryDao.updateByGrssVideoCategory(videoCategory);
	}

	@Override
	public int deleteVideoCategory(String id) {
		return grssVideoCategoryDao.deleteById(id);
	}

	@Override
	public List<GrssVideoCategory> findAllVideoCats() {
		return grssVideoCategoryDao.selectAllVideoCats();
	}

}
