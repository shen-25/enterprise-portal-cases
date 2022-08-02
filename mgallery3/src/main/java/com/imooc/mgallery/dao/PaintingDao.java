package com.imooc.mgallery.dao;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import com.imooc.mgallery.entity.Painting;
import com.imooc.mgallery.utils.PageModel;
import com.imooc.mgallery.utils.XmlDataSource;
//只对数据增删改查
public class PaintingDao {
	
	public PageModel pagination(int page, int rows) {
		List<Painting> list = XmlDataSource.getRawData();
		PageModel pageModel = new PageModel(list, page, rows);
		return pageModel;
	}
     
	public PageModel pagination(int category, int page, int rows) {
		
		List<Painting> list = XmlDataSource.getRawData();
		List<Painting> categoryList = new ArrayList<>();
		for(Painting painting: list) {
			if(painting.getCategroy() == category) {
				categoryList.add(painting);
			}
		}
		PageModel pageModel = new PageModel(categoryList, page, rows);
		return pageModel;
	}
    
	public void create(Painting painting) {
	   
			XmlDataSource.append(painting);
	}
	
	public Painting findById(Integer id) {
		List<Painting> data = XmlDataSource.getRawData();
		for(Painting p: data) {
			if(p.getId() == id) {
				return p;
			}
		}
		return null;
	}
	public void update(Painting painting) {
		XmlDataSource.update(painting);
	}
	public void delete(Integer id) {
		XmlDataSource.delete(id);
	}
}
