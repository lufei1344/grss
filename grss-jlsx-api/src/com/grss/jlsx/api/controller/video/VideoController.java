package com.grss.jlsx.api.controller.video;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grss.jlsx.api.controller.BaseController;
import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.bean.GrssVideo;
import com.grss.jlsx.core.bean.GrssVideoCatPrograma;
import com.grss.jlsx.core.bean.GrssVideoCategory;
import com.grss.jlsx.core.bean.GrssVideoOrder;
import com.grss.jlsx.core.service.GrssVideoCatProgramaService;
import com.grss.jlsx.core.service.GrssVideoCategoryService;
import com.grss.jlsx.core.service.GrssVideoOrderService;
import com.grss.jlsx.core.service.GrssVideoService;
import com.grss.jlsx.core.utils.StringUtil;

@Controller
@RequestMapping("/api")
public class VideoController extends BaseController{
	@Resource
	private GrssVideoCategoryService grssVideoCategoryService;
	@Resource
	private GrssVideoService grssVideoService;
	@Resource
	private GrssVideoCatProgramaService grssVideoCatProgramaService;
	@Resource
	private GrssVideoOrderService grssVideoOrderService;
	/**
	 * 获取视频一级分类
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getParentCategoryList",method = RequestMethod.POST)
	public ResultDataTO getParentCategoryList(){
		List<GrssVideoCatPrograma> programaList = grssVideoCatProgramaService.findGrssVideoCatProgramaAll();
		LinkedHashMap<String, Object> data = new LinkedHashMap<>();
		List<LinkedHashMap<String, Object>> result = new ArrayList<>();
		for(GrssVideoCatPrograma programa : programaList){
			List<GrssVideoCategory> videoCategoryList = grssVideoCategoryService.findParentCategoryList(programa.getId());
			data.put(programa.getName(), videoCategoryList);
		}
		result.add(data);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), result);
	}
	/**
	 * 获取子集视频分类
	 * @param parentId 视频分类ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSubCategoryList",method = RequestMethod.POST)
	public ResultDataTO getSubCategoryList(@RequestParam(value = "parentId",required = false) String parentId){
		List<GrssVideoCategory> videoCategoryList = grssVideoCategoryService.findCategoryByParentIdList(parentId);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), videoCategoryList);
	}
	/**
	 * 获取视频列表
	 * @param catId 视频分类ID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getVideoList",method = RequestMethod.POST)
	public ResultDataTO getVideoList(@RequestParam(value = "catId",required = false) String catId){
		List<GrssVideo> videoList = grssVideoService.findGrssVideoByCatIdList(catId);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), videoList);
	}
	/**
	 * 根据用户订单号查找视频分类
	 * @param orderId
	 * @param version V2
	 * @return
	 */
	@RequestMapping("/getOrderVideoCat")
	public @ResponseBody ResultDataTO getOrderVideoCat(@RequestParam(value = "orderId",required = false) String orderId,@RequestParam(value = "version",required = false) String version){
		if(StringUtil.empty(version)){
			version = "V1";
		}
		List<GrssVideoOrder> videoOrderList = grssVideoOrderService.findVideoOrderByOrderId(orderId);
		List<Map<String, Object>> listResult = new ArrayList<>();
		for(GrssVideoOrder videoOrder : videoOrderList){
			Map<String, Object> videoOrderMap = new HashMap<>();
			List<GrssVideoOrder> videoOrderSubList = grssVideoOrderService.findVideoOrderByOrderIdAndProgramaName(videoOrder.getOrderId(),videoOrder.getProgramaName(),version);
			videoOrderMap.put("orderId", videoOrder.getOrderId());
			videoOrderMap.put("programaName", videoOrder.getProgramaName());
			videoOrderMap.put("videoCat1", videoOrderSubList);
			listResult.add(videoOrderMap);
		}
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), listResult);
	}
	/**
	 * 获取订单视频子分类（调用两次）
	 * @param orderId 订单ID
	 * @param catName 分类名称
	 * @param level 当前调用了多少次
	 * @param version V2
	 * @return
	 */
	@RequestMapping("/getOrderVideoSubCat")
	public @ResponseBody ResultDataTO getOrderVideoSubCat(@RequestParam(value = "orderId",required = false) String orderId,@RequestParam(value = "catName",required = false) String catName,@RequestParam(value = "level",required = false) String level,@RequestParam(value = "version",required = false) String version){
		if(StringUtil.empty(version)){
			version = "V1";
		}
		int levelInt = Integer.parseInt(StringUtil.trim(level));
		if(levelInt >= 3){
			return resultFaild("已经没有分类了");
		}
		List<GrssVideoOrder> videoOrderSubList = grssVideoOrderService.findVideoOrderByOrderIdAndCatName(catName,orderId,levelInt,version);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), videoOrderSubList);
	}
	/**
	 * 获取订单视频
	 * @param orderId 订单ID
	 * @param catName_level3 必须是第三级名称
	 * @return
	 */
	@RequestMapping("/getOrderVideo")
	public @ResponseBody ResultDataTO getOrderVideo(@RequestParam(value = "orderId",required = false) String orderId,@RequestParam(value = "catName_level3",required = false) String catName_level3){
		List<GrssVideo> videoList = grssVideoService.findGrssVideoByCatNameAndOrderId(orderId,catName_level3);
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), videoList);
	}
}
