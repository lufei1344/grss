package com.grss.jlsx.api.controller.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grss.jlsx.api.controller.BaseController;
import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.bean.GrssAppDeployVersion;
import com.grss.jlsx.core.bean.GrssHomeInfo;
import com.grss.jlsx.core.enums.HomeType;
import com.grss.jlsx.core.service.GrssAppDeployVersionService;
import com.grss.jlsx.core.service.GrssHomeInfoService;
import com.grss.jlsx.core.utils.StringUtil;

@Controller
@RequestMapping("/api")
public class HomeController extends BaseController{
	@Resource
	private GrssHomeInfoService grssHomeInfoService;
	@Resource
	private GrssAppDeployVersionService grssAppDeployVersionService;
	
	/**
	 * 首页
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "homePage",method=RequestMethod.POST)
	public ResultDataTO homePage(){
		
		List<GrssHomeInfo> carouselList = grssHomeInfoService.findGrssHomeInfoByType(new HomeType[]{HomeType.CAROUSEL});
		HomeType[] homeTypes = new HomeType[]{HomeType.CLUB,HomeType.COACH,HomeType.COMPETITION};
		List<GrssHomeInfo> etcList =grssHomeInfoService.findGrssHomeInfoByType(homeTypes);
		Map<String, Object> homeResult = new HashMap<>();
		homeResult.put(HomeType.CAROUSEL.name().toLowerCase(), carouselList);
		if(etcList != null && etcList.size() > 0){
			for(GrssHomeInfo homeInfo : etcList){
				homeResult.put(homeInfo.getType().toLowerCase(), homeInfo);
			}
		}else{
			for (HomeType type : homeTypes) {
				homeResult.put(type.name().toLowerCase(), null);
			}
		}
		
		return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), homeResult);
	}
	
	/**
	 * 获取app更新版本号
	 * @param appType 0用户版 1教练版
	 * @return
	 */
	@RequestMapping(value = "getAppVersion",method=RequestMethod.POST)
	public @ResponseBody ResultDataTO getAppVersion(String appType){
		if(!StringUtil.empty(appType)){
			GrssAppDeployVersion grssAppDeployVersion = grssAppDeployVersionService.findGrssAppDeployVersionByAppType(Integer.parseInt(StringUtil.trim(appType)));
			Map<String, Object> result = new HashMap<>();
			result.put("version", grssAppDeployVersion.getDeployVersion());
			result.put("url", grssAppDeployVersion.getDeployUrl());
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), result);
		}
		return resultFaild("[appType]类型不能为空");
		
	}
	
}
