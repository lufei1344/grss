package com.grss.jlsx.core.bean;

public class GrssDictionaries extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8322466457459617059L;

	private Integer id;

    private String key;

    private String value;

    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}