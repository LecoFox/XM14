package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RegVehicleDao;
import com.dao.UserDao;
import com.model.RegVehicle;
import com.model.User;

public class RegVehicleServlet extends HttpServlet {

	/**
		 * Constructor of the object.
		 */
	public RegVehicleServlet() {
		super();
	}

	/**
		 * Destruction of the servlet. <br>
		 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
		 * The doGet method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to get.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");

        //获取车辆登记信息
        String device_id = req.getParameter("deviceid");
        String engine_id = req.getParameter("engineid");
        String owner = req.getParameter("owner");
        String chepai = req.getParameter("chepai");
        String carimg = req.getParameter("carimg");
        String model = req.getParameter("model");
        String brand = req.getParameter("brand");
        System.out.println("获取车辆登记信息成功");
        //实例化RegVehicleDao对象
        RegVehicleDao vehicleDao = new RegVehicleDao();
        if (device_id != null) {
            //实例化一个RegVehicle对象
            RegVehicle vehicle = new RegVehicle();
            //对用户对象的属性赋值
            vehicle.setDevice_id(device_id);
            vehicle.setOwner(owner);
            vehicle.setChepai(chepai);
            vehicle.setCarImg(carimg);
            vehicle.setModel(model);
            vehicle.setBrand(brand);
            vehicle.setEngine_id(engine_id);
            vehicleDao.saveVehicle(vehicle);
            req.setAttribute("info", "登记成功 <br>");
        } else {
            req.setAttribute("info", "登记失败<br>");
        }
        //转发到message.jsp页面
        req.getRequestDispatcher("front.jsp").forward(req,response);
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
