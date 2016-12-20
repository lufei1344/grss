package com.grss.jlsx.core.network.im;

import java.net.URL;

import com.grss.jlsx.core.prove.Credential;



/**
 * 即时通信通道
 * @author XYL
 *
 */
public abstract class IMChannel {
	
	public abstract String createNewIMUserSingle(URL url,Credential credential,String dataBody);
	/**
	 * 删除用户
	 * @param username
	 * @return
	 */
	public abstract String deleteIMUserByuserName(URL url,Credential credential,String username);
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public abstract String imUserLogin(URL url,String username, String password);
	/**
	 * 查找用户
	 * @param username
	 * @return
	 */
	public abstract String getIMUsersByUserName(URL url,Credential credential,String username);
	/**
	 * 重置密码 提供管理员token
	 * @param url
	 * @param credential
	 * @param username
	 * @param newpassword
	 * @return
	 */
	public abstract String modifyIMUserPasswordWithAdminToken(URL url,Credential credential,String username,String newpassword);
	/**
	 * 查看好友
	 * @param ownerUsername
	 * @return
	 */
	public abstract String getFriends(URL url,Credential credential,String ownerUsername);
	/**
	 * 添加好友
	 * @param ownerUserName
	 * @param friendUserName
	 * @return
	 */
	public abstract String addFriendSingle(URL url,Credential credential,String ownerUsername, String friendUsername);
	/**
	 * 删除好友
	 * @param ownerUserName
	 * @param friendUserName
	 * @return
	 */
	public abstract String deleteFriendSingle(URL url,Credential credential,String ownerUsername, String friendUsername);
}
