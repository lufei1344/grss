package com.grss.jlsx.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssClub;
import com.grss.jlsx.core.bean.GrssOrder;
import com.grss.jlsx.core.pages.Pager;

public interface GrssOrderService {
    int deleteById(String id);

    int addByGrssOrder(GrssOrder grssOrder);

    GrssOrder findById(String id);

    int updateByGrssOrder(GrssOrder grssOrder);
    
    Integer findOrderCountByCoach(List<String> courseIds);
    
    List<GrssOrder> findOrdersByCourseId(Map<String,Object> params);
    
    List<Map<String,Object>> findOrderCounts(Map<String,Object> params);
    
	void addOrderResult(String orderId, String vcIds);

	Integer findCoachOrderCount(String courseId);
	Integer findCoachOrderCount_v2(String courseId);
	
	Pager<GrssOrder> findGrssOrder(Map<String,Object> params);
	
	void updateMissOrder(Date missTime);

	List<GrssOrder> findOrderByUserId(String userId);

	void addOrderResult_V2(String orderId, String vcIds);

	List<GrssOrder> findOrderCurrentDate(int minute);
	
}