package com.grss.jlsx.core.bean;

public class GrssAdminClub extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3873163612528001793L;

	private String adminUserId;

    private String clubId;

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId == null ? null : adminUserId.trim();
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId == null ? null : clubId.trim();
    }
}