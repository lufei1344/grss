package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssClub;
import com.grss.jlsx.core.bean.GrssCommunity;
import com.grss.jlsx.core.bean.GrssUserCommunity;
import com.grss.jlsx.core.dao.GrssCommunityDao;
import com.grss.jlsx.core.dao.GrssUserCommunityDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssCommunityService;
@Service("grssCommunityService")
public class GrssCommunityServiceImpl extends BaseService implements GrssCommunityService {
	@Resource
	private GrssCommunityDao grssCommunityDao;
	@Resource
	private GrssUserCommunityDao grssUserCommunityDao;
	
	
	@Override
	public int addGrssCommunity(GrssCommunity community,GrssUserCommunity grssUserCommunity) {
		grssUserCommunityDao.insertByGrssUserCommunity(grssUserCommunity);
		return grssCommunityDao.insertByGrssCommunity(community);
	}


	@Override
	public List<GrssCommunity> findGrssCommuniryByUserId(String userId) {
		Map<String, Object> params = getPageParam();
		params.put("userId", userId);
		return grssCommunityDao.selectGrssCommuniryByUserId(params);
	}


	@Override
	public List<GrssCommunity> findGrssCommuniryByKeyword(String keyword) {
		Map<String, Object> params = getPageParam();
		params.put("keyword", keyword);
		return grssCommunityDao.selectGrssCommuniryByKeyword(params);
	}


	@Override
	public List<GrssCommunity> findGrssCommunityByRecommentd() {
		return grssCommunityDao.selectGrssCommunityByRecommentd();
	}


	@Override
	public GrssCommunity findGrssCommunityById(String communityId) {
		return grssCommunityDao.selectGrssCommunityById(communityId);
	}


	@Override
	public Pager<GrssCommunity> findGrssCommuniry(Map<String, Object> params) {
		List<GrssCommunity> list = grssCommunityDao.findGrssCommuniry(getPageParam(params));
		int total = grssCommunityDao.findGrssCommuniryCount(getPageParam(params));
		Pager<GrssCommunity> pager = new Pager<>(total,list);
		return pager;
	}

}
