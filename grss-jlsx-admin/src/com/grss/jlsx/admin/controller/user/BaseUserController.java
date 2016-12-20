package com.grss.jlsx.admin.controller.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.utils.SecurityUtil;

public abstract class BaseUserController extends BaseController{
	@Resource
	protected GrssUserService grssUserService;
	
	public abstract String restPassowrd(HttpServletRequest request,@PathVariable("userId") String userId);
	@RequestMapping("/updatePassword")
	public ModelAndView updatePassword(String userId,String password){
		GrssUser grssUser = new GrssUser();
		grssUser.setUserId(userId);
		grssUser.setPassword(SecurityUtil.md5Hex(password));
		grssUserService.updateGrssUser(grssUser);
		return ajaxDoneSuccess("密码重置成功");
	}
	@RequestMapping("/forbiddenAccount/{userId}")
	public ModelAndView forbiddenAccount(@PathVariable("userId") String userId){
			GrssUser grssUser = new GrssUser();
			grssUser.setUserId(userId);
			grssUser.setStatus(2);
			grssUserService.updateGrssUser(grssUser);
		return ajaxDoneSuccess("封号成功！");
		
	}
	
	protected void setAttr(HttpServletRequest request,String userId){
		GrssUser user = grssUserService.findGrssUserById(userId);
		request.setAttribute("user", user);
	}

}
