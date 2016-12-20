package com.grss.jlsx.admin.controller.community;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.vo.CommunityVO;
import com.grss.jlsx.core.bean.GrssCommunity;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssCommunityService;

/**
 * 社区管理
 * @author wang
 *
 */
@Controller
@RequestMapping("/community")
public class CommunityController extends BaseController{
	
	@Resource
	private GrssCommunityService grssCommunityService;
	
	@RequestMapping("/listCommunity")
	public String listCommunity(HttpServletRequest request,CommunityVO communityVO){
		Pager<GrssCommunity> communityList = grssCommunityService.findGrssCommuniry(communityVO.getParams());
		return goTo(request,"community/listCommunity",communityList);
	}

}
