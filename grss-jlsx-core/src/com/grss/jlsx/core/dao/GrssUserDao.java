package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.po.GrssUserPo;

public interface GrssUserDao {
	
	GrssUser selectByUserId(@Param("userId") String userId);

	GrssUser selectGrssUserByAccount(Map<String, String> accuntMap);
	
	int insertGrssUser(GrssUser grssUser);
	
	int updateGrssUser(GrssUser grssUser);

	GrssUser selectGrssUserByPhone(@Param("phone") String phone);

	List<GrssUser> selectGrssUserByParams(Map<String, Object> params);

	List<GrssUser> selectGrssUserByKeyword(Map<String, Object> paramsMap);

	List<GrssUser> selectFollowUsersByUserId(Map<String, Object> params);
	
	List<GrssUser> selectFansUsersByUserId(Map<String, Object> params);

	List<GrssUser> selectUsersByIdsStr(@Param(value="userIds")List<String> userIds);

	List<GrssUserPo> listByHot(Map<String, Object> map);

	List<GrssUserPo> listByNewest(Map<String, Object> map);

	List<GrssUserPo> listByTop(Map<String, Object> map);

	List<GrssUser> selectGrssUserByCommunityId(@Param("communityId") String communityId);

	List<GrssUser> selectNearbyUser(Map<String, Object> params);

	List<GrssUser> selectGrssUserList(Map<String, Object> pm);

	int selectGrssUserCount(Map<String, Object> pm);

	List<GrssUser> selectGrssUserAudit(Map<String, Object> params);

	int selectGrssUserAuditCount(Map<String, Object> params);

	List<GrssUser> selectGrssUserByApplyCoachDate();
	
	GrssUserPo selectByCoachId(@Param("userId") String userId);

	List<GrssUser> selectGrssUserAll();

	List<GrssUser> selectHotUser(Map<String, Object> pageParam);
}
