package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssPosts;

public interface GrssPostsDao {
	
    int deleteById(String id);

    int insertByGrssPosts(GrssPosts grrsPosts);
    
    GrssPosts selectById(String id);
    
    int updateByGrssPosts(GrssPosts record);

	List<GrssPosts> selectGrssPostsByCommunityId(Map<String, Object> params);

	List<GrssPosts> selectGrssPostsAll(Map<String, Object> params);

	List<GrssPosts> selectGrssPostsByFollowMyUserId(Map<String, Object> params);

	GrssPosts selectGrssPostsByParams(Map<String, Object> params);

	List<GrssPosts> selectGrssPostsAllOrderByTotal(Map<String, Object> map);

	List<GrssPosts> selectGrssPostsByUserId(Map<String, Object> map);

	GrssPosts selectGrssPostsByPostsId(@Param("postsId") String postsId);

	List<GrssPosts> selectGrssPostsByUserIds(Map<String, Object> params);

	List<GrssPosts> selectGrssPostsByAdmireUserId(Map<String, Object> params);

	List<GrssPosts> findGrssPost(Map<String, Object> pageParam);

	int findGrssPostCount(Map<String, Object> pageParam);

}