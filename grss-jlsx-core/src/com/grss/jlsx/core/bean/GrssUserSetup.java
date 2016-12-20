package com.grss.jlsx.core.bean;

public class GrssUserSetup extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5622164636531085243L;

	private Integer id;

    private Integer reply;

    private Integer praise;

    private Integer privateChat;

    private Integer newFans;

    private Integer systemMessage;

    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReply() {
        return reply;
    }

    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getPrivateChat() {
        return privateChat;
    }

    public void setPrivateChat(Integer privateChat) {
        this.privateChat = privateChat;
    }

    public Integer getNewFans() {
        return newFans;
    }

    public void setNewFans(Integer newFans) {
        this.newFans = newFans;
    }

    public Integer getSystemMessage() {
        return systemMessage;
    }

    public void setSystemMessage(Integer systemMessage) {
        this.systemMessage = systemMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}