package com.servlet;

import java.text.*;
import org.json.JSONObject;

import com.utils.DataBaseUtil;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchTrack extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String req_name = null;
		String start_time = null;
		String end_time = null;
		req_name = request.getParameter("tname");
		start_time = request.getParameter("start");
		end_time = request.getParameter("end");
		System.out.println(start_time);
		if(req_name == null){
			req_name = "体验25";
		}
		List jsonList = new ArrayList();
		try {
			Connection conn = DataBaseUtil.getConn();
			Statement statement = conn.createStatement();
			// 构造sql语句，根据传递过来的查询条件参数
			String sql = "select * from bcx_data where car_name=\'"+ req_name + "\'";
			sql += "and GPS_time>=\'" + start_time + "\' and GPS_time <= \'" + end_time + "\';";
			System.out.println("构造出来的sql语句是："+sql);
			ResultSet rs = statement.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				count = count + 1;
				Map map = new HashMap();
				map.put("index", count);
				map.put("device_id", rs.getString("Device_id"));
				map.put("location", rs.getString("Location"));
				map.put("name", rs.getString("car_name"));
				map.put("lon", rs.getString("Lon"));
				map.put("lat", rs.getString("Lat"));
				map.put("time", rs.getString("GPS_time"));
				map.put("speed", rs.getString("Speed"));
				map.put("start", rs.getString("Start"));
				map.put("direction", rs.getString("Direction"));
				jsonList.add(map);
			}
			statement.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// 下面开始构建返回的json
		JSONObject json = new JSONObject();
		try {
			json.put("aaData", jsonList);
			json.put("total", 25);
			json.put("result_msg", "ok"); // 如果发生错误就设置成"error"等
			json.put("result_code", 0); // 返回0表示正常，不等于0就表示有错误产生，错误代码
			System.out.println("最后构造得到的json是："+json.toString());
			response.setContentType("application/json; charset=UTF-8");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		try {
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println("返回结果给调用页面了。");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
