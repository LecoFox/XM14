package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.RegVehicleDao;
import com.model.RegVehicle;

/**
 * Servlet implementation class UpdateVehicleServlet
 */
@WebServlet("/UpdateVehicleServlet")
public class UpdateVehicleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateVehicleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");

        //获取车辆登记信息
        String driver_id = req.getParameter("driverid");
        String engine_id = req.getParameter("engineid");
        System.out.println("获取分配信息成功");
        //实例化RegVehicleDao对象
        RegVehicleDao vehicleDao = new RegVehicleDao();
        
        if (!driver_id.equals("请选择")) {
			if(!engine_id.equals("请选择")){
				
					//实例化一个User对象
					RegVehicle vehicle = new RegVehicle();
		            //对用户对象的属性赋值
		            vehicle.setEngine_id(engine_id);
		            vehicle.setDriver_id(driver_id);
					if(vehicleDao.driverAvailable(driver_id)){
						vehicleDao.allocateVehicle(vehicle);
						req.setAttribute("info", "分配成功！ <br>");
						req.setAttribute("flag","1");
					}
					else {
						req.setAttribute("info", "此车辆已分配！<br>分配失败！<br>");
						req.setAttribute("flag","0");
					}
			}
			else{
				req.setAttribute("info", "发动机号为空！<br>分配失败！<br>");
				req.setAttribute("flag","0");
			}
		}
        else{
        	req.setAttribute("info", "驾驶证号为空！！<br>分配失败！<br>");
			req.setAttribute("flag","0");
        }
		
		//转发到message.jsp页面
		req.setAttribute("type", "register");
		req.getRequestDispatcher("upvehmsg.jsp").forward(req, response);
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
