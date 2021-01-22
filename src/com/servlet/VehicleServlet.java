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

import com.dao.RegVehicleDao;
import com.model.RegVehicle;
import com.utils.BaseServlet;
import com.utils.DataBaseUtil;

/**
 * Servlet implementation class VehicleServlet
 */
@WebServlet("/Vehicle")
public class VehicleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public String toList(){
		return "showallRegVehicle";
	}
	public void add(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        //获取车辆登记信息
        String device_id = req.getParameter("deviceid");
        String engine_id = req.getParameter("engineid");
        String owner = req.getParameter("owner");
        String chepai = req.getParameter("chepai");
        String model = req.getParameter("model");
        String brand = req.getParameter("brand");
        Integer uId=Integer.parseInt(req.getParameter("uid"));
        String uname = req.getParameter("uname");
        String driver_id = "";
        if(uId!=1){
        	owner=uname;
        }
        System.out.println("获取车辆登记信息成功");
        //实例化RegVehicleDao对象
        RegVehicleDao vehicleDao = new RegVehicleDao();
        JSONObject json = new JSONObject();
        if (!device_id.equals("请选择")) {
        	if (!owner.equals("请选择")) {
        		if(engine_id !=""){
					//实例化一个User对象
					RegVehicle vehicle = new RegVehicle();
		            //对用户对象的属性赋值
		            vehicle.setDevice_id(device_id);
		            vehicle.setOwner(owner);
		            vehicle.setChepai(chepai);
		            vehicle.setModel(model);
		            vehicle.setBrand(brand);
		            vehicle.setEngine_id(engine_id);
		            vehicle.setDriver_id(driver_id);
					if(vehicleDao.deviceAvailable(device_id)){
						vehicleDao.saveVehicle(vehicle);
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
							json.put("result_msg", "车辆已绑定！注册失败"); // ���������������ó�"error"��
							json.put("result_code", 0); // ����0��ʾ������������0�ͱ�ʾ�д���������������
							// System.out.println("�����õ���json�ǣ�"+json.toString());
							
						} catch (JSONException e1) {
							e1.printStackTrace();
						}
					}
        		}
        	
        		else{
        			try {
        				json.put("result_msg", "发动机号为空！<br>注册失败！<br>"); // ���������������ó�"error"��
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

        String engineid = request.getParameter("engineid");
        String driverid = request.getParameter("driverid");
        String deviceid = request.getParameter("deviceid");
        System.out.println("engineid:"+engineid);
        System.out.println("driverid:"+driverid);
        System.out.println("deviceid:"+deviceid);
        
        RegVehicleDao dao=new RegVehicleDao();
        JSONObject json = new JSONObject();
        try{
        	if(dao.UpdateReg(engineid, driverid, deviceid)){
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
        
        RegVehicleDao dao=new RegVehicleDao();
        String deviceid=request.getParameter("engineid");
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
		Integer uId=Integer.parseInt(request.getParameter("uid"));
		String username = request.getParameter("uname");
		String tmp1=request.getParameter("limit");
		String tmp2=request.getParameter("offset");
		String model=request.getParameter("model");
		String engine_id=request.getParameter("engine_id");
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
		System.out.println("model:"+model);
		System.out.println("engine_id:"+engine_id);
		System.out.println("order:"+order);
		System.out.println("ordername:"+ordername);
		System.out.println("username:"+username);
		List jsonList = new ArrayList();
		int size=0;
		try {
			Connection conn = DataBaseUtil.getConn();
			Statement statement = conn.createStatement();
			// ����sql��䣬���ݴ��ݹ����Ĳ�ѯ��������
			String sql="";
			PreparedStatement preparedStatement=null;
			
			
			if(uId!=1){
				if((model==null||model.equals("")) &&(engine_id==null||engine_id.equals("")))
				{
					sql="select * from (select * from reg_device where owner = ?) as a";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, username);
					ResultSet tmprs = preparedStatement.executeQuery();
					
					while (tmprs.next()) {
						size = size + 1;	
					}
					tmprs.close();
					
					sql = "select * from (select * from reg_device where owner = ?) as a order by "+ ordername + " "+ order +" limit ?,?";
					System.out.println("1");
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setInt(2, offset);
					preparedStatement.setInt(3, limit);
					
				}
				else if(!model.equals("")&&(engine_id==null||engine_id.equals(""))){
					sql="select * from (select * from reg_device where owner = ?) as a where model=?";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, model);
					ResultSet tmprs = preparedStatement.executeQuery();
					
					while (tmprs.next()) {
						size = size + 1;	
					}
					tmprs.close();
					
					sql = "select * from (select * from reg_device where owner = ?) as a where model=? order by "+ ordername + " "+ order +" limit ?,?";
					System.out.println("2");
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, model);
					preparedStatement.setInt(3, offset);
					preparedStatement.setInt(4, limit);
				}
				else if(!engine_id.equals("")&&(model==null||model.equals(""))){
					sql="select * from (select * from reg_device where owner = ?) as a where engine_id=?";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, engine_id);
					ResultSet tmprs = preparedStatement.executeQuery();
					
					while (tmprs.next()) {
						size = size + 1;	
					}
					tmprs.close();
					
					sql = "select * from (select * from reg_device where owner = ?) as a where engine_id=? order by "+ ordername + " "+ order +" limit ?,?";
					System.out.println("3");
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, engine_id);
					preparedStatement.setInt(3, offset);
					preparedStatement.setInt(4, limit);
				}
				else{
					sql="select * from (select * from reg_device where owner = ?) as a where engine_id=? and model=?";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, engine_id);
					preparedStatement.setString(3, model);
					ResultSet tmprs = preparedStatement.executeQuery();
					
					while (tmprs.next()) {
						size = size + 1;	
					}
					tmprs.close();
					
					System.out.println("4");
					sql = "select * from (select * from reg_device where owner = ?) as a where engine_id=? and model=? order by "+ ordername + " "+ order +" limit ?,?";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, username);
					preparedStatement.setString(2, engine_id);
					preparedStatement.setString(3, model);
					preparedStatement.setInt(4, offset);
					preparedStatement.setInt(5, limit);
				}
			} else{
				if((model==null||model.equals("")) &&(engine_id==null||engine_id.equals("")))
				{
					sql="select * from reg_device";
					preparedStatement = conn.prepareStatement(sql);
					ResultSet tmprs = preparedStatement.executeQuery();
					
					while (tmprs.next()) {
						size = size + 1;	
					}
					tmprs.close();
					
					sql = "select * from reg_device order by "+ ordername + " "+ order +" limit ?,?";
					System.out.println("1");
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setInt(1, offset);
					preparedStatement.setInt(2, limit);
					
				}
				else if(!model.equals("")&&(engine_id==null||engine_id.equals(""))){
					sql="select * from reg_device where model=?";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, model);
					ResultSet tmprs = preparedStatement.executeQuery();
					
					while (tmprs.next()) {
						size = size + 1;	
					}
					tmprs.close();
					
					sql = "select * from reg_device where model=? order by "+ ordername + " "+ order +" limit ?,?";
					System.out.println("2");
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, model);
					//preparedStatement.setString(2, ordername);
					//preparedStatement.setString(3, order);
					preparedStatement.setInt(2, offset);
					preparedStatement.setInt(3, limit);
				}
				else if(!engine_id.equals("")&&(model==null||model.equals(""))){
					sql="select * from reg_device where engine_id=?";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, engine_id);
					ResultSet tmprs = preparedStatement.executeQuery();
					
					while (tmprs.next()) {
						size = size + 1;	
					}
					tmprs.close();
					
					sql = "select * from reg_device where engine_id=? order by "+ ordername + " "+ order +" limit ?,?";
					System.out.println("3");
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, engine_id);
					//preparedStatement.setString(2, ordername);
					//preparedStatement.setString(3, order);
					preparedStatement.setInt(2, offset);
					preparedStatement.setInt(3, limit);
				}
				else{
					sql="select * from reg_device where engine_id=? and model=?";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, engine_id);
					preparedStatement.setString(2, model);
					ResultSet tmprs = preparedStatement.executeQuery();
					
					while (tmprs.next()) {
						size = size + 1;	
					}
					tmprs.close();
					
					System.out.println("4");
					sql = "select * from reg_device where engine_id=? and model=? order by "+ ordername + " "+ order +" limit ?,?";
					preparedStatement = conn.prepareStatement(sql);
					preparedStatement.setString(1, engine_id);
					preparedStatement.setString(2, model);
					//preparedStatement.setString(3, ordername);
					//preparedStatement.setString(4, order);
					preparedStatement.setInt(3, offset);
					preparedStatement.setInt(4, limit);
				}
			}
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
		}
		catch (SQLException e1) {
			e1.printStackTrace();
		}
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

}
