package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssCommunitySnitch;
import com.grss.jlsx.core.dao.GrssCommunitySnitchDao;
import com.grss.jlsx.core.service.GrssCommunitySnitchService;
@Service
public class GrssCommunitySnitchServicceImpl implements GrssCommunitySnitchService {
	@Resource
	private GrssCommunitySnitchDao grssCommunitySnitchDao;
	
	@Override
	public int addGrssCommunitySnitch(GrssCommunitySnitch communitySnitch) {
		return grssCommunitySnitchDao.insertByGrssCommunitySnitch(communitySnitch);
	}

}
