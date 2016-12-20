package com.grss.jlsx.core.bean;

import java.util.Date;

public class GrssCommunitySnitch extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3172711752610862660L;

	private String id;

    private String snitchPersonId;

    private String communityId;

    private Date snitchTime;

    private String snitchContent;
    

    public GrssCommunitySnitch(String id, String snitchPersonId, String communityId, Date snitchTime,
			String snitchContent) {
		super();
		this.id = id;
		this.snitchPersonId = snitchPersonId;
		this.communityId = communityId;
		this.snitchTime = snitchTime;
		this.snitchContent = snitchContent;
	}
    
    

	public GrssCommunitySnitch(String id, String snitchPersonId, String communityId, Date snitchTime) {
		super();
		this.id = id;
		this.snitchPersonId = snitchPersonId;
		this.communityId = communityId;
		this.snitchTime = snitchTime;
	}

	

	public GrssCommunitySnitch() {
		super();
	}



	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSnitchPersonId() {
        return snitchPersonId;
    }

    public void setSnitchPersonId(String snitchPersonId) {
        this.snitchPersonId = snitchPersonId == null ? null : snitchPersonId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public Date getSnitchTime() {
        return snitchTime;
    }

    public void setSnitchTime(Date snitchTime) {
        this.snitchTime = snitchTime;
    }

    public String getSnitchContent() {
        return snitchContent;
    }

    public void setSnitchContent(String snitchContent) {
        this.snitchContent = snitchContent == null ? null : snitchContent.trim();
    }
}