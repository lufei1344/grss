package com.grss.jlsx.api.process.util;

import java.net.MalformedURLException;
import java.net.URL;

import com.grss.jlsx.api.process.constant.Constants;

public class URLUtil {
	public static URL getURL(String path) {
		URL url = null;

		try {
			url = new URL(Constants.API_HTTP_SCHEMA, Constants.API_SERVER_HOST, "/" + path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return url;
	}
}
