package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssCommunity;
import com.grss.jlsx.core.bean.GrssPosts;
import com.grss.jlsx.core.dao.GrssPostsAdmireDao;
import com.grss.jlsx.core.dao.GrssPostsDao;
import com.grss.jlsx.core.dao.GrssPostsRemarkDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssPostsService;
@Service
public class GrssPostsServiceImpl extends BaseService implements GrssPostsService {
	
	@Resource
	private GrssPostsDao grssPostsDao;
	@Resource
	private GrssPostsAdmireDao grssPostsAdmireDao;
	@Resource
	private GrssPostsRemarkDao grssPostsRemarkDao;
	
	@Override
	public List<GrssPosts> findGrssPostsByCommunityId(String communityId) {
		Map<String, Object> params = getPageParam();
		params.put("communityId", communityId);
		return grssPostsDao.selectGrssPostsByCommunityId(params);
	}

	@Override
	public List<GrssPosts> findGrssPostsAll() {
		return grssPostsDao.selectGrssPostsAll(getPageParam());
	}

	@Override
	public List<GrssPosts> findGrssPostsByFollowMyUserId(String userId) {
		Map<String, Object> params = getPageParam();
		params.put("userId", userId);
		return grssPostsDao.selectGrssPostsByFollowMyUserId(params);
	}

	@Override
	public GrssPosts finGrssPostsByParams(Map<String, Object> params) {
		return grssPostsDao.selectGrssPostsByParams(params);
	}

	@Override
	public void addGrssPosts(GrssPosts grssPosts) {
		grssPostsDao.insertByGrssPosts(grssPosts);
	}

	@Override
	public List<GrssPosts> findGrssPostsAllOrderByTotal() {
		return grssPostsDao.selectGrssPostsAllOrderByTotal(getPageParam());
	}

	@Override
	public List<GrssPosts> findGrssPostsByUserId(String userId) {
		Map<String, Object> params = getPageParam();
		params.put("userId", userId);
		return grssPostsDao.selectGrssPostsByUserId(params);
	}

	@Override
	public GrssPosts findGrssPostsByPostsId(String postsId) {
		return grssPostsDao.selectGrssPostsByPostsId(postsId);
	}

	@Override
	public GrssPosts findGrssPostsById(String postsId) {
		return grssPostsDao.selectById(postsId);
	}

	@Override
	public List<GrssPosts> findGrssPostsByUserIds(List<String> userIds) {
		Map<String, Object> params = getPageParam();
		params.put("userIds", userIds);
		return grssPostsDao.selectGrssPostsByUserIds(params);
	}

	@Override
	public List<GrssPosts> findGrssPostsByAdmireUserId(String userId) {
		Map<String, Object> params = getPageParam();
		params.put("userId", userId);
		return grssPostsDao.selectGrssPostsByAdmireUserId(params);
	}

	@Override
	public void deleteGrssPostsById(String postsId) {
		grssPostsDao.deleteById(postsId);
		grssPostsRemarkDao.deleteByPostsId(postsId);
		grssPostsAdmireDao.deleteByPostsId(postsId);
	}

	@Override
	public Integer deletePosts(String postsId) {
		return grssPostsDao.deleteById(postsId);
	}

	@Override
	public Pager<GrssPosts> findGrssPost(Map<String, Object> params) {
		List<GrssPosts> list = grssPostsDao.findGrssPost(getPageParam(params));
		int total = grssPostsDao.findGrssPostCount(getPageParam(params));
		Pager<GrssPosts> pager = new Pager<>(total,list);
		return pager;
	}


}
