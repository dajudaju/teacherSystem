package com.zr.teacherSystem.pojo;

import java.io.Serializable;

/**
 * 用户实体类
 * 
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable {
	// id
	private Integer id;
	// 用户名
	private String name;
	// 密码
	private String password;
	// 用户角色
	private Integer usertype;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public User() {
		super();
	}

	public User(Integer id, String name, String password, Integer usertype) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.usertype = usertype;
	}

}