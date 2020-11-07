package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RegVehicleDao;
import com.model.RegVehicle;


public class SearchallRegVehicle extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RegVehicleDao ud = new RegVehicleDao();
		System.out.println("这是一个断点。");
		List<RegVehicle> regVehicleAll = ud.getRegVehicleAll();
		request.setAttribute("regVehicleAll", regVehicleAll);
		request.getRequestDispatcher("/showallRegVehicle.jsp").forward(request, response);
	}
}