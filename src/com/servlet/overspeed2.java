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

import com.dao.RegVehicleDao;
import com.model.User;

/**
 * Servlet implementation class overspeed2
 */
@WebServlet("/overspeed2")
public class overspeed2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public overspeed2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//response.getWriter().append("Served at: ").append(request.getContextPath());
				response.setContentType("text/html;charset=utf-8");
				request.setCharacterEncoding("utf-8");
				
				String setSpeed=request.getParameter("setSpeed");
				String StartTime=request.getParameter("StartTime");
				String EndTime=request.getParameter("EndTime");
				HttpSession session =request.getSession(false);
				String username= (String) session.getAttribute("username");
				System.out.println("OverSpeedServlet2");
				System.out.println(setSpeed);
				System.out.println(StartTime);
				System.out.println(EndTime);
				System.out.println(username);
				RegVehicleDao vehicle = new RegVehicleDao();
				JSONArray res;
				try {
					res=vehicle.getOverSpeed2(StartTime, EndTime, setSpeed,username);
					System.out.println(res);
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
