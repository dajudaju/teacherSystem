package com.zr.teacherSystem.pojo;

/**
 * 班级和课程实体
 * 
 * @author Administrator
 *
 */
public class Course_Class {

	// 班级
	private Classes classes;
	// 课程
	private Course course;

	public Classes getClasses() {
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Course_Class() {
		super();
	}

	public Course_Class(Classes classes, Course course) {
		super();
		this.classes = classes;
		this.course = course;
	}

}
