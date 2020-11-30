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
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import com.dao.EmailDao;

import com.utils.DataBaseUtil;

/**
 * Servlet implementation class EmailServlet
 */
@WebServlet("/EmailServlet")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF=8");
		response.setContentType("text/html;charset=UTF-8");
		List jsonList = new ArrayList();
		String e = "1";
		String totMess = "你的超速信息如下：";
		try {
			Connection conn = DataBaseUtil.getConn();
			Statement statement = conn.createStatement();
			// ����sql��䣬���ݴ��ݹ����Ĳ�ѯ��������
			String sql = "select distinct Device_id, Location,car_name, Lon, Lat, Start, Speed, carImg from bcx_data where speed > 30 order by id DESC;";
			System.out.println("Sql语句是："+sql);
			ResultSet rs = statement.executeQuery(sql);
			//int count = 0;
			while (rs.next()) {
				//count = count + 1;
				//Map map = new HashMap();
				HashMap<String, String> map =new HashMap<String, String>();
				//map.put("index", count);
				map.put("device_id", rs.getString("Device_id"));
				map.put("location", rs.getString("Location"));
				map.put("name", rs.getString("car_name"));
				map.put("lon", rs.getString("Lon"));
				map.put("lat", rs.getString("Lat"));
				map.put("start", rs.getString("Start"));
				map.put("speed", rs.getString("Speed"));
				map.put("carImg", rs.getString("carImg"));
				jsonList.add(map);
			}
			statement.close();
			conn.close();
			
			for(int i = 0;i < jsonList.size();i++){
				HashMap<String,String> map1 = new HashMap<String,String>();
				map1 = (HashMap<String, String>)jsonList.get(i);
				String device_id = map1.get("device_id");
				System.out.println("车辆ID是"+device_id);
				String speed = map1.get("speed");
				String mess= "车辆ID为"+ device_id + "速度为"+ speed + "  ";
				totMess += mess;
			}
			HttpSession session =request.getSession(false);
			e = (String) session.getAttribute("username");
			System.out.println(e);
			
			//String e = "251946391@qq.com";
			EmailDao emaildao = new EmailDao();
			emaildao.sendEmail(e, totMess);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println(e);
		List jsonList1 = new ArrayList();
		try {
			System.out.println(e);
			Connection conn = DataBaseUtil.getConn();
			Statement statement = conn.createStatement();
			// ����sql��䣬���ݴ��ݹ����Ĳ�ѯ��������
			String sql = "select * from tb_user where username = "
					+ "\"" + e + "\"" + " order by id DESC;";
			System.out.println("Sql语句是："+sql);
			ResultSet rs = statement.executeQuery(sql);
			//int count = 0;
			while (rs.next()) {
				HashMap<String, String> map =new HashMap<String, String>();
				map.put("username", rs.getString("username"));
				map.put("sex", rs.getString("sex"));
				map.put("email", rs.getString("email"));
				jsonList1.add(map);
			}
			statement.close();
			conn.close();
				HashMap<String,String> map1 = new HashMap<String,String>();
				map1 = (HashMap<String, String>)jsonList1.get(0);
				String email = map1.get("email");
				System.out.println("该用户的邮箱是"+email);
			//HttpSession session =request.getSession(false);
			//String e = (String) session.getAttribute("username");
			//System.out.println(e);
			
			//String e = "251946391@qq.com";
			EmailDao emaildao = new EmailDao();
			emaildao.sendEmail(e, totMess);
		} catch (SQLException e1) {
			e1.printStackTrace();
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
