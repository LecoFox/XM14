package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;

import com.dao.DriverDao;

/**
 * Servlet implementation class SelectOnesDriver
 */
@WebServlet("/SelectOnesDriver")
public class SelectOnesDriver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOnesDriver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("------SelectOnesDriver------");
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 连接数据库
        // 执行查询查询一级列表内容
        HttpSession session =request.getSession(false);
		String username= (String) session.getAttribute("username");
        DriverDao driver = new DriverDao();
        JSONArray res;
		try {
			res = driver.SelectOnesDriver(username);
			System.out.println(res.toString());
			PrintWriter out = response.getWriter();
	        out.println(res.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
