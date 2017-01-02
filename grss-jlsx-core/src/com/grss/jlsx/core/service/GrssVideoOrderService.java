package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssVideoOrder;

public interface GrssVideoOrderService {

	List<GrssVideoOrder> findVideoOrderByOrderId(String orderId);
	List<Map<String,Object>> getOrderVideoCat_v2(String orderId);

	List<GrssVideoOrder> findVideoOrderByOrderIdAndProgramaName(String orderId, String programaName,String version);

	List<GrssVideoOrder> findVideoOrderByOrderIdAndCatName(String catName, String orderId, int level,String version);

	Integer findOrderCountByVideoId(String videoId);

}
