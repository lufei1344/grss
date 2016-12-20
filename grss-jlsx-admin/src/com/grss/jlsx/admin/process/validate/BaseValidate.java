package com.grss.jlsx.admin.process.validate;

import org.quartz.xml.ValidationException;

import com.grss.jlsx.admin.process.exception.LoginException;


public class BaseValidate{

	/**
	 * @throws ValidationException 
	 * 
	 */
	
	public void validate(String message) throws LoginException{
		throw new LoginException(message);
	}
	
	
}
