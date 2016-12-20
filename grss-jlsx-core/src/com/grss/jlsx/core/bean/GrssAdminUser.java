package com.grss.jlsx.core.bean;

public class GrssAdminUser extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 159754485306937999L;

	private String id;

    private String name;

    private String phone;

    private Integer sex;

    private String email;

    private String password;
    
    private String roleId;
    
    private GrssRole grssRole;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public GrssRole getGrssRole() {
		return grssRole;
	}

	public void setGrssRole(GrssRole grssRole) {
		this.grssRole = grssRole;
	}
    
    
}