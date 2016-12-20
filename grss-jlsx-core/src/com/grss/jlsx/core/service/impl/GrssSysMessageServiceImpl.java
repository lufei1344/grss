package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssSysMessage;
import com.grss.jlsx.core.bean.GrssSysMessageUser;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.dao.GrssSysMessageDao;
import com.grss.jlsx.core.dao.GrssSysMessageUserDao;
import com.grss.jlsx.core.dao.GrssUserDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssSysMessageService;
@Service
public class GrssSysMessageServiceImpl extends BaseService implements GrssSysMessageService {
	@Resource
	private GrssSysMessageDao grssSysMessageDao;
	@Resource
	private GrssUserDao grssUserDao;
	@Resource
	private GrssSysMessageUserDao grssSysMessageUserDao;
	@Override
	public int deleteByGrssSysMessage(String id) {
		grssSysMessageUserDao.deleteByMessageId(id);
		return grssSysMessageDao.deleteByPrimaryKey(id);
	}

	@Override
	public int addGrssSysMessage(GrssSysMessage record) {
		List<GrssUser> userList = grssUserDao.selectGrssUserAll();
		for(GrssUser u : userList){
			GrssSysMessageUser grssSysMessageUser = new GrssSysMessageUser();
			grssSysMessageUser.setMessageId(record.getId());
			grssSysMessageUser.setUserId(u.getUserId());
			grssSysMessageUserDao.insertByGrssSysMessageUser(grssSysMessageUser);
		}
		return grssSysMessageDao.insertSelective(record);
	}

	@Override
	public GrssSysMessage findGrssSysMessageById(String id) {
		return grssSysMessageDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByGrssSysMessage(GrssSysMessage record) {
		return grssSysMessageDao.updateByGrssSysMessage(record);
	}

	@Override
	public List<GrssSysMessage> findMessagesByUserId(String userId) {
		Map<String,Object> params=getPageParam();
		params.put("userId",userId);
		return grssSysMessageDao.selectMessagesByUserId(params);
	}

	@Override
	public Pager<GrssSysMessage> findGrssSysMessageList(Map<String, Object> paramsMap) {
		List<GrssSysMessage> messageList = grssSysMessageDao.selectGrssSysMessageList(getPageParam(paramsMap));
		int total = grssSysMessageDao.selectGrssSysMessageCount(paramsMap);
		return new Pager<>(total, messageList);
	}

}
