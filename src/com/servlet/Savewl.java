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
import com.dao.WeilanDao;
import com.model.RegVehicle;
import com.model.WeiLan;

/**
 * Servlet implementation class savewl
 */

public class Savewl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Savewl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String lon=request.getParameter("lon");
		String lat=request.getParameter("lat");
		String radius=request.getParameter("radius");
		String deviceid=request.getParameter("deviceid");
		String email=request.getParameter("email");
		System.out.println("savewl");
		System.out.println(lon);
		System.out.println(lat);
		System.out.println(radius);
		System.out.println(deviceid);
		
		WeilanDao weilandao = new WeilanDao();
        if (deviceid != null) {
            //实例化一个RegVehicle对象
            WeiLan Weilan = new WeiLan();
            //对用户对象的属性赋值
            Weilan.setDeviceid(deviceid);
            Weilan.setLat(lat);
            Weilan.setLon(lon);
            Weilan.setRadius(radius);
            Weilan.setEmail(email);
            weilandao.savewl(Weilan);
            request.setAttribute("info", "保存成功 <br>");
        } else {
            request.setAttribute("info", "保存失败<br>");
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
