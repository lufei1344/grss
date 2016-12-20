package com.grss.jlsx.core.dao;

import java.util.Map;

import com.grss.jlsx.core.bean.GrssUserCommunity;
import com.grss.jlsx.core.bean.GrssUserCommunityKey;

public interface GrssUserCommunityDao {
    int deleteByKey(GrssUserCommunityKey key);

    int insertByGrssUserCommunity(GrssUserCommunity grssUserCommunity);

    GrssUserCommunity selectByKey(GrssUserCommunityKey key);

    int updateByGrssUserCommunity(GrssUserCommunity record);

	int selectGrssUserCommunityByUserIdAndCommunityId(Map<String, Object> params);

	GrssUserCommunity selectOneGrssUserCommunityByUserIdAndCommunityId(Map<String, Object> params);

}