package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.pages.Pager;

public interface GrssUserService {
	/**
	 * @comment 根据用户ID 查询用户
	 * @param userId
	 * @return
	 * 2016年1月27日
	 * 下午3:08:36
	 */
	GrssUser findGrssUserByUserId(String userId);
	/**
	 * @comment 根据第三方信息查询用户信息
	 * @param accuntMap
	 * @return
	 * 2016年1月27日
	 * 下午3:09:24
	 */
	GrssUser findGrssUserByAccount(Map<String,String> accuntMap);
	
	/**
	 * @comment 选择性插入用户信息
	 * @param grssUser
	 * @return
	 * 2016年1月27日
	 * 下午3:10:24
	 */
	int addGrssUser(GrssUser grssUser);
	
	/**
	 * @comment 选择性更新用户信息
	 * @param grssUser
	 * @return
	 * 2016年1月27日
	 * 下午3:29:09
	 */
	int updateGrssUser(GrssUser grssUser);
	/**
	 * 根据手机号搜索
	 * @param username
	 * @return
	 */
	GrssUser findGrssUserByPhone(String phone);
	/**
	 * 根据帖子用户ID查询  分页前6条
	 * @param postsId
	 * @return
	 */
	List<GrssUser> findGrssUserByParams(Map<String, Object> params);
	/**
	 * 根据帖子ID查询
	 * @param params
	 * @return
	 */
	List<GrssUser> findGrssUserByPostsIdList(String postsId);
	
	/**
	 * @comment 搜索用户
	 * @param paramsMap 查询条件
	 * @return
	 * 2016年2月16日
	 * 下午5:42:40
	 */
	List<GrssUser> findGrssUserByKeyword(Map<String, Object> paramsMap);
	/**
	 * @comment 查询用户关注列表
	 * @param userId
	 * @return
	 * 2016年2月18日
	 * 下午1:23:26
	 */
	List<GrssUser> findFollowUsersByUserId(String userId);
	
	/**
	 * @comment 查询用户粉丝列表
	 * @param userId
	 * @return
	 * 2016年2月18日
	 * 下午1:39:11
	 */
	List<GrssUser> findFansUsersByUserId(String userId);
	
	/**
	 * @comment 根据userIds 查询用户信息  1,2,3,4..
	 * @param userIds
	 * @return
	 * 2016年2月19日
	 * 下午4:26:53
	 */
	List<GrssUser> findUsersByIdsStr(String userIds);
	/**
	 * 获取社区居民
	 * @param communityId
	 * @return
	 */
	List<GrssUser> findGrssUserByCommunityId(String communityId);
	/**
	 *  获取附近好友信息
	 * @param lngD
	 * @param latD
	 * @return
	 */
	List<GrssUser> findNearbyUser(Map<String,Object> params);
	/**
	 * 查询用户列表
	 * @param params
	 * @return
	 */
	Pager<GrssUser> findGrssUserList(Map<String, Object> params);
	/**
	 * 根据用户Id查询
	 * @param userId
	 * @return
	 */
	GrssUser findGrssUserById(String userId);
	/**
	 * 查询正在审核的用户
	 * @return
	 */
	Pager<GrssUser> findGrssUserAudit(Map<String,Object> params);
	/**
	 * 查询申请教练资格
	 * @return
	 */
	List<GrssUser> findGrssUserByApplyCoachDate();
	
	List<GrssUser> findGrssUserAll();
	
	/**
	 * @comment 根据粉丝数排名查询 达人用户
	 * @return
	 * 2016年5月29日
	 * 下午2:53:55
	 */
	List<GrssUser> findUsersByHot();
	
	/**
	 * @comment 根据订单数确定订单等级
	 * @param orderCount
	 * @return
	 * 2016年6月12日
	 * 下午11:36:06
	 */
	Integer findCoachLevel(Integer orderCount);
}
