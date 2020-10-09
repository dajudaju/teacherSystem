package com.zr.teacherSystem.pojo;

public class Teacher {
	// id
	private Integer id;
	// 名字
	private String name;
	// 类别
	private Integer usertype;
	// 用户名
	private String loginname;
	// 密码
	private String password;
	//
	private String workid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname == null ? null : loginname.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getWorkid() {
		return workid;
	}

	public void setWorkid(String workid) {
		this.workid = workid == null ? null : workid.trim();
	}

	public Teacher() {
		super();
	}

	public Teacher(Integer id, String name, Integer usertype, String loginname, String password, String workid) {
		super();
		this.id = id;
		this.name = name;
		this.usertype = usertype;
		this.loginname = loginname;
		this.password = password;
		this.workid = workid;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", usertype=" + usertype + ", loginname=" + loginname
				+ ", password=" + password + ", workid=" + workid + "]";
	}

}