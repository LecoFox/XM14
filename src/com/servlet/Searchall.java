package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.model.User;

public class Searchall extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDao ud = new UserDao();
		System.out.println("这是一个断点。");
		ArrayList<User> userAll = ud.getAllUser();
		request.setAttribute("userAll", userAll);
		request.getRequestDispatcher("/showall.jsp").forward(request, response);
	}
}