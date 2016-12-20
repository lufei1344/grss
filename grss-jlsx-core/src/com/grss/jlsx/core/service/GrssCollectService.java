package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssCollect;

import java.util.*;

public interface GrssCollectService{
	
	/**insert**/
	public int insertByGrssCollect(GrssCollect grssCollect);
	/**Delete***/
	public int deleteByGrssCollect(String id);
	/**GetModelByID***/
	public GrssCollect findGrssCollectById(String id);
	/**Upate***/
	public int updateByGrssCollect(GrssCollect grssCollect);
	
	/**FindAll***/
	public List<GrssCollect> findAll(GrssCollect grssCollect);
	
	public int deleteByIds(String clubId,String userId);
	
	public GrssCollect findGrssCollectByIds(String clubId,String userId);
}
