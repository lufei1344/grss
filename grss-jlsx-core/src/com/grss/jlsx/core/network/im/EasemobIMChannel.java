package com.grss.jlsx.core.network.im;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.grss.jlsx.core.enums.HTTPMethod;
import com.grss.jlsx.core.prove.ClientSecretCredential;
import com.grss.jlsx.core.prove.Credential;
import com.grss.jlsx.core.utils.RestClientUtil;
import com.grss.jlsx.core.utils.StringUtil;

public class EasemobIMChannel extends IMChannel{

	@Override
	public String createNewIMUserSingle(URL url,Credential credential,String dataBody) {
		String text = RestClientUtil.sendHTTPRequest(url, credential, dataBody, HTTPMethod.POST);
		return text;
	}

	@Override
	public String deleteIMUserByuserName(URL url,Credential credential,String username) {
		String text = RestClientUtil.sendHTTPRequest(dealURL(url, new String[]{username}), credential, null, HTTPMethod.DELETE);
		return text;
	}

	@Override
	public String imUserLogin(URL url,String username, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "password");
		params.put("username", username);
		params.put("password", password);
		String text = RestClientUtil.sendHTTPRequest(url, null, JSON.toJSON(params), HTTPMethod.POST);
		return text;
	}

	@Override
	public String getIMUsersByUserName(URL url,Credential credential,String username) {
		String text = RestClientUtil.sendHTTPRequest(dealURL(url, new String[]{username}), credential, null, HTTPMethod.GET);
		return text;
	}

	@Override
	public String modifyIMUserPasswordWithAdminToken(URL url,Credential credential,String username,String newpassword) {
		Map<String, String> params = new HashMap<>();
		params.put("newpassword", newpassword);
		String text = RestClientUtil.sendHTTPRequest(dealURL(url, new String[]{username,"password"}), credential, JSON.toJSON(params), HTTPMethod.PUT);
		return text;
	}

	@Override
	public String getFriends(URL url,Credential credential,String ownerUsername) {
		String text = RestClientUtil.sendHTTPRequest(dealURL(url, new String[]{ownerUsername,"contacts/users"}), credential, null, HTTPMethod.GET);
		return text;
	}

	@Override
	public String addFriendSingle(URL url,Credential credential,String ownerUsername, String friendUsername) {
		String text = RestClientUtil.sendHTTPRequest(dealURL(url, new String[]{ownerUsername,"contacts/users",friendUsername}), credential, null, HTTPMethod.POST);
		return text;
	}

	@Override
	public String deleteFriendSingle(URL url,Credential credential,String ownerUsername, String friendUsername) {
		String text = RestClientUtil.sendHTTPRequest(dealURL(url, new String[]{ownerUsername,"contacts/users",friendUsername}), credential, null, HTTPMethod.DELETE);
		return text;
	}
	private  static URL dealURL(URL url,String[] values){
		StringBuilder builder = new StringBuilder(url.toString());
		if(!StringUtil.empty(values)){
			for(String value : values){
				builder.append("/" + value);
			}
		}
		try {
			return new URL(builder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		try {
			IMChannel channel = new EasemobIMChannel();
			Credential credential = new ClientSecretCredential("YXA6GrRqIMQFEeWywYeS9UE0-w","YXA6e90l8KYRcpCttXg-Qz-EwpCg0Xk", "bug21121", new URL("https://a1.easemob.com/gongxin/accompany/token"));
			/*channel.imUserLogin(new URL("https://a1.easemob.com/gongxin/accompany/token"), "9c18c66d6b70442c94054986c572e7a8", "e10adc3949ba59abbe56e057f20f883e");*/
			//System.out.println(channel.modifyIMUserPasswordWithAdminToken(new URL("https://a1.easemob.com/gongxin/accompany/users"), credential, "37854421bc0140fa914e5c8e05808ccd", "c33367701511b4f6020ec61ded352059"));
			channel.getFriends(new URL("https://a1.easemob.com/gongxin/accompany/users"), credential, "1b25ce3188884399936859ea2267d8e4");
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
