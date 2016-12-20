package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssCollect;
import com.grss.jlsx.core.dao.GrssCollectDao;
import com.grss.jlsx.core.service.GrssCollectService;

import java.util.*;

@Service("GrssCollectImpl")
public class GrssCollectServiceImpl extends BaseService implements GrssCollectService{
	
	@Resource
	private GrssCollectDao grssCollectDao;

	/**insert**/
	public int insertByGrssCollect(GrssCollect grssCollect){
		return grssCollectDao.insertByGrssCollect(grssCollect);
	}
	/**Delete***/
	public int deleteByGrssCollect(String id){
		return grssCollectDao.deleteById(id);
	}
	
	/**GetModelByID***/
	public GrssCollect findGrssCollectById(String id){
			return grssCollectDao.selectById(id);
	}
	/**Upate***/
	public int updateByGrssCollect(GrssCollect grssCollect){
		return grssCollectDao.updateByGrssCollect(grssCollect);
	}
	
	/**FindAll***/
	public List<GrssCollect> findAll(GrssCollect grssCollect){
		return null;
	}
	
	public int deleteByIds(String clubId,String userId){
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("clubId", clubId);
		params.put("userId", userId);
		return grssCollectDao.deleteByIds(params);
	}
	
	public GrssCollect findGrssCollectByIds(String clubId,String userId){
		return grssCollectDao.findGrssCollect(clubId, userId);
	}
}
