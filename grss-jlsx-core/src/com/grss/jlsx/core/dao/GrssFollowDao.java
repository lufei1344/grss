package com.grss.jlsx.core.dao;

import java.util.Map;

import com.grss.jlsx.core.bean.GrssFollow;

public interface GrssFollowDao {
    int deleteByKey(GrssFollow key);

    int insertByGrssFollow(GrssFollow grssFollow);
    
    Integer selectFollowCount(String userId);
    
    Integer selectFansCount(String userId);

	Integer selectFansRelationship(Map<String,String> mapUserIds);

	Integer selectFollowRelationship(Map<String,String> mapUserIds);
}