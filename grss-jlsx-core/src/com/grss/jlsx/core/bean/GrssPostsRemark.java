package com.grss.jlsx.core.bean;

import java.util.Date;
/**
 * 回复帖子
 * @author XYL
 *
 */
public class GrssPostsRemark extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1117056447792008070L;

	private String id;

    private String content;

    private String remarkUserId;

    private String replyUserId;

    private Date remarkDate;
    
    private String postsId;
    
    private String remarkName;
    
    private String replyName;
    
    private String remarkUserImg;
    
    public GrssPostsRemark(String id, String content, String postsId, String remarkUserId, String replyUserId,
			Date remarkDate) {
		super();
		this.id = id;
		this.content = content;
		this.postsId = postsId;
		this.remarkUserId = remarkUserId;
		this.replyUserId = replyUserId;
		this.remarkDate = remarkDate;
	}
    
    

	public GrssPostsRemark() {}



	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }


	public String getRemarkUserId() {
        return remarkUserId;
    }

    public void setRemarkUserId(String remarkUserId) {
        this.remarkUserId = remarkUserId == null ? null : remarkUserId.trim();
    }

    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId == null ? null : replyUserId.trim();
    }

    public Date getRemarkDate() {
        return remarkDate;
    }

    public void setRemarkDate(Date remarkDate) {
        this.remarkDate = remarkDate;
    }

	public String getPostsId() {
		return postsId;
	}

	public void setPostsId(String postsId) {
		this.postsId = postsId;
	}

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getReplyName() {
		return replyName;
	}

	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}



	public String getRemarkUserImg() {
		return remarkUserImg;
	}



	public void setRemarkUserImg(String remarkUserImg) {
		this.remarkUserImg = remarkUserImg;
	}
    
    
}