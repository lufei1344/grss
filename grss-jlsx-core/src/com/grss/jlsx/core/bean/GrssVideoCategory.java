package com.grss.jlsx.core.bean;

import java.util.Date;

public class GrssVideoCategory extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6182230782961922970L;

	private String id;

    private String name;

    private String prarentId;

    private String cateCode;

    private Date createDate;

    private Date updateDate;
    
    private Integer programaId;
    
    private String programaName;
    
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

    public String getPrarentId() {
        return prarentId;
    }

    public void setPrarentId(String prarentId) {
        this.prarentId = prarentId == null ? null : prarentId.trim();
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode == null ? null : cateCode.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	public Integer getProgramaId() {
		return programaId;
	}

	public void setProgramaId(Integer programaId) {
		this.programaId = programaId;
	}

	public String getProgramaName() {
		return programaName;
	}

	public void setProgramaName(String programaName) {
		this.programaName = programaName;
	}
    
    
}