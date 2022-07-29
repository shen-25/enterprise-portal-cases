package com.zs.servlet;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Employee {
	
	private Integer empno;
	private String job;
	private String Ename;
	@JSONField(name = "hiredate", format = "yyyy-MM-dd")
	private Date hdate;
	private Float salary;
	@JSONField(serialize = false)
	private String dname;
	public String getEname() {
		return Ename;
	}
	public void setEname(String ename) {
		Ename = ename;
	}
	
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHdate() {
		return hdate;
	}
	public void setHdate(Date hdate) {
		this.hdate = hdate;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}

}
