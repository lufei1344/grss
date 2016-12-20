package com.grss.jlsx.api.process.spring;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext implements ApplicationContextAware{
	protected Log log = LogFactory.getLog(this.getClass());
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		
	}

}
