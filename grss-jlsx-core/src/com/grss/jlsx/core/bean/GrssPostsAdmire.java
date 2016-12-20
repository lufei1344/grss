package com.grss.jlsx.core.bean;

public class GrssPostsAdmire extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6624838536909098019L;

	private String postsId;

    private String userId;
    
    

    public GrssPostsAdmire(String postsId, String userId) {
		super();
		this.postsId = postsId;
		this.userId = userId;
	}
    
    

	public GrssPostsAdmire() {}



	

    public String getPostsId() {
		return postsId;
	}



	public void setPostsId(String postsId) {
		this.postsId = postsId;
	}



	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}