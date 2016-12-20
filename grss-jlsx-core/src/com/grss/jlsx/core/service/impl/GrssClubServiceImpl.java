package com.grss.jlsx.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssAdminClub;
import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.bean.GrssClub;
import com.grss.jlsx.core.dao.GrssAdminClubDao;
import com.grss.jlsx.core.dao.GrssClubDao;
import com.grss.jlsx.core.dao.GrssCollectDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssClubService;
import com.grss.jlsx.core.utils.StringUtil;

@Service("GrssClubImpl")
public class GrssClubServiceImpl extends BaseService implements GrssClubService{
	
	@Resource
	private GrssClubDao grssClubDao;
	@Resource
	private GrssCollectDao grssCollectDao;
	@Resource
	private GrssAdminClubDao grssAdminClubDao;

	/**insert**/
	public int insertByGrssClub(GrssClub grssClub,GrssAdminUser adminUser){
		if(!StringUtil.empty(adminUser.getGrssRole().getRoleType()) && "CLUB".equals(adminUser.getGrssRole().getRoleType())){
			GrssAdminClub adminClub = new GrssAdminClub();
			adminClub.setAdminUserId(adminUser.getId());
			adminClub.setClubId(grssClub.getClubId());
			grssAdminClubDao.insertByGrssAdminClub(adminClub);
		}
		return grssClubDao.insertByGrssClub(grssClub);
	}
	/**Delete***/
	public int deleteByGrssClub(String clubId){
		return grssClubDao.deleteById(clubId);
	}
	/**GetModel***/
	public GrssClub findGrssClub(GrssClub grssClub){
		return grssClubDao.findGrssClub(grssClub);
	}
	/**GetModelByID***/
	public GrssClub findGrssClubById(String clubId){
			return grssClubDao.selectById(clubId);
	}
	/**Update***/
	public int updateByGrssClub(GrssClub grssClub){
		return grssClubDao.updateByGrssClub(grssClub);
	}
	@Override
	public List<GrssClub> findAll(GrssClub grssClub,String userId) {
		Map<String,Object> map = getPageParam();
		map.put("grssClub", grssClub);
		map.put("userId", userId);
		return grssClubDao.findAll(map);
	}
	@Override
	public GrssClub findGrssClubById(String clubId, String userId) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("clubId", clubId);
		params.put("userId", userId);
		GrssClub grssClub = grssClubDao.selectByIds(params);
//		if(grssClub != null){
//			grssClub.setGrssCollect(grssCollectDao.findGrssCollect(clubId, userId));
//		}
		return grssClub;
	}
	@Override
	public List<GrssClub> findCollectClubs(String userId) {
		Map<String,Object> map = getPageParam();
		map.put("userId", userId);
		return grssClubDao.selectCollectClubs(map);
	} 
	
	@Override
	public Pager<GrssClub> findGrssClubAllList(Map<String,Object> params) {
		List<GrssClub> list = grssClubDao.selectGrssClubAllList(getPageParam(params));
		int total = grssClubDao.selectGrssClubAllCount(params);
		Pager<GrssClub> pager = new Pager<>(total,list);
		return pager;
	}


}
