package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssAssets;
import com.grss.jlsx.core.dao.GrssAssetsDao;
import com.grss.jlsx.core.service.GrssAssetsService;

@Service("grssAssetsService")
public class GrssAssetsServiceImpl implements GrssAssetsService {
	@Resource
	private GrssAssetsDao grssAssetsDao;
	
	@Override
	public int deleteById(String id) {
		return grssAssetsDao.deleteById(id);
	}

	@Override
	public int addByGrssAssets(GrssAssets grssAssets) {
		return grssAssetsDao.insertByGrssAssets(grssAssets);
	}

	@Override
	public GrssAssets findById(String id) {
		return grssAssetsDao.selectById(id);
	}

	@Override
	public int updateByGrssAssets(GrssAssets grssAssets) {
		return grssAssetsDao.updateByGrssAssets(grssAssets);
	}

	@Override
	public GrssAssets findByUserId(String userId) {
		return grssAssetsDao.selectByUserId(userId);
	}

}
