package com.grss.jlsx.admin.process.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.grss.jlsx.admin.process.el.ServerInfo;
import com.grss.jlsx.admin.process.validate.SessionValidate;

public class SossionFilter implements Filter {
	private static final String DEFAULT = "/login.jsp";
	/**
	 * 需要排除的页面
	 */
	private String excludedPages;
	private String[] excludedPageArray;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		SessionValidate loginValidate = SessionValidate.getInstance();
		loginValidate.init(request.getSession());
		boolean isExcludedPage = false;
		for (String page : excludedPageArray) {// 判断是否在过滤url之外
			if (request.getServletPath().equals(page)) {
				isExcludedPage = true;
				break;
			}
		}
		if (isExcludedPage){
			chain.doFilter(request, response);
			return;
		}

		if (loginValidate.sessionValidate()) {
			if (ServerInfo.isAjax(request) || request.getParameter("ajax") != null) {
				PrintWriter out = response.getWriter();
				out.println("{\"statusCode\":\"301\", \"message\":\"会话超时\"}");
			} else {
				response.sendRedirect(request.getContextPath() + DEFAULT);
			}
			return;
			
		} else {
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		excludedPages = config.getInitParameter("excludedPages");
		if (StringUtils.isNotEmpty(excludedPages)) {
			excludedPageArray = excludedPages.split(",");
		}
		return;
	}

}
