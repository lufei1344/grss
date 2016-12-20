package com.grss.jlsx.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssUserCommunity;
import com.grss.jlsx.core.bean.GrssUserCommunityKey;
import com.grss.jlsx.core.dao.GrssUserCommunityDao;
import com.grss.jlsx.core.service.GrssUserCommunityService;
@Service
public class GrssUserCommunityServiceImpl implements GrssUserCommunityService {
	
	@Resource
	private GrssUserCommunityDao grssUserCommunityDao;

	@Override
	public int addGrssUserCommunity(GrssUserCommunity grssUserCommunity) {
		return grssUserCommunityDao.insertByGrssUserCommunity(grssUserCommunity);
	}

	@Override
	public void addGrssUserCommunityByUserIds(String communityId, String[] uIds) {
		for(String uId : uIds){
			GrssUserCommunity grssUserCommunity = new GrssUserCommunity(uId, communityId, 3, 0);
			this.addGrssUserCommunity(grssUserCommunity);
		}
	}

	@Override
	public void deleteGrssUserCommunityByUserIdAndCommunityId(GrssUserCommunityKey communityKey) {
		grssUserCommunityDao.deleteByKey(communityKey);
	}

	@Override
	public int findGrssUserCommunityByUserIdAndCommunityId(String userId,
			String communityId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("communityId", communityId);
		return grssUserCommunityDao.selectGrssUserCommunityByUserIdAndCommunityId(params);
	}

	@Override
	public GrssUserCommunity findOneGrssUserCommunityByUserIdAndCommunityId(String userId, String communityId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("communityId", communityId);
		return grssUserCommunityDao.selectOneGrssUserCommunityByUserIdAndCommunityId(params);
	}
	

}
