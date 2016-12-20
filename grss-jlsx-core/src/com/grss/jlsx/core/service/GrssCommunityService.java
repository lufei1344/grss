package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssCommunity;
import com.grss.jlsx.core.bean.GrssUserCommunity;
import com.grss.jlsx.core.pages.Pager;
public interface GrssCommunityService {
	
	public int addGrssCommunity(GrssCommunity community,GrssUserCommunity grssUserCommunity);

	public List<GrssCommunity> findGrssCommuniryByUserId(String userId);

	public List<GrssCommunity> findGrssCommuniryByKeyword(String keyword);

	public List<GrssCommunity> findGrssCommunityByRecommentd();

	public GrssCommunity findGrssCommunityById(String communityId);
	
	public Pager<GrssCommunity> findGrssCommuniry(Map<String,Object> params);
	
}
