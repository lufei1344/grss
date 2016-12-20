package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssSysMessage;

public interface GrssSysMessageDao {
    int deleteByPrimaryKey(String id);

    int insertSelective(GrssSysMessage record);

    GrssSysMessage selectByPrimaryKey(String id);

    int updateByGrssSysMessage(GrssSysMessage record);

	List<GrssSysMessage> selectMessagesByUserId(Map<String, Object> params);

	List<GrssSysMessage> selectGrssSysMessageList(Map<String, Object> pageParam);

	int selectGrssSysMessageCount(Map<String, Object> paramsMap);

}