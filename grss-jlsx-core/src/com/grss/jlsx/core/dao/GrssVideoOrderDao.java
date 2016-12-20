package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssVideoOrder;

public interface GrssVideoOrderDao {
    int deleteById(String id);

    int insertByGrssVideoOrder(GrssVideoOrder grssVideoOrder);

    GrssVideoOrder selectById(String id);

    int updateByGrssVideoOrder(GrssVideoOrder grssVideoOrder);

	List<GrssVideoOrder> selectVideoOrderByOrderId(@Param("orderId") String orderId);

	List<GrssVideoOrder> selectVideoOrderByOrderIdAndProgramaName(Map<String, Object> params);

	List<GrssVideoOrder> selectVideoOrderByOrderIdAndCatName(Map<String, Object> params);

	Integer selectOrderCountByVideoId(@Param("videoId")String videoId);

}