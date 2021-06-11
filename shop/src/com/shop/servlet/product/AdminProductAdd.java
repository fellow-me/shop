package com.shop.servlet.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.shop.entity.Category;
import com.shop.entity.Image;
import com.shop.entity.Product;
import com.shop.service.ServiceFactory;

/**
 * Servlet implementation class AdminProductAdd
 */
@WebServlet("/admin/AdminProductAdd")
public class AdminProductAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminProductAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Category> list = ServiceFactory.getCategoryService().getAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("admin_product_add.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符集编码
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 获取打印输出流
		PrintWriter out = response.getWriter();
		SmartUpload smartUpload = new SmartUpload();
		String path = getServletContext().getInitParameter("img_path");//获取web.xml中配置的图片路径
		java.io.File folder = new java.io.File(path);
		if (!folder.exists()) {// 如果文件夹不存在，就创建
			folder.mkdirs();
		}
		// 初始化
		smartUpload.initialize(this.getServletConfig(), request, response);
		try {
			smartUpload.setAllowedFilesList("jpg,png,jpeg,jfif");
			smartUpload.upload();
		} catch (ServletException | IOException | SmartUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e) {// 当上传的不是图片时，将捕获异常
			out.write("<script>");
			out.write("alert('只允许上传图片!');");
			out.write("location.href='" + request.getContextPath() + "/admin/AdminProductAdd'");
			out.write("</script>");
		}
		// 获取文件上传对象
		Request request1 = smartUpload.getRequest();
		String name = request1.getParameter("name");
		double price = Double.valueOf(request1.getParameter("price"));
		int stock = Integer.parseInt(request1.getParameter("stock"));
		Category category = ServiceFactory.getCategoryService()
				.getById(Integer.valueOf(request1.getParameter("category")));
		String detail = request1.getParameter("detail");
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setStock(stock);
		product.setCategory(category);
		product.setDetail(detail);
		int count = ServiceFactory.getProductService().doAdd(product);
		if (count > 0) {// 判断商品是否添加成功
			Files files = smartUpload.getFiles();
			for (int i = 0; i < files.getCount(); i++) {
				File file = files.getFile(i);
				if (file.isMissing()) {// 是否选择了图片
					continue;
				}
				String filename = UUID.randomUUID().toString() + "." + file.getFileExt();// 防止文件名重复
				String filepath = path + java.io.File.separator + filename;
				Image image = new Image();
				image.setProduct(ServiceFactory.getProductService().getLastInsertProduct());
				image.setUrl(filename);
				ServiceFactory.getImageService().doAdd(image);// 将商品图片的url路径保存到数据库
				try {
					file.saveAs(filepath);// 保存文件
				} catch (IOException | SmartUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			response.sendRedirect(request.getContextPath() + "/admin/AdminGetProduct");
		} else {
			out.write("<script>");
			out.write("alert('商品添加失败!');");
			out.write("location.href='" + request.getContextPath() + "/admin/AdminProductAdd'");
			out.write("</script>");
		}
	}
}
