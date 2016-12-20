package com.grss.jlsx.core.bean;

public class GrssClubCoach extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 895444689156586725L;

	private Integer id;

    private String clubId;

    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId == null ? null : clubId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}