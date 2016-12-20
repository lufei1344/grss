package com.grss.jlsx.core.bean;

import java.util.Date;
/**
 * 意见帮助
 * @author XYL
 *
 */
public class GrssFeedback extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8755121397665304308L;

	private String id;

    private String userId;

    private String comment;
    
    private String commentImg;
    
    private Date submitDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

	public String getCommentImg() {
		return commentImg;
	}

	public void setCommentImg(String commentImg) {
		this.commentImg = commentImg;
	}
}