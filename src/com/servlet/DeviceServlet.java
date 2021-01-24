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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.DeviceDao;
import com.dao.RegVehicleDao;
import com.model.Device;
import com.model.RegVehicle;
import com.utils.BaseServlet;
import com.utils.DataBaseUtil;

/**
 * Servlet implementation class DeviceServlet
 */
@WebServlet("/Device")
public class DeviceServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public String toList(){
		return "AllocationDevice";
	}
	public void add(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        //获取车辆登记信息
        String device_id = req.getParameter("deviceid");
        String username = req.getParameter("username");
        String date = req.getParameter("date");
        
        //Integer uId=Integer.parseInt(req.getParameter("uid"));
        //String uname = req.getParameter("uname");
       
        //System.out.println("获取车辆登记信息成功");
        //实例化RegVehicleDao对象
        DeviceDao dd = new DeviceDao();
        JSONObject json = new JSONObject();
        if (!device_id.equals("请选择")) {
        	if (!username.equals("请选择")) {
        		
					//实例化一个User对象
					Device dv = new Device();
		            //对用户对象的属性赋值
		            dv.setDeviceId(device_id);
		            dv.setUsername(username);
		            dv.setAdate(date);
					if(dd.deviceAvailable(device_id)){
						dd.saveAlc(dv);
						try {
							json.put("result_msg", "注册成功"); // ���������������ó�"error"��
							json.put("result_code", 200); // ����0��ʾ������������0�ͱ�ʾ�д���������������
							// System.out.println("�����õ���json�ǣ�"+json.toString());
							
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
					}
					else {
						try {
							json.put("result_msg", "设备已分配！注册失败"); // ���������������ó�"error"��
							json.put("result_code", 0); // ����0��ʾ������������0�ͱ�ʾ�д���������������
							// System.out.println("�����õ���json�ǣ�"+json.toString());
							
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
					}
        		}
        	
        		
        	else{
        		try {
    				json.put("result_msg", "选择非法用户名！<br>注册失败！<br>"); // ���������������ó�"error"��
    				json.put("result_code", 0); // ����0��ʾ������������0�ͱ�ʾ�д���������������
    				// System.out.println("�����õ���json�ǣ�"+json.toString());
    				
    			} catch (JSONException e1) {
    				e1.printStackTrace();
    			}
        	}
        }
        else{
        	try {
				json.put("result_msg", "选择非法设备号！<br>注册失败！<br>"); // ���������������ó�"error"��
				json.put("result_code", 0); // ����0��ʾ������������0�ͱ�ʾ�д���������������
				// System.out.println("�����õ���json�ǣ�"+json.toString());
				
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
        }
        try {
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String username = request.getParameter("username");
        String deviceid = request.getParameter("deviceid");
        System.out.println("username:"+username);
        System.out.println("deviceid:"+deviceid);
        
        DeviceDao dao=new DeviceDao();
        JSONObject json = new JSONObject();
        try{
        	if(dao.Update(username, deviceid)){
            	json.put("result_msg", "ok"); // ���������������ó�"error"��
    			json.put("result_code", 200);
            }
            else{
            	json.put("result_msg", "error"); // ���������������ó�"error"��
    			json.put("result_code", 0);
            }
        } catch (JSONException e){
        	e.printStackTrace();
        }
        
        response.setContentType("application/json; charset=UTF-8");
        try {
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        
        DeviceDao dao=new DeviceDao();
        String deviceid=request.getParameter("deviceid");
        System.out.println("-------------delete Vehicle------------");
        System.out.println(request.getParameter("deviceid"));
        System.out.println("-------------delete Vehicle------------");

        JSONObject json = new JSONObject();
        try{
        	if(dao.delete(deviceid)){
            	json.put("result_msg", "ok"); // ���������������ó�"error"��
    			json.put("result_code", 200);
            }
            else{
            	json.put("result_msg", "error"); // ���������������ó�"error"��
    			json.put("result_code", 0);
            }
        } catch (JSONException e){
        	e.printStackTrace();
        }
        
        response.setContentType("application/json; charset=UTF-8");
        try {
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tmp1=request.getParameter("limit");
		String tmp2=request.getParameter("offset");
		String device=request.getParameter("device");
		String user=request.getParameter("user");
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
		System.out.println("device:"+device);
		System.out.println("user:"+user);
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
			
			if((device==null||device.equals("")) &&(user==null||user.equals("")))
			{
				sql="select * from allocation";
				preparedStatement = conn.prepareStatement(sql);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				
				sql = "select * from allocation order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("1");
				preparedStatement = conn.prepareStatement(sql);
				
				preparedStatement.setInt(1, offset);
				preparedStatement.setInt(2, limit);
				
			}
			else if(!device.equals("")&&(user==null||user.equals(""))){
				sql="select * from allocation where device_id=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, device);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				
				sql = "select * from allocation where device_id=? order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("2");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, device);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
			}
			else if(!user.equals("")&&(device==null||device.equals(""))){
				sql="select * from allocation where username=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, user);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				
				sql = "select * from allocation where username=? order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("3");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, user);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
			}
			else{
				sql="select * from allocation where device_id=? and username=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, device);
				preparedStatement.setString(2, user);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				
				System.out.println("4");
				sql = "select * from allocation where device_id=? and username=? order by "+ ordername + " "+ order +" limit ?,?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, device);
				preparedStatement.setString(2, user);
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
					map.put("username", rs.getString("username"));
					map.put("adate", rs.getString("adate"));
					
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
	}
	public void listunalc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List jsonList = new ArrayList();
		int size=0;
		try {
			Connection conn = DataBaseUtil.getConn();
			Statement statement = conn.createStatement();
			// ����sql��䣬���ݴ��ݹ����Ĳ�ѯ��������
			String sql="select * from (select * from bcx_data order by id DESC limit 25) a where device_id not in(select device_id from allocation)";
			ResultSet rs = statement.executeQuery(sql);
			
			int count = 0;
			while (rs.next()) {
				
					count = count + 1;
					Map map = new HashMap();
					map.put("index", count);
					map.put("device_id", rs.getString("Device_id"));
					map.put("car_name", rs.getString("car_name"));
					
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
			json.put("data", jsonList);
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
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 连接数据库
        // 执行查询查询一级列表内容
        Integer uId=Integer.parseInt(request.getParameter("uid"));
		String userName = request.getParameter("username");
		System.out.println("----------------------------");
		System.out.println("uId:"+uId);
		System.out.println("userName:"+userName);
		System.out.println("----------------------------");
		
        DeviceDao dao = new DeviceDao();
        JSONArray res=null;
		try {
			if(uId!=null){
				if(uId==1){
					res = dao.SelectAllDevice();
				}
				else{
					res=dao.SelectOnesDevice(userName);
				}
			}
			System.out.println(res.toString());
			PrintWriter out = response.getWriter();
	        out.println(res.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectunreg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        // 连接数据库
        // 执行查询查询一级列表内容
        Integer uId=Integer.parseInt(request.getParameter("uid"));
		String userName = request.getParameter("username");
		System.out.println("----------------------------");
		System.out.println("uId:"+uId);
		System.out.println("userName:"+userName);
		System.out.println("----------------------------");
		
        DeviceDao dao = new DeviceDao();
        JSONArray res=null;
		if(uId!=null){
			if(uId==1){
				res = dao.SelectAllUnregDevice();
			}
			else{
				res=dao.SelectOnesUnregDevice(userName);
			}
		}
		System.out.println(res.toString());
		PrintWriter out = response.getWriter();
		out.println(res.toString());
	}
}
