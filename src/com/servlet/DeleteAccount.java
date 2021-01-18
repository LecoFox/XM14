package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDao;
import com.model.User;

/**
 * Servlet implementation class DeleteAccount
 */
@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccount() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        UserDao dao=new UserDao();
        
        System.out.println("-------------delete account------------");
        System.out.println(request.getParameter("password"));
        System.out.println(user.getPassword());
        System.out.println("-------------delete account------------");

        if(request.getParameter("password").equals(user.getPassword())){
        	System.out.println("-----删除账号-----");
        	System.out.println(user.getUsername());
        	dao.delete(user.getUsername());
        	request.getSession().invalidate();
        	request.setAttribute("delete", "1");          // 设置错误属性
        	System.out.println("-----删除账号-----");
        	request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        else{
        	System.out.println("-----未能删除账号-----");
        	request.setAttribute("delete", "0");          // 设置错误属性
        	System.out.println("-----未能删除账号-----");
        	request.getRequestDispatcher("front2.jsp").forward(request, response);
        }
	}

}
