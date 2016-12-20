package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.bean.GrssClub;
import com.grss.jlsx.core.pages.Pager;

public interface GrssClubService{
	
	/**insert**/
	public int insertByGrssClub(GrssClub grssClub,GrssAdminUser adminUser);
	/**Delete***/
	public int deleteByGrssClub(String clubId);
	/**GetModel***/
	public GrssClub findGrssClub(GrssClub grssClub);
	/**GetModelByID***/
	public GrssClub findGrssClubById(String clubId);
	/**Upate***/
	public int updateByGrssClub(GrssClub grssClub);
	/**countAllNum***/
	/*public int countAllNum(GrssClubVo grssClubVo);*/
	/**FindAll***/
	public List<GrssClub> findAll(GrssClub grssClub,String userId);
	/**
	 * 是否已收藏
	 * @param clubId
	 * @param userId
	 * @return
	 */
	public GrssClub findGrssClubById(String clubId,String userId);
	
	/**
	 * @comment 获取我的收藏列表
	 * @param userId
	 * @return
	 * 2016年2月29日
	 * 下午4:34:36
	 */
	List<GrssClub> findCollectClubs(String userId);
	
	public Pager<GrssClub> findGrssClubAllList(Map<String,Object> params);

}
