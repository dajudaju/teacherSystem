package com.zr.teacherSystem.pojo;

public class StudentView extends Student {
	private String classname;
	private String couname;
	private Integer cid;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getCouname() {
		return couname;
	}

	public void setCouname(String couname) {
		this.couname = couname;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public StudentView() {
		super();
	}

	public StudentView(Student student, String couname, Integer cid) {
		super(student.getId(), student.getName(), student.getSex(), student.getAddress(), student.getTel(),
				student.getClassid(), student.getUsertype(), student.getPassword(), student.getLoginname());
		this.couname = couname;
		this.cid = cid;
	}

}
