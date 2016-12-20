package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssCommunitySnitch;

public interface GrssCommunitySnitchDao {
    int deleteById(String id);

    int insertByGrssCommunitySnitch(GrssCommunitySnitch grssCommunitySnitch);

    GrssCommunitySnitch selectById(String id);

    int updateByGrssCommunitySnitch(GrssCommunitySnitch grssCommunitySnitch);

}