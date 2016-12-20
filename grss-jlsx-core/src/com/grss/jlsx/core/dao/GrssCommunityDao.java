package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssCommunity;

public interface GrssCommunityDao {
    int deleteById(String id);

    int insertByGrssCommunity(GrssCommunity grssCommunity);

    GrssCommunity selectById(String id);

    int updateByGrssCommunity(GrssCommunity grssCommunity);

	List<GrssCommunity> selectGrssCommuniryByUserId(Map<String, Object> params);

	List<GrssCommunity> selectGrssCommuniryByKeyword(Map<String, Object> params);

	List<GrssCommunity> selectGrssCommunityByRecommentd();

	GrssCommunity selectGrssCommunityById(@Param("communityId") String communityId);

	List<GrssCommunity> findGrssCommuniry(Map<String, Object> pageParam);

	int findGrssCommuniryCount(Map<String, Object> params);
}