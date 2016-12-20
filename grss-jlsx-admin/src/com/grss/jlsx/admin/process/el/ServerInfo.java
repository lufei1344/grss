package com.grss.jlsx.admin.process.el;

import javax.servlet.http.HttpServletRequest;

public class ServerInfo {
	public static boolean isAjax(HttpServletRequest request) {
		if (request != null
				&& "XMLHttpRequest".equalsIgnoreCase(request
						.getHeader("X-Requested-With")))
			return true;
		return false;
	}
}
