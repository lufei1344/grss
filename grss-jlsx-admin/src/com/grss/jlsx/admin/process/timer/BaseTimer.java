package com.grss.jlsx.admin.process.timer;

import java.util.TimerTask;

import com.grss.jlsx.core.service.GrssAssetsService;
import com.grss.jlsx.core.service.GrssCourseService;
import com.grss.jlsx.core.service.GrssOrderService;
import com.grss.jlsx.core.service.GrssPayService;
import com.grss.jlsx.core.service.GrssRefundService;
import com.grss.jlsx.core.service.GrssSysConfigService;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.utils.SpringUtil;

public abstract class BaseTimer extends TimerTask{
	protected GrssUserService grssUserService = (GrssUserService) SpringUtil.getBean("grssUserService");
	protected GrssSysConfigService grssSysConfigService = (GrssSysConfigService) SpringUtil.getBean("grssSysConfigService");
	protected GrssOrderService grssOrderService = (GrssOrderService) SpringUtil.getBean("grssOrderService");
	protected GrssRefundService grssRefundService = (GrssRefundService) SpringUtil.getBean("grssRefundService");
	protected GrssPayService grssPayService = (GrssPayService) SpringUtil.getBean("grssPayService");
	protected GrssAssetsService grssAssetsService = (GrssAssetsService) SpringUtil.getBean("grssAssetsService");
	protected GrssCourseService grssCourseService = (GrssCourseService) SpringUtil.getBean("grssCourseService");
}
