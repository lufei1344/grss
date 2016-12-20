package com.grss.jlsx.admin.process.timer;

import java.util.Date;
import java.util.List;

import com.grss.jlsx.core.bean.GrssUser;

public class CoachAuditTimer extends BaseTimer{

	@Override
	public void run() {
		List<GrssUser> userList = grssUserService.findGrssUserByApplyCoachDate();
		if(userList != null){
			for(GrssUser user : userList){
				user.setUserType("coach");
				user.setStatus(1);
				user.setRegCoachDate(new Date());
				grssUserService.updateGrssUser(user);
			}
		}
	}

	
}
