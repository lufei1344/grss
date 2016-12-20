package com.grss.jlsx.admin.controller.order;
import javax.annotation.Resource;
/**
 * 订单管理
 */
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.vo.OrderVO;
import com.grss.jlsx.core.bean.GrssCommunity;
import com.grss.jlsx.core.bean.GrssOrder;
import com.grss.jlsx.core.bean.GrssSysConfig;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssOrderService;
import com.grss.jlsx.core.service.GrssSysConfigService;
@Controller
@RequestMapping("/admin/order")
public class OrderController extends BaseController{
	
	@Resource
	GrssOrderService grssOrderService; 
	
	@Resource
	GrssSysConfigService grssSysConfigService;
	
	@RequestMapping("/list")
	public String listOrder(HttpServletRequest request,OrderVO orderVO){
		Pager<GrssOrder> list = grssOrderService.findGrssOrder(orderVO.getParams());
		return goTo(request,"order/list",list);
	}
	
	@RequestMapping("/setTime/{name}")
	public String setTime(HttpServletRequest request,@PathVariable("name") String name){
		GrssSysConfig sysConfig = grssSysConfigService.findByName(name);
		request.setAttribute("sysConfig", sysConfig);
		return goTo("order/setTime");
	}
	
	
	@RequestMapping("/updateSysConfig")
	public ModelAndView updateSysConfig(HttpServletRequest request,GrssSysConfig grssSysConfig){
		grssSysConfigService.update(grssSysConfig);
		return ajaxDoneSuccess("修改成功");
	}
}
