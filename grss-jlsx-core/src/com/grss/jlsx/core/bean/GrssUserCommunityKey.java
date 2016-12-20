package com.grss.jlsx.core.bean;

public class GrssUserCommunityKey extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9013656787675176082L;

	private String userId;

    private String communityId;
    
    

    public GrssUserCommunityKey(String userId, String communityId) {
		super();
		this.userId = userId;
		this.communityId = communityId;
	}
    
    
	public GrssUserCommunityKey() {
	}


	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }
}