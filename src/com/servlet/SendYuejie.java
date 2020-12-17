package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmailDao;
import com.utils.DataBaseUtil;

/**
 * Servlet implementation class SendYuejie
 */

public class SendYuejie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendYuejie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF=8");
		response.setContentType("text/html;charset=UTF-8");
		List jsonList = new ArrayList();
		String e = "1";
		String totMess = "您的车辆已超出设定区域，详细信息如下：";
		try {
			Connection conn = DataBaseUtil.getConn();
			Statement statement = conn.createStatement();
			// ����sql��䣬���ݴ��ݹ����Ĳ�ѯ��������
			String sql = "SELECT *, radius, 6378.138*2*ASIN(SQRT(POW(SIN((x.Lat*PI()/180 - "
					+ "weilan.lat*PI()/180)/2),2) + COS(x.Lat*PI()/180)*COS(weilan.lat*PI()/180)*POW("
					+ "SIN((x.Lon* PI()/ 180 - weilan.lon* PI()/180)/2),2))) As dis FROM bcx_data x,weilan "
					+ "where weilan.deviceid=x.Device_id and 6378.138*2*ASIN(SQRT(POW(SIN((x.Lat*PI()/180 - "
					+ "weilan.lat*PI()/180)/2),2) + COS(x.Lat*PI()/180)*COS(weilan.lat*PI()/180)*POW("
					+ "SIN((x.Lon* PI()/ 180 - weilan.lon* PI()/180)/2),2)))*1000>weilan.radius";
			System.out.println("Sql语句是："+sql);
			ResultSet rs = statement.executeQuery(sql);
			//int count = 0;
			while (rs.next()) {
				//count = count + 1;
				//Map map = new HashMap();
				HashMap<String, String> map =new HashMap<String, String>();
				//map.put("index", count);
				map.put("email", rs.getString("email"));
				map.put("device_id", rs.getString("Device_id"));
				map.put("location", rs.getString("Location"));
				map.put("lon", rs.getString("Lon"));
				map.put("lat", rs.getString("Lat"));
				map.put("dis", rs.getString("dis"));
				map.put("radius", rs.getString("radius"));
				jsonList.add(map);
			}
			statement.close();
			conn.close();

			for(int i = 0;i < jsonList.size();i++){
				HashMap<String,String> map1 = new HashMap<String,String>();
				map1 = (HashMap<String, String>)jsonList.get(i);
				String email = map1.get("email");
				String device_id = map1.get("device_id");
				String location = map1.get("location");
				String lon = map1.get("lon");
				String lat = map1.get("lat");
				String dis = map1.get("dis");
				String radius=map1.get("radius");
				System.out.println("车辆ID是"+device_id);
				System.out.println("发送的邮箱是"+email);
				String mess= "尊敬的用户您好:您的车辆已越界！！！\n您设置的区域大小为："+ radius + "KM\n车辆目前位于"+ location + "\n偏离中心区域"+dis+"千米";
				EmailDao emaildao = new EmailDao();
				emaildao.sendEmail(email, mess);
			}
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
