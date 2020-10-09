package com.zr.teacherSystem.pojo;

/**
 * 成绩的显示实体类
 * 
 * @author Administrator
 *
 */
public class GradeExt extends Grade {

	// 课程名称
	private String cname;
	// 代课老师
	private String tname;

	public GradeExt() {
		super();
	}

	public GradeExt(Grade grade, String cname, String tname) {
		super(grade.getId(), grade.getSid(), grade.getCid(), grade.getTid(), grade.getPgrade(), grade.getKgrade(),
				grade.getZgrade());
		this.cname = cname;
		this.tname = tname;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "GradeExt [cname=" + cname + ", tname=" + tname + "]";
	}

}
