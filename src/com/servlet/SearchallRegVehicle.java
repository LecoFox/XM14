package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.RegVehicleDao;
import com.model.RegVehicle;
import com.utils.DataBaseUtil;


public class SearchallRegVehicle extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF=8");
		//response.setContentType("text/html;charset=UTF-8");
		
		String tmp1=request.getParameter("limit");
		String tmp2=request.getParameter("offset");
		String owner=request.getParameter("owner");
		String driver=request.getParameter("driver");
		String order=request.getParameter("order");
		String ordername=request.getParameter("ordername");
		int limit=0;
		int offset=10;
		try {
		    limit = Integer.parseInt(tmp1);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		try {
		    offset = Integer.parseInt(tmp2);
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		System.out.println("limit:"+limit);
		System.out.println("offset:"+offset);
		System.out.println("owner:"+owner);
		System.out.println("driver:"+driver);
		System.out.println("order:"+order);
		System.out.println("ordername:"+ordername);
		//String show=request.getParameter("show");
		System.out.println("-------Search---------");
		List jsonList = new ArrayList();
		int size=0;
		try {
			Connection conn = DataBaseUtil.getConn();
			Statement statement = conn.createStatement();
			// ����sql��䣬���ݴ��ݹ����Ĳ�ѯ��������
			String sql="";
			PreparedStatement preparedStatement=null;
			sql="select * from reg_device";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet tmprs = preparedStatement.executeQuery();
			
			while (tmprs.next()) {
				size = size + 1;	
			}
			if(owner.equals("") && driver.equals(""))
			{
				sql = "select * from reg_device order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("1");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, offset);
				preparedStatement.setInt(2, limit);
				
			}
			else if(!owner.equals("")&&driver.equals("")){
				sql = "select * from reg_device where owner=? order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("2");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, owner);
				//preparedStatement.setString(2, ordername);
				//preparedStatement.setString(3, order);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
			}
			else if(!driver.equals("")&&owner.equals("")){
				sql = "select * from reg_device where driver_id=? order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("3");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, driver);
				//preparedStatement.setString(2, ordername);
				//preparedStatement.setString(3, order);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
			}
			else{
				System.out.println("4");
				sql = "select * from reg_device where driver_id=? and owner=? order by "+ ordername + " "+ order +" limit ?,?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, driver);
				preparedStatement.setString(2, owner);
				//preparedStatement.setString(3, ordername);
				//preparedStatement.setString(4, order);
				preparedStatement.setInt(3, offset);
				preparedStatement.setInt(4, limit);
			}
			// System.out.println("���������sql����ǣ�"+sql);
			//执行查询获取结果集
			ResultSet rs = preparedStatement.executeQuery();
			
			int count = 0;
			while (rs.next()) {
					count = count + 1;
					Map map = new HashMap();
					map.put("index", count);
					map.put("device_id", rs.getString("Device_id"));
					map.put("owner", rs.getString("owner"));
					map.put("chepai", rs.getString("chepai"));
					map.put("brand", rs.getString("brand"));
					map.put("engine_id", rs.getString("engine_id"));
					map.put("model", rs.getString("model"));
					map.put("driver_id", rs.getString("driver_id"));
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
			json.put("rows", jsonList);
			json.put("total", size);
			json.put("result_msg", "ok"); // ���������������ó�"error"��
			json.put("result_code", 0); // ����0��ʾ������������0�ͱ�ʾ�д���������������
			 System.out.println("-----------"+json.toString()+"------------");
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
}