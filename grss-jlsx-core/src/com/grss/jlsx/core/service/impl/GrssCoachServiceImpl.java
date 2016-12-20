package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.dao.GrssUserDao;
import com.grss.jlsx.core.po.GrssUserPo;
import com.grss.jlsx.core.service.GrssCoachService;
@Service("grssCoachService")
public class GrssCoachServiceImpl extends BaseService implements GrssCoachService {
	@Resource
	private GrssUserDao grssUserDao;
	
	@Override
	public GrssUserPo findGrssUserByUserId(String userId) {
		GrssUserPo coach = grssUserDao.selectByCoachId(userId);
		return coach;
	}

	@Override
	public List<GrssUserPo> listByHot(String clubId, GrssUser grssUser) {
		Map<String, Object> map = getPageParam();
		map.put("clubId", clubId);
		map.put("grssUser",grssUser);
		return grssUserDao.listByHot(map);
	}

	@Override
	public List<GrssUserPo> listByNewest(String clubId, GrssUser grssUser) {
		Map<String, Object> map = getPageParam();
		map.put("clubId", clubId);
		map.put("grssUser",grssUser);
		return grssUserDao.listByNewest(map);
	}

	@Override
	public List<GrssUserPo> listByTop(String clubId, GrssUser grssUser) {
		Map<String, Object> map = getPageParam();
		map.put("clubId", clubId);
		map.put("grssUser",grssUser);
		return grssUserDao.listByTop(map);
	}
	
	private GrssUser user2Coach(GrssUser grssUser){
		if(grssUser !=null){
			grssUser.setPassword(null);
			grssUser.setUserWeixin(null);
			grssUser.setUserQQ(null);
		}
		
		return grssUser;
	}

}
