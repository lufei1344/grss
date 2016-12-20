package com.grss.jlsx.core.bean;

public class GrssAssets extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1643604930030598884L;

	private String id;

    private String amount;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}