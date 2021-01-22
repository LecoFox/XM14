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
import com.dao.RoleDao;
import com.dao.UserRoleDao;
import com.dao.UserDao;
import com.model.RegVehicle;
import com.model.Role;
import com.utils.BaseServlet;
import com.utils.DataBaseUtil;

/**
 * Servlet implementation class RoleServlet
 */
@WebServlet("/Role")
public class RoleServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String toList(){
		return "role";
	}
	
	public void add(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");
        //获取车辆登记信息
        String username = req.getParameter("username");
        String role = req.getParameter("role");
        
        System.out.println("获取车辆登记信息成功");
        //实例化RegVehicleDao对象
        UserRoleDao dao = new UserRoleDao();
        JSONObject json = new JSONObject();
        if (!username.equals("请选择")) {
        	if (!role.equals("请选择")){
        		if(dao.urAvailable(username,role)){
					dao.saveUr(username,role);
					try {
						json.put("result_msg", "角色分配成功"); // ���������������ó�"error"��
						json.put("result_code", 200); // ����0��ʾ������������0�ͱ�ʾ�д���������������
						// System.out.println("�����õ���json�ǣ�"+json.toString());
						
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}
				else {
					try {
						json.put("result_msg", "该用户已有该角色！注册失败"); // ���������������ó�"error"��
						json.put("result_code", 0); // ����0��ʾ������������0�ͱ�ʾ�д���������������
						// System.out.println("�����õ���json�ǣ�"+json.toString());
						
					} catch (JSONException e1) {
						e1.printStackTrace();
					}
				}
        	}
        	else{
        		try {
    				json.put("result_msg", "选择角色名！<br>注册失败！<br>"); // ���������������ó�"error"��
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

        String role = request.getParameter("role");
        String uid = request.getParameter("uid");
        System.out.println("uid:"+uid);
        System.out.println("role:"+role);
        
        UserRoleDao dao=new UserRoleDao();
        JSONObject json = new JSONObject();
        try{
        	if(dao.Update(uid,role)){
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
        
        UserRoleDao dao=new UserRoleDao();
        String id=request.getParameter("uid");
        String role=request.getParameter("role");
        System.out.println("-------------delete Vehicle------------");
        System.out.println(id);
        System.out.println(role);
        System.out.println("-------------delete Vehicle------------");

        JSONObject json = new JSONObject();
        try{
        	if(dao.delete(id,role)){
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
	public void listrolename(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    // 连接数据库
	    // 执行查询查询一级列表内容
	    
	    RoleDao dao = new RoleDao();
	    JSONArray res;
		try {
			res = dao.SelectRole();
			System.out.println(res.toString());
			PrintWriter out = response.getWriter();
	        out.println(res.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
		String role=request.getParameter("role");
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
		System.out.println("role:"+role);
		System.out.println("order:"+order);
		System.out.println("ordername:"+ordername);
		//String show=request.getParameter("show");
		System.out.println("-------Search---------");
		List jsonList = new ArrayList();
		int size=0;
		try {
			Connection conn = DataBaseUtil.getConn();
			//Statement statement = conn.createStatement();
			// ����sql��䣬���ݴ��ݹ����Ĳ�ѯ��������
			String sql="";
			PreparedStatement preparedStatement=null;
			
			if((username==null||username.equals(""))&& (role==null||role.equals("")))
			{
				sql="select u.username,u.id,a.name as role ,a.description,a.creator "
						+ "from tb_user u, (select username as creator,ur.uid,role.name,role.description "
						+ "from user_role ur,role ,tb_user where rid=role.id and ur.create_by=tb_user.id) a "
						+ "where u.id=a.uid ";
				preparedStatement = conn.prepareStatement(sql);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				sql="select u.username,u.id,a.name as role ,a.description,a.creator "
						+ "from tb_user u, (select username as creator,ur.uid,role.name,role.description "
						+ "from user_role ur,role ,tb_user where rid=role.id and ur.create_by=tb_user.id) a "
						+ "where u.id=a.uid "
						+ "order by " + ordername + " "+ order +" limit ?,?";
				//sql = "select * from tb_user order by "+ ordername + " "+ order +" limit ?,?";
				System.out.println("1");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, offset);
				preparedStatement.setInt(2, limit);
				
			}
			else if(!username.equals("")&&(role==null||role.equals(""))){
				sql="select u.username,u.id,a.name as role ,a.description,a.creator "
						+ "from tb_user u, (select username as creator,ur.uid,role.name,role.description "
						+ "from user_role ur,role ,tb_user where rid=role.id and ur.create_by=tb_user.id) a "
						+ "where u.id=a.uid and username=? ";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				sql="select u.username,u.id,a.name as role ,a.description,a.creator "
						+ "from tb_user u, (select username as creator,ur.uid,role.name,role.description "
						+ "from user_role ur,role ,tb_user where rid=role.id and ur.create_by=tb_user.id) a "
						+ "where u.id=a.uid and username=? "
						+ "order by " + ordername + " "+ order +" limit ?,?";
				System.out.println("2");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, username);
				//preparedStatement.setString(2, ordername);
				//preparedStatement.setString(3, order);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
			}
			else if(!role.equals("")&&(username==null||username.equals(""))){
				sql="select u.username,u.id,a.name as role ,a.description,a.creator "
						+ "from tb_user u, (select username as creator,ur.uid,role.name,role.description "
						+ "from user_role ur,role ,tb_user where rid=role.id and ur.create_by=tb_user.id) a "
						+ "where u.id=a.uid and a.name=? ";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, role);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				sql="select u.username,u.id,a.name as role ,a.description,a.creator "
						+ "from tb_user u, (select username as creator,ur.uid,role.name,role.description "
						+ "from user_role ur,role ,tb_user where rid=role.id and ur.create_by=tb_user.id) a "
						+ "where u.id=a.uid and a.name=? "
						+ "order by " + ordername + " "+ order +" limit ?,?";
				System.out.println("3");
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, role);
				//preparedStatement.setString(2, ordername);
				//preparedStatement.setString(3, order);
				preparedStatement.setInt(2, offset);
				preparedStatement.setInt(3, limit);
			}
			else{
				sql="select u.username,u.id,a.name as role ,a.description,a.creator "
						+ "from tb_user u, (select username as creator,ur.uid,role.name,role.description "
						+ "from user_role ur,role ,tb_user where rid=role.id and ur.create_by=tb_user.id) a "
						+ "where u.id=a.uid and a.name=? and username=? ";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, role);
				preparedStatement.setString(2, username);
				ResultSet tmprs = preparedStatement.executeQuery();
				
				while (tmprs.next()) {
					size = size + 1;	
				}
				tmprs.close();
				System.out.println("4");
				sql="select u.username,u.id,a.name as role ,a.description,a.creator "
						+ "from tb_user u, (select username as creator,ur.uid,role.name,role.description "
						+ "from user_role ur,role ,tb_user where rid=role.id and ur.create_by=tb_user.id) a "
						+ "where u.id=a.uid and a.name=? and username=? "
						+ "order by " + ordername + " "+ order +" limit ?,?";
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, role);
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
					map.put("id", rs.getString("id"));
					map.put("role", rs.getString("role"));
					map.put("description", rs.getString("description"));
					map.put("creator", rs.getString("creator"));
					jsonList.add(map);
			}
			preparedStatement.close();
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
