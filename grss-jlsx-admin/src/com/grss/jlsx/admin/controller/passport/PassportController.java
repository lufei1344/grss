package com.grss.jlsx.admin.controller.passport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.exception.LoginException;
import com.grss.jlsx.admin.process.passport.Passport;
import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.service.GrssAdminUserService;

@Controller
@RequestMapping("/passport")
public class PassportController extends BaseController{
	@Resource
	private GrssAdminUserService grssAdminUserService;
	
	private final static String LOGIN = "../../login";
	
	
	@RequestMapping("/signin")
	public String signin(HttpServletRequest request,String username,String password){
			try {
				Passport passport = Passport.getPassport(request);
				GrssAdminUser adminUser = grssAdminUserService.findGrssAdminUserByPhone(username);
				passport.login(request, adminUser, password);
				return goTo("index");
			} catch (InstantiationException e) {
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", e.getLocalizedMessage());
			} catch (IllegalAccessException e) {
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", e.getLocalizedMessage());
			} catch (LoginException e) {
				request.setAttribute("statusCode", 300);
				request.setAttribute("message", e.getLocalizedMessage());
			}
			return LOGIN;
	}
	@RequestMapping("/signout")
	public String signout(HttpServletRequest request,HttpServletResponse response){
		try {
			Passport passport = Passport.getPassport(request);
			passport.logout(request, response);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return LOGIN;
	}
}
