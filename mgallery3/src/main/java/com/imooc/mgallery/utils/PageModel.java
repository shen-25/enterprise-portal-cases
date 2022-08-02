package com.imooc.mgallery.utils;

import java.util.ArrayList;
import java.util.List;

public class PageModel {
	
	private int page;//当前页号;
	private int totalPages;//
	private int rows;
	private int totalRows;
	private int pageStartRow;
	private int pageEndRow;
	private boolean hasNextPage;
	private boolean hasPreviousPage;
	private List pageData;
	
	public PageModel() {
		
	}
	public PageModel(List data, int page, int rows) {
		this.page = page;
		this.rows = rows;
		totalRows = data.size();
		this.totalPages = (int)Math.ceil((totalRows * 1.0) / rows);
//		System.out.println(totalPages);
		this.pageStartRow = (page - 1)* rows;
		this.pageEndRow = (page)* rows;
		if(pageEndRow > totalRows) {
			pageEndRow = totalRows;
		}
		pageData = data.subList(pageStartRow, pageEndRow);
		if(page <= 1) {
			hasPreviousPage = false;
		} else {
			hasPreviousPage = true;
		}
		
		if(page >= totalPages) {
			hasNextPage = false;
		} else {
			hasNextPage = true;
		}
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageStartRow() {
		return pageStartRow;
	}
	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}
	public int getPageEndRow() {
		return pageEndRow;
	}
	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public List getPageData() {
		return pageData;
	}
	public void setPageData(List pageData) {
		this.pageData = pageData;
	}
	
	public static void main(String[] args) {
		
		List sample = new ArrayList();
		for(int i = 0; i < 100; i++) {
			sample.add(i + 1);
		}
		
		PageModel pageModel = new PageModel(sample, 6, 8);
		System.out.println(pageModel.getPageData());
		System.out.println(pageModel.getPage());
		System.out.println(pageModel.hasNextPage);
		System.out.println(pageModel.getTotalPages());
		System.out.println(pageModel.getPageEndRow());
		System.out.println(pageModel.getPageStartRow());
	}

}
