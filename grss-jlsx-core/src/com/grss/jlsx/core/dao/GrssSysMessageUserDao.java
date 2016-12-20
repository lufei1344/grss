package com.grss.jlsx.core.dao;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssSysMessageUser;

public interface GrssSysMessageUserDao {

	public void insertByGrssSysMessageUser(GrssSysMessageUser grssSysMessageUser);
	
	public void deleteByGrssSysMessageUser(GrssSysMessageUser grssSysMessageUser);
	
	public void deleteByMessageId(@Param("messageId") String messageId);
}
