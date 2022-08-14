package com.imooc.mgallery.controller;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.imooc.mgallery.service.PaintingService;
import com.imooc.mgallery.utils.PageModel;

/**
 * Servlet implementation class PaintingController
 */
public class PaintingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private PaintingService paintingService = new PaintingService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaintingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		String page = request.getParameter("page");
	    String rows = request.getParameter("r");
	    String category = request.getParameter("c");
//	    int a = Integer.parseInt(category);
	    if(page == null) {
	    	page = "1";
	    	rows = "6";
	    }
	    if(rows == null) {
	    	rows = "6";
	    }
	    PageModel pageModel =  paintingService.pagination(Integer.parseInt(page),Integer.parseInt(rows), category);
	    request.setAttribute("pageModel", pageModel);
	    request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
	    
	}

}
