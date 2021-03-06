package com.zr.teacherSystem.pojo;

public class Course {
	private Integer id;

	private String name;

	private String type;

	// 排课用
	private Teacher teacher;

	// 排课用
	private String classes;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public Course() {
		super();
	}

	public Course(Integer id, String name, String type, Teacher teacher, String classes) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.teacher = teacher;
		this.classes = classes;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", type=" + type + ", teacher=" + teacher + ", classes="
				+ classes + "]";
	}

}