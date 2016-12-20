package com.grss.jlsx.admin.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grss.jlsx.admin.process.vo.CoachVO;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.pages.Pager;
@Controller
@RequestMapping("/admin/coach")
public class CoachController extends BaseUserController{
	
	@RequestMapping("/listCoach")
	public String listCoach(HttpServletRequest request,CoachVO coachVO){
		Pager<GrssUser> pager = grssUserService.findGrssUserList(coachVO.getParams());
		return goTo(request,"coach/listCoach",pager);
	}
	@RequestMapping("/restPassowrd/{userId}")
	public String restPassowrd(HttpServletRequest request, @PathVariable("userId") String userId) {
		setAttr(request, userId);
		return goTo("coach/restPassword");
	}
}
