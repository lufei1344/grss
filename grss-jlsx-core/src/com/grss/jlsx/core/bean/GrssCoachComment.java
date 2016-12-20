package com.grss.jlsx.core.bean;

import java.util.Date;

public class GrssCoachComment extends BaseBean{
	private static final long serialVersionUID = 7338794447386722869L;

	private Integer id;

    private String coachId;

    private String userId;

    private String comment;

    private Date createTime;

    private Integer state;
    
    private GrssUser grssUser;
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId == null ? null : coachId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

	public GrssUser getGrssUser() {
		if(null==grssUser)
			grssUser=new GrssUser();
		return grssUser;
	}

	public void setGrssUser(GrssUser grssUser) {
		this.grssUser = grssUser;
	}
}