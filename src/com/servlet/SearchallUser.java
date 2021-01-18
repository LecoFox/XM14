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


public class SearchallUser extends HttpServlet {
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
		String username=request.getParameter("username");
		String type=request.getParameter("type");
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
		System.out.println("username:"+username);
		System.out.println("type:"+type);
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
			sql="select * from tb_user";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet tmprs = preparedStatement.executeQuery();
			
			while (tmprs.next()) {
				size = size + 1;	
			}
			if(username.equals("") && type.equals(""))
			{
				sql = "select * from tb_user order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("1");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, offset);
				preparedStatement.setInt(2, limit);
				
			}
			else if(!username.equals("")&&type.equals("")){
				sql = "select * from tb_user where username=? order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("2");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				//preparedStatement.setString(2, ordername);
				//preparedStatement.setString(3, order);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
			}
			else if(!type.equals("")&&username.equals("")){
				sql = "select * from tb_user where type=? order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("3");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, type);
				//preparedStatement.setString(2, ordername);
				//preparedStatement.setString(3, order);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
			}
			else{
				System.out.println("4");
				sql = "select * from tb_user where type=? and username=? order by "+ ordername + " "+ order +" limit ?,?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, type);
				preparedStatement.setString(2, username);
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
					map.put("username", rs.getString("username"));
					map.put("sex", rs.getString("sex"));
					map.put("question", rs.getString("question"));
					map.put("email", rs.getString("email"));
					map.put("type", rs.getString("type"));
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