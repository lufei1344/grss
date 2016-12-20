package com.grss.jlsx.core.bean;

import java.math.BigDecimal;
import java.util.Date;

public class GrssDrawMoney extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8831768637526374991L;

	private String id;

    private BigDecimal amount;

    private String accountsId;

    private Date drawTime;

    private Date finishTime;

    private Integer state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(String accountsId) {
        this.accountsId = accountsId == null ? null : accountsId.trim();
    }

    public Date getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(Date drawTime) {
        this.drawTime = drawTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}