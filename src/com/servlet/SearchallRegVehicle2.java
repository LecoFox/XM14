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


public class SearchallRegVehicle2 extends HttpServlet {
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
		String model=request.getParameter("model");
		String engine_id=request.getParameter("engine_id");
		String order=request.getParameter("order");
		String ordername=request.getParameter("ordername");
		String username=request.getParameter("username");
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
		System.out.println("model:"+model);
		System.out.println("engine_id:"+engine_id);
		System.out.println("order:"+order);
		System.out.println("ordername:"+ordername);
		System.out.println("username:"+username);
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
			sql="select * from reg_device where owner = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			System.out.println(sql);
			ResultSet tmprs = preparedStatement.executeQuery();

			
			while (tmprs.next()) {
				size = size + 1;	
			}
			if(model.equals("") && engine_id.equals(""))
			{
				sql = "select * from (select * from reg_device where owner = ?) as a order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("1");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
				
			}
			else if(!model.equals("")&&engine_id.equals("")){
				sql = "select * from (select * from reg_device where owner = ?) as a where model=? order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("2");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, model);
				preparedStatement.setInt(3, offset);
				preparedStatement.setInt(4, limit);
			}
			else if(!engine_id.equals("")&&model.equals("")){
				sql = "select * from (select * from reg_device where owner = ?) as a where engine_id=? order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("3");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, engine_id);
				preparedStatement.setInt(3, offset);
				preparedStatement.setInt(4, limit);
			}
			else{
				System.out.println("4");
				sql = "select * from (select * from reg_device where owner = ?) as a where engine_id=? and model=? order by "+ ordername + " "+ order +" limit ?,?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, engine_id);
				preparedStatement.setString(3, model);
				preparedStatement.setInt(4, offset);
				preparedStatement.setInt(5, limit);
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