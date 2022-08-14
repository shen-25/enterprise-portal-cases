package com.imooc.mgallery.service;

import java.util.List;

import javax.sound.midi.Soundbank;

import com.imooc.mgallery.dao.PaintingDao;
import com.imooc.mgallery.entity.Painting;
import com.imooc.mgallery.utils.PageModel;

public class PaintingService {
	private PaintingDao paintingDao = new PaintingDao();
	public PageModel pagination(int page, int rows, String...category) {
		if(rows <= 0) {
			throw new RuntimeException("无效的参数rows");
		}
		if(category.length == 0 || category[0] == null) {
	       	return paintingDao.pagination(page, rows);
		} else {
			int a = Integer.parseInt(category[0]);
			return paintingDao.pagination(Integer.parseInt(category[0]), page, rows);
		}
	}
	
	public void create(Painting painting) {
		paintingDao.create(painting);
	}
	
	public Painting findById(Integer id) {
		Painting p =  paintingDao.findById(id);
		if(p == null) {
			throw new RuntimeException("[id=" + id + "]油画不存在");
		}
		return p;
	}
	
	public void update(Painting newPainting, Integer isPreviewModified) {
		
		Painting oldPainting = this.findById(newPainting.getId());
		oldPainting.setpname(newPainting.getPname());
		oldPainting.setCategory(newPainting.getCategory());
		oldPainting.setPrice(newPainting.getPrice());
		oldPainting.setDescription(newPainting.getDescription());
		if(isPreviewModified == 1) {
			oldPainting.setPreview(newPainting.getPreview());
		}
		paintingDao.update(oldPainting);
				
	}
	
	  public void delete(Integer id) {
    	  paintingDao.delete(id);
      }
	  
	public static void main(String[] args) {
		PaintingService paintingService = new PaintingService();
		PageModel pageModel = paintingService.pagination(3,4);
		List<Painting> paintingList =  pageModel.getPageData();
		for(Painting painting: paintingList) {
			System.out.println(painting.getpname());
		}
	}

}
