package com.imooc.mgallery.controller;

import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.imooc.mgallery.entity.Painting;
import com.imooc.mgallery.service.PaintingService;
import com.imooc.mgallery.utils.PageModel;

/**
 * Servlet implementation class ManagementController
 */
//映射management
public class ManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PaintingService paintingService = new PaintingService();
    /**
     * @see HttpServlet#HttpServlet(98
     */
    public ManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if(method.equals("list")) {
			this.list(request, response);
		} else if(method.equals("show_create")) {
			this.showCreatePage(request, response);
		} else if(method.equals("create")) {
			this.create(request, response);
		} else if(method.equals("show_update")) {
			this.showUpdatePage(request, response);
		}else if(method.equals("update")) {
			this.update(request, response);
		}else if(method.equals("delete")) {
			this.delete(request, response);
		}
		/**else if(method.equals("kankan")) {
			request.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
		}**/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String p = request.getParameter("p");
		String r = request.getParameter("r");
		if(p == null) {
			p = "1";
		}
		if(r == null) {
			r = "6";
		}
		PageModel pageModel = paintingService.pagination(Integer.parseInt(p), Integer.parseInt(r));
		request.setAttribute("pageModel", pageModel);
		request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
		}
	
   private void showCreatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
   	request.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(request, response);

	
   }
   private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	   //1.初始化FileUpload组件
	   FileItemFactory factory = new DiskFileItemFactory();
	   /**
	    * FileItemFactory用于将前端提交的表单数据转换为一个个FileItem
	    * ServletFileUpload则是为FileUpload组件提供Java Web
	    */
	   ServletFileUpload sf = new ServletFileUpload(factory);
	   try {
		List<FileItem> formData = sf.parseRequest(request);
		Painting painting = new Painting();
		for(FileItem fi: formData) {
			if(fi.isFormField()) {
				switch (fi.getFieldName()) {
				case "pname":
					painting.setpname(fi.getString("UTF-8"));
					break;
				case "category":
					painting.setCategory(Integer.parseInt(fi.getString("UTF-8")));
					break;
				case "price":
					painting.setPrice(Integer.parseInt(fi.getString("UTF-8")));
					break;
				case "description":
					painting.setDescription((fi.getString("UTF-8")));
					break;
				default:
					break;
				}
				
			} else {
				String path = request.getServletContext().getRealPath("/upload");
				//String path = "F:\\eclipse\\eclipse-workspace3\\mgallery/src/main/webapp/upload";
				System.out.println(path);
				String filename = UUID.randomUUID().toString();
				filename += fi.getName().substring(fi.getName().lastIndexOf("."));
				try {
					fi.write(new File(path, filename));
					painting.setPreview("upload/"+filename);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}
		paintingService.create(painting);
		
		//response.sendRedirect("/mgallery3/management.html");
		///mgallery3/management?method=list
				response.sendRedirect("/mgallery3/management?method=list");
	} catch (FileUploadException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
   }
   
   private void showUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   
	   String id = request.getParameter("id");
	   Painting painting = paintingService.findById(Integer.parseInt(id));
	   request.setAttribute("painting", painting);
	   request.getRequestDispatcher("/WEB-INF/jsp/update.jsp").forward(request, response);
   }
   
   private void update(HttpServletRequest request, HttpServletResponse response) {
	   int isPreviewModified = 0;
	   FileItemFactory factory = new DiskFileItemFactory();
	   ServletFileUpload sf = new ServletFileUpload(factory);
	   try {
			List<FileItem> formData = sf.parseRequest(request);
			Painting painting = new Painting();
			for(FileItem fi: formData) {
				if(fi.isFormField()) {
					switch (fi.getFieldName()) {
					case "pname":
						painting.setpname(fi.getString("UTF-8"));
						break;
					case "category":
						painting.setCategory(Integer.parseInt(fi.getString("UTF-8")));
						break;
					case "price":
						painting.setPrice(Integer.parseInt(fi.getString("UTF-8")));
						break;
					case "description":
						painting.setDescription((fi.getString("UTF-8")));
						break;
					case "id":
						painting.setId(Integer.parseInt(fi.getString("UTF-8")));
						break;
					case "isPreviewModified":
						isPreviewModified = Integer.parseInt(fi.getString("UTF-8"));
						break;
					default:
						break;
					}
				}else {
					if(isPreviewModified == 1) {
						System.out.println("文件上传项" + fi.getFieldName());
						String path = request.getServletContext().getRealPath("/upload");
						String filename = UUID.randomUUID().toString();
						filename += fi.getName().substring(fi.getName().lastIndexOf("."));
						fi.write(new File(path, filename));
						painting.setPreview("upload/"+filename);
					}
					
				}	
			 }
			 paintingService.update(painting, isPreviewModified);
			 response.sendRedirect("/mgallery3/management?method=list");
			 }catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
			}
   }
   
   public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
	   
	   String id = request.getParameter("id");
	     PrintWriter out = response.getWriter();
		try {		
			paintingService.delete(Integer.parseInt(id));
			out.println("{\"result\": \"ok\"}");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("{\"result\":\"" + e.getMessage() + "\"}");
		}
              
     }
   
   
}
