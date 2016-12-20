package com.grss.jlsx.core.service;

import java.util.List;

import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.po.GrssUserPo;

public interface GrssCoachService {
	public GrssUserPo findGrssUserByUserId(String userId) ;
	/**
	 * 场馆最热教练列表
	 * @param clubId
	 * @return
	 */
	public List<GrssUserPo> listByHot(String clubId,GrssUser grssUser);
	/**
	 * 场馆最新教练列表
	 * @param clubId
	 * @return
	 */
	public List<GrssUserPo> listByNewest(String clubId,GrssUser grssUser);
	/**
	 * 场馆榜单教练列表
	 * @param clubId
	 * @return
	 */
	public List<GrssUserPo> listByTop(String clubId,GrssUser grssUser);
}
