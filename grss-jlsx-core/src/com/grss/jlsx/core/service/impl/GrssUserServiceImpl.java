package com.grss.jlsx.core.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssAssets;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.dao.GrssAssetsDao;
import com.grss.jlsx.core.dao.GrssCoachLevelDao;
import com.grss.jlsx.core.dao.GrssUserDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.utils.StringUtil;
@Service("grssUserService")
public class GrssUserServiceImpl extends BaseService implements GrssUserService{
	@Resource
	private GrssUserDao grssUserDao;
	@Resource
	private GrssAssetsDao grssAssetsDao;
	@Resource
	private GrssCoachLevelDao grssCoachLevelDao;
	
	public GrssUser findGrssUserByUserId(String userId) {
		return grssUserDao.selectByUserId(userId);
	}

	public GrssUser findGrssUserByAccount(Map<String, String> accuntMap) {
		return grssUserDao.selectGrssUserByAccount(accuntMap);
	}

	public int addGrssUser(GrssUser grssUser) {
		GrssAssets grssAssets = new GrssAssets();
		grssAssets.setAmount("0");
		grssAssets.setId(StringUtil.getUUID());
		grssAssets.setUserId(grssUser.getUserId());
		grssAssetsDao.insertByGrssAssets(grssAssets);
		return grssUserDao.insertGrssUser(grssUser);
	}
	
	public int updateGrssUser(GrssUser grssUser){
		return grssUserDao.updateGrssUser(grssUser);
	}

	@Override
	public GrssUser findGrssUserByPhone(String phone) {
		return grssUserDao.selectGrssUserByPhone(phone);
	}

	@Override
	public List<GrssUser> findGrssUserByParams(Map<String, Object> params) {
		return grssUserDao.selectGrssUserByParams(params);
	}

	@Override
	public List<GrssUser> findGrssUserByPostsIdList(String postsId) {
		Map<String, Object> pms = getPageParam();
		pms.put("postsId", postsId);
		return grssUserDao.selectGrssUserByParams(pms);
	}

	@Override
	public List<GrssUser> findGrssUserByKeyword(Map<String, Object> paramsMap) {
		return grssUserDao.selectGrssUserByKeyword(getPageParam(paramsMap));
	}

	@Override
	public List<GrssUser> findFollowUsersByUserId(String userId) {
		Map<String,Object> params=getPageParam();
		params.put("userId",userId);
		return grssUserDao.selectFollowUsersByUserId(params);
	}

	@Override
	public List<GrssUser> findFansUsersByUserId(String userId) {
		Map<String,Object> params=getPageParam();
		params.put("userId",userId);
		return grssUserDao.selectFansUsersByUserId(params);
	}

	@Override
	public List<GrssUser> findUsersByIdsStr(String userIds) {
		String [] idStrs=userIds.split(",");
		List<String> ids=Arrays.asList(idStrs);
		return grssUserDao.selectUsersByIdsStr(ids);
	}

	@Override
	public List<GrssUser> findGrssUserByCommunityId(String communityId) {
		return grssUserDao.selectGrssUserByCommunityId(communityId);
	}

	@Override
	public List<GrssUser> findNearbyUser(Map<String,Object> params) {
		return grssUserDao.selectNearbyUser(getPageParam(params));
	}

	@Override
	public Pager<GrssUser> findGrssUserList(Map<String, Object> params) {
		Map<String, Object> pm = getPageParam(params);
		List<GrssUser> userList = grssUserDao.selectGrssUserList(pm);
		int total = grssUserDao.selectGrssUserCount(params);
		return new Pager<GrssUser>(total, userList);
	}

	@Override
	public GrssUser findGrssUserById(String userId) {
		return grssUserDao.selectByUserId(userId);
	}

	@Override
	public Pager<GrssUser> findGrssUserAudit(Map<String,Object> params) {
		List<GrssUser> userList = grssUserDao.selectGrssUserAudit(getPageParam(params));
		int total = grssUserDao.selectGrssUserAuditCount(params);
		Pager<GrssUser> pager = new Pager<>(total, userList);
		return pager;
	}

	@Override
	public List<GrssUser> findGrssUserByApplyCoachDate() {
		return grssUserDao.selectGrssUserByApplyCoachDate();
	}

	@Override
	public List<GrssUser> findGrssUserAll() {
		return grssUserDao.selectGrssUserAll();
	}
	
	@Override
	public List<GrssUser> findUsersByHot(){
		return grssUserDao.selectHotUser(getPageParam());
	}

	@Override
	public Integer findCoachLevel(Integer orderCount) {
		return grssCoachLevelDao.selectCoachLevelByOrderCount(orderCount);
	}

}
