package com.grss.jlsx.core.bean;

public class GrssUserAccounts extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7873262346286560040L;

	private String id;

    private String cardNo;

    private String openAccountName;

    private String openAccountBank;

    private String openAccountCity;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getOpenAccountName() {
        return openAccountName;
    }

    public void setOpenAccountName(String openAccountName) {
        this.openAccountName = openAccountName == null ? null : openAccountName.trim();
    }

    public String getOpenAccountBank() {
        return openAccountBank;
    }

    public void setOpenAccountBank(String openAccountBank) {
        this.openAccountBank = openAccountBank == null ? null : openAccountBank.trim();
    }

    public String getOpenAccountCity() {
        return openAccountCity;
    }

    public void setOpenAccountCity(String openAccountCity) {
        this.openAccountCity = openAccountCity == null ? null : openAccountCity.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}