package com.grss.jlsx.core.bean;

import java.util.Date;

import com.grss.jlsx.core.utils.StringUtil;
/**
 * 收藏
 * @author XYL
 *
 */
public class GrssCollect extends BaseBean{
    private String id;

    private String userId;

    private String clubId;

    private Date jionDate;
    
	public GrssCollect(){
		
	}
	
	public GrssCollect(String userId,String clubId){
		this.id = StringUtil.getUUID();
		this.userId = userId;
		this.clubId = clubId;
		this.jionDate = new Date();
	}

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

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId == null ? null : clubId.trim();
    }

    public Date getJionDate() {
        return jionDate;
    }

    public void setJionDate(Date jionDate) {
        this.jionDate = jionDate;
    }
}