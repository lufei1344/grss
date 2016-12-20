package com.grss.jlsx.core.bean;

import java.util.Date;

public class GrssAppDeployVersion extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 614229659876450142L;
	
	private int deployId;
	
	private String deployType;
	
	private int deployVersion;
	
	private String deployComment;
	
	private String deployUrl;
	
	private int appType;
	
	private Date depolyTime;

	public int getDeployId() {
		return deployId;
	}

	public void setDeployId(int deployId) {
		this.deployId = deployId;
	}

	public String getDeployType() {
		return deployType;
	}

	public void setDeployType(String deployType) {
		this.deployType = deployType;
	}


	public int getDeployVersion() {
		return deployVersion;
	}

	public void setDeployVersion(int deployVersion) {
		this.deployVersion = deployVersion;
	}

	public String getDeployComment() {
		return deployComment;
	}

	public void setDeployComment(String deployComment) {
		this.deployComment = deployComment;
	}

	public String getDeployUrl() {
		return deployUrl;
	}

	public void setDeployUrl(String deployUrl) {
		this.deployUrl = deployUrl;
	}

	public Date getDepolyTime() {
		return depolyTime;
	}

	public void setDepolyTime(Date depolyTime) {
		this.depolyTime = depolyTime;
	}

	public int getAppType() {
		return appType;
	}

	public void setAppType(int appType) {
		this.appType = appType;
	}
	
	

}
