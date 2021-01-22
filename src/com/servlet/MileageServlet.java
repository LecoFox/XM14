package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.dao.RegVehicleDao;
import com.utils.BaseServlet;

/**
 * Servlet implementation class MileageServlet
 */
@WebServlet("/Mileage")
public class MileageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public String toList(){
		return "mileage";
	}
	public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String setDeviceId=request.getParameter("setDeviceId");
		String StartTime=request.getParameter("StartTime");
		String EndTime=request.getParameter("EndTime");
		System.out.println("MileServlet");
		System.out.println(setDeviceId);
		System.out.println(StartTime);
		System.out.println(EndTime);
		
		RegVehicleDao vehicle = new RegVehicleDao();
		JSONArray res;
		try {
			res=vehicle.getMileage(StartTime, EndTime, setDeviceId);
			System.out.println(res);
			PrintWriter out = response.getWriter();
	        out.println(res.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}
