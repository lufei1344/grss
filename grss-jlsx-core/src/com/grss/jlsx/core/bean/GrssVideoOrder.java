package com.grss.jlsx.core.bean;

public class GrssVideoOrder extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3978770138670096831L;

	private String id;

    private String vidoId;

    private String orderId;

    private String programaName;

    private String vidoCatName1;

    private String vidoCatName2;

    private String vidoCatName3;

    private String vidoCatName4;

    private String vidoCatName5;
    
    private Integer level;
    
    private String name;
    
    private Integer orderSort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVidoId() {
        return vidoId;
    }

    public void setVidoId(String vidoId) {
        this.vidoId = vidoId == null ? null : vidoId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getProgramaName() {
        return programaName;
    }

    public void setProgramaName(String programaName) {
        this.programaName = programaName == null ? null : programaName.trim();
    }

    public String getVidoCatName1() {
        return vidoCatName1;
    }

    public void setVidoCatName1(String vidoCatName1) {
        this.vidoCatName1 = vidoCatName1 == null ? null : vidoCatName1.trim();
    }

    public String getVidoCatName2() {
        return vidoCatName2;
    }

    public void setVidoCatName2(String vidoCatName2) {
        this.vidoCatName2 = vidoCatName2 == null ? null : vidoCatName2.trim();
    }

    public String getVidoCatName3() {
        return vidoCatName3;
    }

    public void setVidoCatName3(String vidoCatName3) {
        this.vidoCatName3 = vidoCatName3 == null ? null : vidoCatName3.trim();
    }

    public String getVidoCatName4() {
        return vidoCatName4;
    }

    public void setVidoCatName4(String vidoCatName4) {
        this.vidoCatName4 = vidoCatName4 == null ? null : vidoCatName4.trim();
    }

    public String getVidoCatName5() {
        return vidoCatName5;
    }

    public void setVidoCatName5(String vidoCatName5) {
        this.vidoCatName5 = vidoCatName5 == null ? null : vidoCatName5.trim();
    }

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getOrderSort() {
		return orderSort;
	}

	public void setOrderSort(Integer orderSort) {
		this.orderSort = orderSort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    
}