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
 * Servlet implementation class Overspeed
 */
@WebServlet("/Overspeed")
public class Overspeed extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public String toList(){
		return "overspeed";
	}
	public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String setSpeed=request.getParameter("setSpeed");
		String StartTime=request.getParameter("StartTime");
		String EndTime=request.getParameter("EndTime");
		Integer uId=Integer.parseInt(request.getParameter("uid"));
		String userName = request.getParameter("username");
		
		System.out.println("OverSpeedServlet");
		System.out.println(setSpeed);
		System.out.println(StartTime);
		System.out.println(EndTime);
		
		RegVehicleDao vehicle = new RegVehicleDao();
		JSONArray res = null;
		try {
			if(uId!=null){
				if(uId==1){
					res=vehicle.getOverSpeed(StartTime, EndTime, setSpeed);
				}
				else{
					res=vehicle.getOverSpeed2(StartTime, EndTime, setSpeed,userName);
				}
			}

			System.out.println(res);
			PrintWriter out = response.getWriter();
	        out.println(res.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

}
