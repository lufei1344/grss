package com.grss.jlsx.core.bean;

import java.util.Date;
import java.util.List;

public class GrssPosts extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1242762169681929043L;

	private String id;

    private String idea;

    private String communityId;

    private String imagesUrl;

    private String vidosUrl;
    
    private Date sendDate;
    
    private String title;
    
    private GrssUser grssUser;
    
    private Integer remarkTotal;
    
    private Integer admireTotal;
    
    private List<String> imageUrls;
    
    private Integer  isAdmire = 0; // 是否点赞 0未点赞1点赞
    
    private String userId;
    
    private Integer isMaster;
    
    private String sourceUserId;
    
    public GrssPosts(String id, String idea, String communityId, String imagesUrl, Date sendDate,String title,String userId, int isMaster) {
		super();
		this.id = id;
		this.idea = idea;
		this.communityId = communityId;
		this.imagesUrl = imagesUrl;
		this.sendDate = sendDate;
		this.title = title;
		this.isMaster = isAdmire;
		this.userId = userId;
	}
    
    
	public GrssPosts() {
	}


	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea == null ? null : idea.trim();
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId == null ? null : communityId.trim();
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl == null ? null : imagesUrl.trim();
    }

    public String getVidosUrl() {
        return vidosUrl;
    }

    public void setVidosUrl(String vidosUrl) {
        this.vidosUrl = vidosUrl == null ? null : vidosUrl.trim();
    }

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	
	public GrssUser getGrssUser() {
		return grssUser;
	}


	public void setGrssUser(GrssUser grssUser) {
		this.grssUser = grssUser;
	}


	public Integer getRemarkTotal() {
		return remarkTotal;
	}

	public void setRemarkTotal(Integer remarkTotal) {
		this.remarkTotal = remarkTotal;
	}

	public Integer getAdmireTotal() {
		return admireTotal;
	}

	public void setAdmireTotal(Integer admireTotal) {
		this.admireTotal = admireTotal;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public List<String> getImageUrls() {
		return imageUrls;
	}


	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}



	public Integer getIsAdmire() {
		return isAdmire;
	}


	public void setIsAdmire(Integer isAdmire) {
		this.isAdmire = isAdmire;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getIsMaster() {
		return isMaster;
	}


	public void setIsMaster(int isMaster) {
		this.isMaster = isMaster;
	}


	public String getSourceUserId() {
		return sourceUserId;
	}


	public void setSourceUserId(String sourceUserId) {
		this.sourceUserId = sourceUserId;
	}
    
    
}