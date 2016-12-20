package com.grss.jlsx.core.bean;

public class GrssUserCommunity extends GrssUserCommunityKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5023118679830962941L;

	private Integer isMaster;

    private Integer isOff;
    
    

    public GrssUserCommunity(String userId,String communityId,Integer isMaster, Integer isOff) {
    	this.setUserId(userId);
    	this.setCommunityId(communityId);
		this.isMaster = isMaster;
		this.isOff = isOff;
	}
    
    

	public GrssUserCommunity() {
		super();
	}



	public Integer getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Integer isMaster) {
        this.isMaster = isMaster;
    }

    public Integer getIsOff() {
        return isOff;
    }

    public void setIsOff(Integer isOff) {
        this.isOff = isOff;
    }
}