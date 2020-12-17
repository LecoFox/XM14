package com.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.model.User;
import com.utils.DataBaseUtil;

public class UserDao {
    //查询数据库信息

    /**
     * 在用户提交注册信息是，需要判断该用户名是否存在
     *
     * @param username
     * @return
     */
    public boolean userAvailable(String username) {
        Connection conn = DataBaseUtil.getConn();
        //根据指定的用户名查询信息
        String sql = "select * from tb_user where username = ?";

        try {
            //获取PreparedStatement对象，用于执行数据库查询
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            //执行查询获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                //如果没有此数据，证明该用户名可用
                return true;
            }
            //释放资源,后创建的先销毁
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }

        return false;
    }

    /**
     * 在用户提交注册信息时，如果注册成功需要将，需要将用户注册的信息存入数据库
     */
    public void saveUser(User user) {
        //获取数据库连接
        Connection conn = DataBaseUtil.getConn();
        //插入信息的sql语句
        	String sql = "insert into tb_user(username,password,sex,question,answer,email) values(?,?,?,?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getSex());
                ps.setString(4, user.getQuestion());
                ps.setString(5, user.getAnswer());
                ps.setString(6, user.getEmail());
                //执行更新操作
                System.out.println(sql);
                ps.executeUpdate();
                //释放资源
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * 注册成功后，用户既可通过注册的用户及密码进行登录，对于程序而言，此操作实质是根据
     * 用户所提供的用户名及密码在数据库进行查询，如果查询成功，则登录成功
     */
    public User login(String username, String password) {
        //实例化一个用户对象
        User user =null;
        Connection conn = DataBaseUtil.getConn();
        String sql = "select * from tb_user where username = ? and password = ?";
        String newsql = "insert into login(username,login_time) value(?,?)";
        String thirdsql = "update tb_user set login_time = ? where username=?";
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");//  
        Date date = new Date();// 获取当前时间
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement newps = conn.prepareStatement(newsql);
            PreparedStatement thirdps = conn.prepareStatement(thirdsql);
            ps.setString(1, username);
            ps.setString(2, password);
            newps.setString(1,username);
            newps.setString(2, sdf.format(date));
            thirdps.setString(1, sdf.format(date));
            thirdps.setString(2, username);
            //执行查询获取结果集
            ResultSet rs = ps.executeQuery();
            
            //判断结果集是否有效,如过有效，则对用户进行赋值
            while (rs.next()) {

                user = new User();
                //对用户对象进行复制
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getString("sex"));
                user.setQuestion(rs.getString("question"));
                user.setAnswer(rs.getString("answer"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
                newps.executeUpdate();
                thirdps.executeUpdate();
            }
            //释放资源
            rs.close();
            ps.close();
            newps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }

        return user;
    }
    
    public User pwdupdate(String username, String password, String new_pwd1, String new_pwd2) {
        //实例化一个用户对象
        User user =null;
        Connection conn = DataBaseUtil.getConn();
        String sql = "select * from tb_user where username = ? and password = ?";
        String sql1= "update tb_user set password = ? where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps.setString(1, username);
            ps.setString(2, password);
            ps1.setString(1, new_pwd1);
            ps1.setString(2, username);
            //执行查询获取结果集
            ResultSet rs = ps.executeQuery();

            //判断结果集是否有效,如果有效，则准备对密码进行修改
            while (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                //判断两次新密码是否一致
                if(new_pwd1.equals(new_pwd2)){
                	user.setPassword(rs.getString("password"));
                	ps1.executeUpdate();
                }
            }
            //释放资源
            rs.close();
            ps1.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }
        return user;
    }
    
    public boolean delete(String username){
        Connection conn = DataBaseUtil.getConn();
        String sql = "delete from tb_user where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            //执行更新操作
            System.out.println(sql);
            ps.executeUpdate();
            //释放资源
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }
        return true;
    }

    public ArrayList<User> getAllUser(){
    	ArrayList<User> list = new ArrayList<User>();
    	Connection conn = DataBaseUtil.getConn();
        //根据指定的用户名查询信息
        String sql = "select * from tb_user";

        try {
            //获取PreparedStatement对象，用于执行数据库查询
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //执行查询获取结果集
            ResultSet rs = preparedStatement.executeQuery();
            User user=null;
            while (rs.next()) {
                //如果没有此数据，证明该用户名可用
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setSex(rs.getString("sex"));
                user.setQuestion(rs.getString("question"));
                user.setAnswer(rs.getString("answer"));
                user.setEmail(rs.getString("email"));
                user.setType(rs.getString("type"));
                list.add(user);
            }
            //释放资源,后创建的先销毁
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }
    	return list;
    }
    public JSONArray getLoginStatus(String username) throws JSONException{
		JSONArray array = new JSONArray();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select * from login where username=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			//执行查询获取结果集
			ResultSet rs = preparedStatement.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData(); 
            int columnCount = metaData.getColumnCount();
            
            //将结果集转换为jsonarray
            while (rs.next()) {
            	JSONObject jsonObj = new JSONObject();
            	for (int i = 1; i <= columnCount; i++) { 
                    String columnName =metaData.getColumnLabel(i); 
                    String value = rs.getString(columnName);
                    if(columnName.contains("time")){
                    	value = value.substring(0,value.length()-2);
                    }
                    jsonObj.put(columnName, value);
                    //System.out.println(jsonObj);
                }  
                array.put(jsonObj); 
            }
			//释放资源,后创建的先销毁
			rs.close();
			preparedStatement.close();
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JSONArray getTotalLoin() throws JSONException{
		JSONArray array = new JSONArray();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select * from tb_user where login_time + interval 30 MINUTE > ?";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
	        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");  
	        Date date = new Date();// 获取当前时间 
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, sdf.format(date));
			//执行查询获取结果集
			ResultSet rs = preparedStatement.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData(); 
            int columnCount = metaData.getColumnCount();
            
            //将结果集转换为jsonarray
            while (rs.next()) {
            	JSONObject jsonObj = new JSONObject();
            	for (int i = 1; i <= columnCount; i++) { 
                    String columnName =metaData.getColumnLabel(i); 
                    String value = rs.getString(columnName);
                    if(columnName.contains("time")){
                    	value = value.substring(0,value.length()-2);
                    }
                    jsonObj.put(columnName, value);
                    //System.out.println(jsonObj);
                }  
                array.put(jsonObj); 
            }
			//释放资源,后创建的先销毁
			rs.close();
			preparedStatement.close();
			return array;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public JSONArray SelectUser() throws JSONException{
		System.out.println("selectuser函数");
		JSONArray array = new JSONArray();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select * from tb_user";
		try {
            PreparedStatement ps = conn.prepareStatement(sql);
            //执行查询获取结果集
            ResultSet rs = ps.executeQuery();
            
            ResultSetMetaData metaData = rs.getMetaData(); 
            int columnCount = metaData.getColumnCount();
            
            //将结果集转换为jsonarray
            while (rs.next()) {
            	JSONObject jsonObj = new JSONObject();
            	for (int i = 1; i <= columnCount; i++) { 
                    String columnName =metaData.getColumnLabel(i); 
                    String value = rs.getString(columnName); 
                    jsonObj.put(columnName, value);
                }  
                array.put(jsonObj); 
            }
            System.out.println(array);
            //释放资源
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }

        return array;
	}
}