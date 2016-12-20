package com.grss.jlsx.admin.process.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.grss.jlsx.core.pages.SystemContext;
import com.grss.jlsx.core.utils.StringUtil;

public class PageFilter implements Filter {

	@Override
	public void destroy() {
		
	}
	/**
	 * 设置分页参数
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String pageNum = request.getParameter("pageNum");
		String pageSieze = request.getParameter("pageSize");
		SystemContext.setPageNum(!StringUtil.empty(pageNum) ? Integer.parseInt(pageNum) : 0);
		SystemContext.setPageSize(!StringUtil.empty(pageSieze) ? Integer.parseInt(pageSieze) : 10);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
