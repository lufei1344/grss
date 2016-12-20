package com.grss.jlsx.admin.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.process.constants.Constants;
import com.grss.jlsx.admin.process.editor.DateEditor;
import com.grss.jlsx.admin.process.editor.DoubleEditor;
import com.grss.jlsx.admin.process.editor.IntegerEditor;
import com.grss.jlsx.admin.process.editor.LongEditor;
import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.pages.PageUtil;
import com.grss.jlsx.core.pages.Pager;

public abstract class BaseController implements ApplicationContextAware{
	protected static final String PRDFIX = "/admin/";
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		
		binder.registerCustomEditor(int.class, new IntegerEditor());
		binder.registerCustomEditor(long.class, new LongEditor());
		binder.registerCustomEditor(double.class, new DoubleEditor());
		binder.registerCustomEditor(Date.class, new DateEditor());
	}

	protected ModelAndView ajaxDone(int statusCode, String message, String forwardUrl) {
		ModelAndView mav = new ModelAndView("ajaxDone");
		mav.addObject("statusCode", statusCode);
		mav.addObject("message", message);
		mav.addObject("forwardUrl", forwardUrl);
		return mav;
	}
	
	protected ModelAndView ajaxDoneSuccess(String message) {
		return ajaxDone(200, message, "");
	}

	protected ModelAndView ajaxDoneError(String message) {
		return ajaxDone(300, message, "");
	}
	protected String getMessage(String code) {
		return this.getMessage(code, new Object[] {});
	}

	protected String getMessage(String code, Object arg0) {
		return getMessage(code, new Object[] { arg0 });
	}

	protected String getMessage(String code, Object arg0, Object arg1) {
		return getMessage(code, new Object[] { arg0, arg1 });
	}

	protected String getMessage(String code, Object arg0, Object arg1,
			Object arg2) {
		return getMessage(code, new Object[] { arg0, arg1, arg2 });
	}

	protected String getMessage(String code, Object arg0, Object arg1,
			Object arg2, Object arg3) {
		return getMessage(code, new Object[] { arg0, arg1, arg2, arg3 });
	}
	
	protected String goTo(String url){
		return PRDFIX + url;
	}
	protected String goTo(HttpServletRequest request,String url,Pager<?> pager){
		pager.setPageNum(PageUtil.getPageNum());
		pager.setPageSize(PageUtil.getPageSize());
		request.setAttribute("pager", pager);
		return PRDFIX + url;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		
	}
	/**
	 * 获取当前登录用户
	 * @param request
	 * @return
	 */
	protected GrssAdminUser getCurrentLoginUser(HttpServletRequest request){
		return (GrssAdminUser) request.getSession().getAttribute(Constants.SISSION_KEY);
	}
	
}