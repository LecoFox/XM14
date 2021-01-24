package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DriverDao;
import com.model.Driver;

public class RegDriverServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");

		//获取司机注册信息
		String driver_id = req.getParameter("driver_id");
		String name = req.getParameter("name");
		String sex = req.getParameter("sex");
		String birthday = req.getParameter("birthday");
		String validity_period = req.getParameter("validity_period");
		String phone_number = req.getParameter("phone_number");
		String email = req.getParameter("email");
		String creator = req.getParameter("creator");
		System.out.println("获取用户注册信息成功");
		//实例化DriverDao对象
		DriverDao driverDao = new DriverDao();
		if (driver_id != "") {
			if(name !=""){
				
					//实例化一个User对象
					Driver driver = new Driver();
					//对用户对象的属性赋值
					driver.setDriverId(driver_id);
					driver.setName(name);
					driver.setSex(sex);
					driver.setBirthday(birthday);
					driver.setValidityPeriod(validity_period);
					driver.setPhoneNumber(phone_number);
					driver.setEmail(email);
					driver.setCreator(creator);
					if(driverDao.DriverIdAvailable(driver_id)){
						driverDao.saveDriver(driver);
						req.setAttribute("info", "注册成功！ <br>");
					}
					else {
						req.setAttribute("info", "此司机已存在！<br>注册失败！<br>");
					}
			}
			else{
				req.setAttribute("info", "密码为空！<br>注册失败！<br>");
			}
		}
		else{
			req.setAttribute("info", "用户名为空！<br>注册失败！<br>");
		}
		//转发到message.jsp页面
		req.getRequestDispatcher("front.jsp").forward(req,resp);
	}



}
