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

import com.dao.RegVehicleDao;
import com.dao.UserDao;
import com.model.RegVehicle;
import com.utils.BaseServlet;
import com.utils.DataBaseUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public String toList(){
		return "showall";
	}
	
	protected void add(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
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
        String driver_id = "";
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
					if(vehicleDao.engineAvailable(engine_id)){
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
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String question = request.getParameter("question");
        String email = request.getParameter("email");
        String type = request.getParameter("type");
        String username = request.getParameter("username");
        System.out.println("question:"+question);
        System.out.println("email:"+email);
        System.out.println("username:"+username);
        System.out.println("type:"+type);
        
        UserDao dao=new UserDao();
        JSONObject json = new JSONObject();
        try{
        	if(dao.UpdateUsr(question, email,type,username)){
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
 
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        
        UserDao dao=new UserDao();
        String username=request.getParameter("username");
        System.out.println("-------------delete Vehicle------------");
        System.out.println(request.getParameter("username"));
        System.out.println("-------------delete Vehicle------------");

        JSONObject json = new JSONObject();
        try{
        	if(dao.delete(username)){
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
	
	public void list(HttpServletRequest request, HttpServletResponse response)
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
			
			if(username.equals("") && type.equals(""))
			{
				sql="select * from tb_user";
				preparedStatement = conn.prepareStatement(sql);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				
				sql = "select * from tb_user order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("1");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, offset);
				preparedStatement.setInt(2, limit);
				
			}
			else if(!username.equals("")&&type.equals("")){
				sql="select * from tb_user where username=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				
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
				
				sql="select * from tb_user where type=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, type);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				
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
				sql="select * from tb_user where type=? and username=?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, type);
				preparedStatement.setString(2, username);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				
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
	
	public void down(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    // 连接数据库
	    // 执行查询查询一级列表内容
	    
	    UserDao user = new UserDao();
	    JSONArray res;
		try {
			res = user.SelectUser();
			System.out.println(res.toString());
			PrintWriter out = response.getWriter();
	        out.println(res.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
