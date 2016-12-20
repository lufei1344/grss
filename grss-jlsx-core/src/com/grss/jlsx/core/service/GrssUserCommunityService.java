package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssUserCommunity;
import com.grss.jlsx.core.bean.GrssUserCommunityKey;

public interface GrssUserCommunityService {
	
	public int addGrssUserCommunity(GrssUserCommunity grssUserCommunity);

	public void addGrssUserCommunityByUserIds(String communityId, String[] uIds);

	public void deleteGrssUserCommunityByUserIdAndCommunityId(GrssUserCommunityKey communityKey);

	public int findGrssUserCommunityByUserIdAndCommunityId(String userId,
			String communityId);

	public GrssUserCommunity findOneGrssUserCommunityByUserIdAndCommunityId(String userId, String communityId);
}
