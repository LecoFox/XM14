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

public class SearchRecord extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF=8");
		response.setContentType("text/html;charset=UTF-8");
		List jsonList = new ArrayList();
		try {
			Connection conn = DataBaseUtil.getConn();
			Statement statement = conn.createStatement();
			// ����sql��䣬���ݴ��ݹ����Ĳ�ѯ��������
			String sql = "select * from bcx_data order by id DESC limit 25;";
			// System.out.println("���������sql����ǣ�"+sql);
			
			
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
				map.put("start", rs.getString("Start"));
				map.put("speed", rs.getString("Speed"));
				map.put("carImg", rs.getString("carImg"));
				map.put("direction", rs.getString("Direction"));
				jsonList.add(map);
			}
			statement.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		// ���濪ʼ�������ص�json
		JSONObject json = new JSONObject();
		try {
			json.put("aaData", jsonList);
			json.put("total", 25);
			json.put("result_msg", "ok"); // ���������������ó�"error"��
			json.put("result_code", 0); // ����0��ʾ������������0�ͱ�ʾ�д���������������
			// System.out.println("�����õ���json�ǣ�"+json.toString());
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
		// System.out.println("���ؽ��������ҳ���ˡ�");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
