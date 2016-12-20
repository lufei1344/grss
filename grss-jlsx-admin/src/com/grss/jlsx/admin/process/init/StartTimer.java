package com.grss.jlsx.admin.process.init;

import java.util.Timer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

import com.grss.jlsx.admin.process.timer.CoachAuditTimer;
import com.grss.jlsx.admin.process.timer.OrderTime;
/**
 * 启动定时器
 * @author xuelong
 *
 */
public class StartTimer implements ServletContextAware{
	

	@Override
	public void setServletContext(ServletContext context) {
		init();
	}
	
	private void init(){
		new Timer().schedule(new CoachAuditTimer(), 0, 1000 * 60 * 5);
		new Timer().schedule(new OrderTime(), 0,1000 * 60 * 5);
	}

}
