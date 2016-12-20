package com.grss.jlsx.core.po;

import com.grss.jlsx.core.bean.GrssClub;
import com.grss.jlsx.core.bean.GrssCourse;
import com.grss.jlsx.core.bean.GrssUser;

public class GrssUserPo extends GrssUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 869746916554741195L;
	
	
	private GrssClub grssClub; //教练所属场馆
	private GrssCourse grssCourse;//教练课程
	
	public GrssClub getGrssClub() {
		return grssClub==null?new GrssClub():grssClub;
	}
	
	public void setGrssClub(GrssClub grssClub) {
		this.grssClub = grssClub;
	}
	
	public GrssCourse getGrssCourse() {
		return grssCourse==null?new GrssCourse():grssCourse;
	}
	
	public void setGrssCourse(GrssCourse grssCourse) {
		this.grssCourse = grssCourse;
	}

}
