package com.zr.teacherSystem.pojo;

public class StudentExt extends Student {
	private String classname;

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public StudentExt() {
		super();
	}

	public StudentExt(Student student, String classname) {
		super(student.getId(), student.getName(), student.getSex(), student.getAddress(), student.getTel(),
				student.getClassid(), student.getUsertype(), student.getPassword(), student.getLoginname());
		this.classname = classname;
	}

}
