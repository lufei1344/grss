package com.grss.jlsx.admin.process.validate;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.grss.jlsx.admin.process.constants.Constants;

public class SessionValidate extends BaseValidate {
	private static final Log log = LogFactory.getLog(SessionValidate.class);
	
	private static ThreadLocal<SessionValidate> validatorHolder = new ThreadLocal<SessionValidate>() {

		@Override
		protected SessionValidate initialValue() {
			return new SessionValidate();
		}

	};
	public static SessionValidate getInstance() {
		return validatorHolder.get();
	}
	private HttpSession session;
	public void init(HttpSession session) {
			this.session = session;
	}

	public boolean sessionValidate() {

		if (session == null) {
			log.warn("session 为空");
			return true;
		} else if (session.getAttribute(Constants.SISSION_KEY) == null) {
			log.warn("session 中不存在用户");
			return true;
		}
		return false;
	}

	
	
}
