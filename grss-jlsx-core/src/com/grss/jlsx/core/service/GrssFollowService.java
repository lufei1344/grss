package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssFollow;

public interface GrssFollowService {
    int deleteByKey(GrssFollow key);

    int addByGrssFollow(GrssFollow grssFollow);
    
    Integer findFollowCount(String userId);
    
    Integer findFansCount(String userId);

	Integer findFollowRelationship(String mainUserId, String secondUserId);
}