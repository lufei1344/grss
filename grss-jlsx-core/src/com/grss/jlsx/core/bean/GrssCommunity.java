package com.grss.jlsx.core.bean;

import java.util.Date;

public class GrssCommunity extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2251658446684680882L;

	private String id;

    private String name;

    private String comment;

    private String imageUrl;
    
    private Date createDate;
    
    private String createUserId;
    
    private String residentTotal;
    
    private String 	topicTotal;
    
    private GrssUser grssUser;
    
    private int isJoin = 0;
    
    public GrssCommunity(String id, String name, String comment, String imageUrl, Date createDate,String createUserId) {
		super();
		this.id = id;
		this.name = name;
		this.comment = comment;
		this.imageUrl = imageUrl;
		this.createDate = createDate;
		this.createUserId = createUserId;
	}
    
    

	public GrssCommunity() {
		super();
	}



	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getCreateUserId() {
		return createUserId;
	}



	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}



	public String getResidentTotal() {
		return residentTotal;
	}



	public void setResidentTotal(String residentTotal) {
		this.residentTotal = residentTotal;
	}



	public String getTopicTotal() {
		return topicTotal;
	}



	public void setTopicTotal(String topicTotal) {
		this.topicTotal = topicTotal;
	}



	public GrssUser getGrssUser() {
		return grssUser;
	}



	public void setGrssUser(GrssUser grssUser) {
		this.grssUser = grssUser;
	}



	public int getIsJoin() {
		return isJoin;
	}



	public void setIsJoin(int isJoin) {
		this.isJoin = isJoin;
	}
	
	
	
    
}