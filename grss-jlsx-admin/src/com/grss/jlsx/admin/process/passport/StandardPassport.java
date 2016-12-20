package com.grss.jlsx.admin.process.passport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grss.jlsx.admin.process.constants.Constants;
import com.grss.jlsx.admin.process.exception.LoginException;
import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.utils.SecurityUtil;


public class StandardPassport extends Passport {
	@Override
	public void login(HttpServletRequest request, GrssAdminUser user,
			String password) throws LoginException {
		String inputPassword = SecurityUtil.MD5(password);
		if(user == null){
			throw new LoginException("用户名错误！");
		}
		if(!inputPassword.equals(user.getPassword())){
			throw new LoginException("密码错误！");
		}
		setSessionUser(request, user);
		
	}

	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(Constants.SISSION_KEY);
	}
	
}
