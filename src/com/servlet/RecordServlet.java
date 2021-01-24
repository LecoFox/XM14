package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.utils.BaseServlet;
import com.utils.DataBaseUtil;

/**
 * Servlet implementation class Record
 */
@WebServlet("/Record")
public class RecordServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer uId=Integer.parseInt(request.getParameter("uid"));
		String userName = request.getParameter("username");
		String show=request.getParameter("show");
		List jsonList = new ArrayList();
		
		try{
			String sql = "select * from bcx_data order by id DESC limit 25;";
			Connection conn = DataBaseUtil.getConn();
			PreparedStatement ps=conn.prepareStatement(sql);
		
			if(uId!=1){
				sql = "select * from bcx_data natural join allocation where username=? order by id DESC limit 1;";
				ps= conn.prepareStatement(sql);
				ps.setString(1, userName);
			}
			ResultSet rs = ps.executeQuery();
		
			int count = 0;
			while (rs.next()) {
				if(show.equals("全部")||rs.getString("Start").substring(0, 2).equals(show)){
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
				map.put("GPS_time", rs.getString("GPS_time"));
				jsonList.add(map);
				}
			}
			ps.close();
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	// ���濪ʼ�������ص�json
	JSONObject json = new JSONObject();
	try {
		json.put("aaData", jsonList);
		json.put("total", jsonList.size());
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
		
	}

}
