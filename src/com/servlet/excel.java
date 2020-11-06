package com.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ExcelFileGenerator;
import com.dao.UserDao;
import com.model.User;

/**
 * Servlet implementation class excel
 */
@WebServlet("/excel")
public class excel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public excel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//初始化fieldName，fieldDate
		try{
			String myexcel="myexcel";
			//回去输出流
			OutputStream out=response.getOutputStream();
			//重置输出流
			response.reset();
			//设置导出Excel报表的导出形式
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment;filename="+myexcel+".xls");
			ExcelFileGenerator  efg=new ExcelFileGenerator();
			UserDao user = new UserDao();
			ArrayList<User> slist = user.getAllUser();
			efg.exportUser(slist,out);
			//设置输出形式
			System.setOut(new PrintStream(out));
			//刷新输出流
			out.flush();
			//关闭输出流
			if(out!=null){
				out.close();
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}
}
