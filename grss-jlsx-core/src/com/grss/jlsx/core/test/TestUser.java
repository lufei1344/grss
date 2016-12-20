package com.grss.jlsx.core.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.grss.jlsx.core.service.GrssUserService;

public class TestUser {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml","classpath:spring-mybatis.xml"});
	  GrssUserService grssUserService =  (GrssUserService) applicationContext.getBean("grssUserService");
	  System.out.println(grssUserService.findGrssUserByUserId("123").getUserId());
	}
}
