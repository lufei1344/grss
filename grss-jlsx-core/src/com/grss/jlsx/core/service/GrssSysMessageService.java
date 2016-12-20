package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssSysMessage;
import com.grss.jlsx.core.pages.Pager;

public interface GrssSysMessageService {
    int deleteByGrssSysMessage(String id);

    int addGrssSysMessage(GrssSysMessage record);

    GrssSysMessage findGrssSysMessageById(String id);

    int updateByGrssSysMessage(GrssSysMessage record);
    
    List<GrssSysMessage> findMessagesByUserId(String userId);

	Pager<GrssSysMessage> findGrssSysMessageList(Map<String, Object> paramsMap);

}