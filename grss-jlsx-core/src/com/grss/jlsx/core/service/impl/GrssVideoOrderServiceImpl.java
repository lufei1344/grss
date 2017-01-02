package com.grss.jlsx.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssVideoOrder;
import com.grss.jlsx.core.dao.GrssVideoOrderDao;
import com.grss.jlsx.core.service.GrssVideoOrderService;
@Service
public class GrssVideoOrderServiceImpl implements GrssVideoOrderService {
	@Resource
	private GrssVideoOrderDao grssVideoOrderDao;

	@Override
	public List<GrssVideoOrder> findVideoOrderByOrderId(String orderId) {
		return grssVideoOrderDao.selectVideoOrderByOrderId(orderId);
	}

	@Override
	public List<GrssVideoOrder> findVideoOrderByOrderIdAndProgramaName(String orderId, String programaName,String version) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		params.put("programaName", programaName);
		params.put("version", version);
		return grssVideoOrderDao.selectVideoOrderByOrderIdAndProgramaName(params);
	}

	@Override
	public List<GrssVideoOrder> findVideoOrderByOrderIdAndCatName(String catName, String orderId, int level,String version) {
		Map<String, Object> params = new HashMap<>();
		params.put("catName", catName);
		params.put("orderId", orderId);
		params.put("level", (level + 1));
		params.put("conditionLevel", level);
		params.put("version", version);
		return grssVideoOrderDao.selectVideoOrderByOrderIdAndCatName(params);
	}

	@Override
	public Integer findOrderCountByVideoId(String videoId) {
		return grssVideoOrderDao.selectOrderCountByVideoId(videoId);
	}

	@Override
	public List<Map<String, Object>> getOrderVideoCat_v2(String orderId) {
		Map<String, Object> params = new HashMap<>();
		params.put("orderId", orderId);
		return grssVideoOrderDao.getOrderVideoCat_v2(params);
	}

}
