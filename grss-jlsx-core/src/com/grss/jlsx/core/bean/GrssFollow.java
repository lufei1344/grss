package com.grss.jlsx.core.bean;

public class GrssFollow extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8250746405580252762L;

	private String beNoticedId;

    private String fansId;

    private Integer followRelationship;//用户关注状态
    
    public String getBeNoticedId() {
        return beNoticedId;
    }

    public void setBeNoticedId(String beNoticedId) {
        this.beNoticedId = beNoticedId == null ? null : beNoticedId.trim();
    }

    public String getFansId() {
        return fansId;
    }

    public void setFansId(String fansId) {
        this.fansId = fansId == null ? null : fansId.trim();
    }

	public Integer getFollowRelationship() {
		return followRelationship;
	}

	public void setFollowRelationship(Integer followRelationship) {
		this.followRelationship = followRelationship;
	}
}