package com.grss.jlsx.core.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssFollow;
import com.grss.jlsx.core.dao.GrssFollowDao;
import com.grss.jlsx.core.service.GrssFollowService;

@Service
public class GrssFollowServiceImpl implements GrssFollowService {
	@Resource
	private GrssFollowDao grssFollowDao;
	@Override
	public int deleteByKey(GrssFollow key) {
		return grssFollowDao.deleteByKey(key);
	}

	@Override
	public int addByGrssFollow(GrssFollow grssFollow) {
		return grssFollowDao.insertByGrssFollow(grssFollow);
	}

	@Override
	public Integer findFollowCount(String userId) {
		return grssFollowDao.selectFollowCount(userId);
	}

	@Override
	public Integer findFansCount(String userId) {
		return grssFollowDao.selectFansCount(userId);
	}
	
	@Override
	public Integer findFollowRelationship(String mainUserId,String secondUserId){
		Map<String,String> mapUserIds=new HashMap<String, String>();
		mapUserIds.put("mainUserId", mainUserId);
		mapUserIds.put("secondUserId", secondUserId);
		Integer isFans=grssFollowDao.selectFansRelationship(mapUserIds);
		Integer isFollow=grssFollowDao.selectFollowRelationship(mapUserIds);
		if(isFollow!=0){
			return isFans+isFollow;
		}else{
			return 0;
		}
	}
}
