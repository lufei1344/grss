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
	@RequestMapping("/getOrderVideoCat_v2")
	public @ResponseBody ResultDataTO getOrderVideoCat_v2(@RequestParam(value = "orderId",required = false) String orderId,@RequestParam(value = "version",required = false) String version){
		List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> list = grssVideoOrderService.getOrderVideoCat_v2(orderId);
		List<String> level1 = new ArrayList<String>();
		Map<String,List<Map<String,Object>>> level2 = new HashMap<String,List<Map<String,Object>>>();
		Map<String,List<Map<String,Object>>> level3 = new HashMap<String,List<Map<String,Object>>>();
		Map<String,List<Map<String,Object>>> vido = new HashMap<String,List<Map<String,Object>>>();
		/*//diyi
		for(Map<String,Object> m : list){
			if(!level1.contains(m.get("catname1"))){
				level1.add(m.get("catname1").toString());
			}
			//二级
			if( level2.get(m.get("key1")) == null){
				level2.put(m.get("key1").toString(), new ArrayList<Map<String,Object>>());
				Map<String,Object> vv = new HashMap<String,Object>();
				vv.put("name", m.get("catname2"));
				vv.put("type", "sub");
				level2.get(m.get("key1")).add(vv);
			}else{
				boolean ex = false;
				for(Map<String,Object> mm : level2.get(m.get("key1"))){
					if( m.get("catname2").equals(mm.get("name"))){
						ex = true;
					}
				}
				if(!ex){
					Map<String,Object> vv = new HashMap<String,Object>();
					vv.put("name", m.get("catname2"));
					vv.put("type", "sub");
					level2.get(m.get("key1")).add(vv);
				}
			}
			
			//三级
			if( level3.get(m.get("key2")) == null){
				level3.put(m.get("key2").toString(), new ArrayList<Map<String,Object>>());
				Map<String,Object> vvv = new HashMap<String,Object>();
				vvv.put("name", m.get("catname3"));
				vvv.put("type", "sub");
				level3.get(m.get("key2")).add(vvv);
			}else{
				boolean ex = false;
				for(Map<String,Object> mm : level3.get(m.get("key2"))){
					if( m.get("catname3").equals(mm.get("name"))){
						ex = true;
					}
				}
				if(!ex){
					Map<String,Object> vvv = new HashMap<String,Object>();
					vvv.put("name", m.get("catname3"));
					vvv.put("type", "sub");
					level3.get(m.get("key2")).add(vvv);
				}
			}
			
			
			//视频
			if( vido.get(m.get("key3")) == null){
				vido.put(m.get("key3").toString(), new ArrayList<Map<String,Object>>());
			}
			Map<String,Object> v = new HashMap<String,Object>();
			v.put("name", m.get("name"));
			v.put("url", m.get("url"));
			v.put("type", "sub");
			vido.get(m.get("key3")).add(v);
		}
		
		for(String s : level1){
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("name", s);
			m.put("type","parent");
			m.put("subnode",level2.get(s));
			for(Map<String,Object> vv : level2.get(s) ){
				String key = s+vv.get("name");
				vv.put("subnode", level3.get(key));
				for(Map<String,Object> vvv : level3.get(key)){
					String keyy = key+vvv.get("name");
					vvv.put("subnode", vido.get(keyy));
				}
			}
			listResult.add(m);
		}*/
		
		
		for(Map<String,Object> m : list){
			if(!level1.contains(m.get("catname1"))){
				level1.add(m.get("catname1").toString());
			}
		}
			
		for(String s : level1){
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("name", s);
			m.put("type","parent");
			List<Map<String,Object>> sub = new ArrayList<Map<String,Object>>();
			m.put("subnode",sub);
			for(Map<String,Object> mm : list){
				if(mm.get("catname1").equals(s)){
					Map<String,Object> v = new HashMap<String,Object>();
					v.put("name", mm.get("name"));
					v.put("url", mm.get("url"));
					m.put("type","sub");
					sub.add(v);
				}
			}
			listResult.add(m);
		}
		
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), listResult);
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
