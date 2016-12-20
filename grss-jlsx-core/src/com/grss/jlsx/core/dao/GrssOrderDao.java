package com.grss.jlsx.core.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssOrder;
import com.grss.jlsx.core.bean.GrssPosts;

public interface GrssOrderDao {
    int deleteById(String id);

    int insertByGrssOrder(GrssOrder grssOrder);

    GrssOrder selectById(String id);

    int updateByGrssOrder(GrssOrder grssOrder);
    
    Integer selectOrderCountByCoach(@Param("courseIds")List<String> courseIds);
    
    List<GrssOrder> selectOrdersByCourseId(Map<String,Object> params);
    
    List<Map<String,Object>> selectOrderCounts(Map<String,Object> params);
    
    Integer selectCoachOrderCount(@Param("courseId")String courseId);
    
    List<GrssOrder> findGrssOrder(Map<String, Object> pageParam);

	int findGrssOrderCount(Map<String, Object> pageParam);

	void updateMissOrder(@Param("missTime")Date missTime);

	List<GrssOrder> selectOrderByUserId(Map<String, Object> params);

	List<GrssOrder> selectOrderCurrentDate(@Param("minute") int minute);

}