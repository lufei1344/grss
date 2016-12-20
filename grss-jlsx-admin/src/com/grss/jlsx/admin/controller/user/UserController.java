package com.grss.jlsx.admin.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.utils.StringUtil;
@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseUserController{
	
	
	@RequestMapping("/listUser")
	public String listUser(HttpServletRequest request,GrssUser grssUser){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userType", "user");
		if(grssUser != null){
			if(!StringUtil.empty(grssUser.getUserPhone())){
				params.put("phone", grssUser.getUserPhone());
			}
		}
		Pager<GrssUser> page = grssUserService.findGrssUserList(params);
		return goTo(request,"/user/listUser",page);
	}
	@RequestMapping("/restPassowrd/{userId}")
	public String restPassowrd(HttpServletRequest request, @PathVariable("userId") String userId) {
		setAttr(request, userId);
		return goTo("user/restPassword");
	}
}
