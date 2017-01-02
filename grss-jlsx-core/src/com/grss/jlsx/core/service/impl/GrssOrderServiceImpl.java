package com.grss.jlsx.core.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssOrder;
import com.grss.jlsx.core.bean.GrssPosts;
import com.grss.jlsx.core.bean.GrssVideoOrder;
import com.grss.jlsx.core.dao.GrssOrderDao;
import com.grss.jlsx.core.dao.GrssVideoCatProgramaDao;
import com.grss.jlsx.core.dao.GrssVideoCategoryDao;
import com.grss.jlsx.core.dao.GrssVideoOrderDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssOrderService;
import com.grss.jlsx.core.utils.StringUtil;

@Service("grssOrderService")
public class GrssOrderServiceImpl extends BaseService implements GrssOrderService {
	@Resource
	private GrssOrderDao grssOrderDao;
	@Resource
	private GrssVideoCategoryDao grssVideoCategoryDao;
	@Resource
	private GrssVideoCatProgramaDao grssVideoCatProgramaDao;
	@Resource
	private GrssVideoOrderDao grssVideoOrderDao;
	@Override
	public int deleteById(String id) {
		return grssOrderDao.deleteById(id);
	}

	@Override
	public int addByGrssOrder(GrssOrder grssOrder) {
		return grssOrderDao.insertByGrssOrder(grssOrder);
	}

	@Override
	public GrssOrder findById(String id) {
		return grssOrderDao.selectById(id);
	}

	@Override
	public int updateByGrssOrder(GrssOrder grssOrder) {
		return grssOrderDao.updateByGrssOrder(grssOrder);
	}

	@Override
	public Integer findOrderCountByCoach(List<String> courseIds) {
		return grssOrderDao.selectOrderCountByCoach(courseIds);
	}

	@Override
	public List<GrssOrder> findOrdersByCourseId(Map<String, Object> params) {
		return grssOrderDao.selectOrdersByCourseId(getPageParam(params));
	}

	@Override
	public List<Map<String, Object>> findOrderCounts(Map<String, Object> params) {
		return grssOrderDao.selectOrderCounts(params);
	}

	@Override
	public void addOrderResult(String orderId, String vcIds) {
		String [] vcIdArray=vcIds.split(";");
		for (String vcIdStr : vcIdArray) {
			if(vcIdStr.contains(",")){
				String []vcId=vcIdStr.split(",");
				String programaName=vcId[0];
				String catName1=vcId[1];
				String catName2=vcId[2];
				String catName3=vcId[3];
				String vidoId=vcId[4];
				GrssVideoOrder grssVideoOrder=new GrssVideoOrder();
				grssVideoOrder.setId(StringUtil.getUUID());
				grssVideoOrder.setOrderId(orderId);
				grssVideoOrder.setVidoId(vidoId);
				grssVideoOrder.setProgramaName(programaName);
				grssVideoOrder.setVidoCatName1(catName1);
				grssVideoOrder.setVidoCatName2(catName2);
				grssVideoOrder.setVidoCatName3(catName3);
				grssVideoOrderDao.insertByGrssVideoOrder(grssVideoOrder);
			}
		}
	}
	
	@Override
	public void addOrderResult_V2(String orderId, String vcIds) {
		Map<String,Integer> programOrderSortMap=new HashMap<>();
		programOrderSortMap.put("热身", 1);
		programOrderSortMap.put("力量训练", 2);
		programOrderSortMap.put("柔韧伸展", 3);
		programOrderSortMap.put("有氧训练", 4);
		String [] vcIdArray=vcIds.split(";");
		for (String vcIdStr : vcIdArray) {
			if(vcIdStr.contains(",")){
				String []vcId=vcIdStr.split(",");
				String programaName=vcId[0];
				String catId1=vcId[1];
				String catId2=vcId[2];
				String catId3=vcId[3];
				String vidoId=vcId[4];
				Integer orderSort=programOrderSortMap.get(programaName);
				GrssVideoOrder grssVideoOrder=new GrssVideoOrder();
				grssVideoOrder.setId(StringUtil.getUUID());
				grssVideoOrder.setOrderId(orderId);
				grssVideoOrder.setVidoId(vidoId);
				grssVideoOrder.setProgramaName(programaName);
				grssVideoOrder.setVidoCatName1(catId1);
				grssVideoOrder.setVidoCatName2(catId2);
				grssVideoOrder.setVidoCatName3(catId3);
				grssVideoOrder.setOrderSort(orderSort);
				grssVideoOrderDao.insertByGrssVideoOrder(grssVideoOrder);
			}
		}
	}
	
	@Override
	public Integer findCoachOrderCount(String courseId){
		return grssOrderDao.selectCoachOrderCount(courseId);
	}

	@Override
	public Pager<GrssOrder> findGrssOrder(Map<String, Object> params) {
		List<GrssOrder> list = grssOrderDao.findGrssOrder(getPageParam(params));
		int total = grssOrderDao.findGrssOrderCount(getPageParam(params));
		Pager<GrssOrder> pager = new Pager<>(total,list);
		return pager;
	}

	@Override
	public void updateMissOrder(Date missTime) {
		grssOrderDao.updateMissOrder(missTime);
	}


	@Override
	public List<GrssOrder> findOrderByUserId(String userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		return grssOrderDao.selectOrderByUserId(params);
	}

	@Override
	public List<GrssOrder> findOrderCurrentDate(int minute) {
		return grssOrderDao.selectOrderCurrentDate(minute);
	}

	@Override
	public Integer findCoachOrderCount_v2(String courseId) {
		return grssOrderDao.selectCoachOrderCount_v2(courseId);
	}

}
