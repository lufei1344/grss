package com.grss.jlsx.api.process.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityAnnotation {
	/**
	 * 
	 * @comment 是否允许用户在未登录的情况下访问（默认是允许）
	 * @return
	 * 2015年12月16日
	 * 下午4:03:45
	 */
	public boolean isApprove() default true;
}
