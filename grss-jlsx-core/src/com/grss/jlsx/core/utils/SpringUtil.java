package com.grss.jlsx.core.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtil {
	/**
	 * 获取spring上下文对象
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId){
		ApplicationContext resouce = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml","classpath:spring-mybatis.xml"});
		Object obj = resouce.getBean(beanId);
		return obj;
	}
}
