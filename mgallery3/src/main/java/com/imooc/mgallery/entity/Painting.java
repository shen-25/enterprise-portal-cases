package com.imooc.mgallery.entity;

public class Painting {
	
	private Integer id;
	private String pname;
	private Integer category;
	private Integer price;
	private String  preview;
	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}
	private String description;
	
	public Painting() {}
	
	public Painting(Integer id, String pname, Integer categroy, Integer price, String preview, String description) {
		super();
		this.id = id;
		this.pname = pname;
		this.category = category;
		this.price = price;
		this.preview = preview;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}  
	public String getpname() {
		return pname;
	}
	public void setpname(String pname) {
		this.pname = pname;
	}
	public Integer getCategroy() {
		return category;
	}
	public void setCategroy(Integer category) {
		this.category = category;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
