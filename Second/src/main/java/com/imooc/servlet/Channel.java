package com.imooc.servlet;

public class Channel {
   
	private  String code;
	private String name;
	public Channel() {
	};
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Channel(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
}
