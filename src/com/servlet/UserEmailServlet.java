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
import javax.servlet.http.HttpSession;

import com.dao.EmailDao;
import com.utils.DataBaseUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserEmailServlet")
public class UserEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserEmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			String sql = "select distinct email,reg_device.Device_id, Location,car_name, Lon, Lat, Start, max(Speed) as Speed, reg_device.carImg, owner "
					+ "from bcx_data,reg_device,tb_user where speed > 15 and bcx_data.Device_id = reg_device.Device_id and reg_device.`owner` = tb_user.username"
					+ " group by reg_device.Device_id;";
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
				map.put("name", rs.getString("car_name"));
				map.put("lon", rs.getString("Lon"));
				map.put("lat", rs.getString("Lat"));
				map.put("start", rs.getString("Start"));
				map.put("speed", rs.getString("Speed"));
				map.put("carImg", rs.getString("carImg"));
				map.put("owner", rs.getString("owner"));
				jsonList.add(map);
			}
			statement.close();
			conn.close();

			for(int i = 0;i < jsonList.size();i++){
				HashMap<String,String> map1 = new HashMap<String,String>();
				map1 = (HashMap<String, String>)jsonList.get(i);
				String email = map1.get("email");
				String speed = map1.get("speed");
				String device_id = map1.get("device_id");
				System.out.println("车辆ID是"+device_id);
				System.out.println("超速速度是"+speed);
				System.out.println("发送的邮箱是"+email);
				String mess= ":您的车辆已超速（超过15km/h）\n您的超速信息如下：车辆ID为"+ device_id + "最高速度为"+ speed + "  ";
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
