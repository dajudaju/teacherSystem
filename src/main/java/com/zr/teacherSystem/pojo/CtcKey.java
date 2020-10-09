package com.zr.teacherSystem.pojo;

public class CtcKey {
	private Integer couid;

	private Integer tid;

	private Integer claid;

	public Integer getCouid() {
		return couid;
	}

	public void setCouid(Integer couid) {
		this.couid = couid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getClaid() {
		return claid;
	}

	public void setClaid(Integer claid) {
		this.claid = claid;
	}

	public CtcKey() {
		super();
	}

	public CtcKey(Integer couid, Integer tid, Integer claid) {
		super();
		this.couid = couid;
		this.tid = tid;
		this.claid = claid;
	}

}