package com.grss.jlsx.core.bean;

import java.util.Date;

public class GrssCoachAttestation extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6146112991744749621L;

	private String id;

    private String identityCardUrl;

    private String certificationUrl;

    private Date finishTime;

    private String coachId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIdentityCardUrl() {
        return identityCardUrl;
    }

    public void setIdentityCardUrl(String identityCardUrl) {
        this.identityCardUrl = identityCardUrl == null ? null : identityCardUrl.trim();
    }

    public String getCertificationUrl() {
        return certificationUrl;
    }

    public void setCertificationUrl(String certificationUrl) {
        this.certificationUrl = certificationUrl == null ? null : certificationUrl.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId == null ? null : coachId.trim();
    }
}