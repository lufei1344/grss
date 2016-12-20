package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssPosts;
import com.grss.jlsx.core.pages.Pager;

public interface GrssPostsService {

	List<GrssPosts> findGrssPostsByCommunityId(String communityId);
	
	List<GrssPosts> findGrssPostsAll();

	List<GrssPosts> findGrssPostsByFollowMyUserId(String userId);

	GrssPosts finGrssPostsByParams(Map<String, Object> params);

	void addGrssPosts(GrssPosts grssPosts);

	List<GrssPosts> findGrssPostsAllOrderByTotal();

	List<GrssPosts> findGrssPostsByUserId(String userId);

	GrssPosts findGrssPostsByPostsId(String postsId);

	GrssPosts findGrssPostsById(String postsId);

	List<GrssPosts> findGrssPostsByUserIds(List<String> userIds);

	List<GrssPosts> findGrssPostsByAdmireUserId(String userId);

	void deleteGrssPostsById(String postsId);

	Integer deletePosts(String postsId);
	
	Pager<GrssPosts> findGrssPost(Map<String,Object> params);
	
}
