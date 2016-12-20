package com.grss.jlsx.admin.process.passport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grss.jlsx.admin.process.constants.Constants;
import com.grss.jlsx.admin.process.exception.LoginException;
import com.grss.jlsx.core.bean.GrssAdminUser;


public abstract class Passport {

	private static Passport passport = null;

	private static final Object lock = new Object();

	public static Passport getPassport(HttpServletRequest request)
			throws InstantiationException, IllegalAccessException {
		if (passport == null) {
			synchronized (lock) {
				if (passport == null) {
					passport = StandardPassport.class.newInstance();
				}
			}
		}
		return passport;
	}

	public abstract void login(HttpServletRequest request, GrssAdminUser user,
			String password) throws LoginException;

	
	public abstract void logout(HttpServletRequest request,
			HttpServletResponse response);

	public static void setSessionUser(HttpServletRequest request, GrssAdminUser user) {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.SISSION_KEY, user);
	}
}