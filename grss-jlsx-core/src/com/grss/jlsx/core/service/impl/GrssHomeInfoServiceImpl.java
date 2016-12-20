package com.grss.jlsx.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssHomeInfo;
import com.grss.jlsx.core.dao.GrssHomeInfoDao;
import com.grss.jlsx.core.enums.HomeType;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssHomeInfoService;
@Service
public class GrssHomeInfoServiceImpl extends BaseService implements GrssHomeInfoService {
	@Resource
	private GrssHomeInfoDao grssHomeInfoDao;
	
	@Override
	public List<GrssHomeInfo> findGrssHomeInfoByType(HomeType[] homeTypes) {
		List<String> paramsList = new ArrayList<>();
		if(homeTypes != null){
			for (HomeType homeType : homeTypes) {
				paramsList.add(homeType.name());
			}
		}
		return grssHomeInfoDao.selectGrssHomeInfoByType(paramsList);
	}

	@Override
	public List<GrssHomeInfo> homePage(String homeType) {
		return grssHomeInfoDao.homePage(homeType);
	}

	@Override
	public Pager<GrssHomeInfo> findHomeInfosList(Map<String, Object> params) {
		List<GrssHomeInfo> datas=grssHomeInfoDao.selectHomeInfoList(getPageParam(params));
		Integer total=grssHomeInfoDao.selectHomeInfoListCount(params);
		Pager<GrssHomeInfo> pager=new Pager<>(total, datas);
		return pager;
	}

	@Override
	public int addHomeInfo(GrssHomeInfo grssHomeInfo) {
		return grssHomeInfoDao.insertByGrssHomeInfo(grssHomeInfo);
	}

	@Override
	public GrssHomeInfo findHomeInfoById(Integer id) {
		return grssHomeInfoDao.selectById(id);
	}

	@Override
	public int updateCarousel(GrssHomeInfo grssHomeInfo) {
		return grssHomeInfoDao.updateByGrssHomeInfo(grssHomeInfo);
	}

	@Override
	public int deleteCarousel(Integer id) {
		return grssHomeInfoDao.deleteById(id);
	}
	
}
