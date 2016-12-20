package com.grss.jlsx.api.process.constant;

import java.net.URL;

import com.grss.jlsx.api.process.util.URLUtil;


/**
 * 
 * 
 * @author XYL
 *
 */
public interface EndPoints {

	static final URL TOKEN_APP_URL = URLUtil.getURL(Constants.APPKEY.replace("#", "/") + "/token");

	static final URL USERS_URL = URLUtil.getURL(Constants.APPKEY.replace("#", "/") + "/users");

	static final URL MESSAGES_URL = URLUtil.getURL(Constants.APPKEY.replace("#", "/") + "/messages");

	static final URL CHATGROUPS_URL = URLUtil.getURL(Constants.APPKEY.replace("#", "/") + "/chatgroups");

	static final URL CHATFILES_URL = URLUtil.getURL(Constants.APPKEY.replace("#", "/") + "/chatfiles");

}
