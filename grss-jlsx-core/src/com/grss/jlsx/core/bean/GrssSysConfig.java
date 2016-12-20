package com.grss.jlsx.core.bean;
/**
 * 系统配置
 * @author wang
 *
 */
public class GrssSysConfig extends BaseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1202632623801752178L;
	private String id;
	private String name;
	private String value;
	private String remark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
