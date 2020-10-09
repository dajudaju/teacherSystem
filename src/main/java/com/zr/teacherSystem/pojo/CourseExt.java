package com.zr.teacherSystem.pojo;

/**
 * <p>
 * Company: offcn
 * </p>
 * 
 * @author zgf
 * @date 2017年6月6日
 * @version 1.0
 */
public class CourseExt extends Course {
	private String tname;// 教师姓名
	private Integer tId;// 教师的主键

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Integer gettId() {
		return tId;
	}

	public void settId(Integer tId) {
		this.tId = tId;
	}

	public CourseExt() {
		super();
	}

	public CourseExt(Course course, String tname) {
		super(course.getId(), course.getName(), course.getType(), course.getTeacher(), course.getClasses());
		this.tname = tname;
	}

	@Override
	public String toString() {
		return super.toString() + "CourseExt [tname=" + tname + ", tId=" + tId + "]";
	}

}
