package com.shop.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.entity.User;
import com.shop.service.ServiceFactory;

/**
 * Servlet implementation class Register
 */
@WebServlet("/AdminRegister")
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置字符集编码
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/html;charset=UTF-8");
		//获取打印输出流
		PrintWriter out = response.getWriter();
		//获取请求参数
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		if (password.equals(repassword)) {
			//创建用户实体
			User user = new User();
			user.setName(name);
			user.setPassword(password);
			user.setStatus(1);
			if (ServiceFactory.getUserService().doAdd(user) > 0) {//判断用户是否注册成功
				response.sendRedirect(request.getContextPath()+"/admin_login.jsp");
			}else {
				out.write("<script>");
				out.write("alert('用户注册失败!');");
				out.write("location.href='"+request.getContextPath()+"/admin_register.jsp'");
				out.write("</script>");
			}
		}else {
			out.write("<script>");
			out.write("alert('密码不一致!');");
			out.write("location.href='"+request.getContextPath()+"/admin_register.jsp'");
			out.write("</script>");
		}
	}

}
