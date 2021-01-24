package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.utils.DataBaseUtil;

public class UserRoleDao {

	public boolean delete(String id, String role) {
		Connection conn = DataBaseUtil.getConn();
        String sql = "delete from user_role where  uid= ? and rid = (select id from role where name=?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, role);
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

	public boolean Update(String uid, String role) {
		Connection conn = DataBaseUtil.getConn();
        //String sql = "select * from tb_user where username = ? and password = ?";
        String sql= "update user_role set rid = (select id from role where name=?) where uid=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(2, uid);
            ps.setString(1, role);
            //执行查询获取结果集
            int rs = ps.executeUpdate();

            //判断结果集是否有效,如果有效，则准备对密码进行修改
            //释放资源
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DataBaseUtil.closeConn(conn);
        }
        return false;
	}

	public boolean urAvailable(String username, String role) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseUtil.getConn();
        //根据指定的用户名查询信息
        String sql = "select * from user_role where uid = (select id from tb_user where username=?)"
        		+ " and rid = (select id from role where name=?)";

        try {
            //获取PreparedStatement对象，用于执行数据库查询
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, role);
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

	public void saveUr(String username, String role) {
		// TODO Auto-generated method stub
		 Connection conn = DataBaseUtil.getConn();
	        //插入信息的sql语句
	        	String sql = "insert into user_role(uid,rid,create_by,create_date) "
	        			+ "(select u.id,r.id,1,CURDATE() from tb_user u,role r "
	        			+ "where u.username=? and r.name=?)";
	            try {
	                PreparedStatement ps = conn.prepareStatement(sql);
	                ps.setString(1, username);
	                ps.setString(2, role);
	                
	                //执行更新操作
	                System.out.println(sql);
	                ps.executeUpdate();
	                //释放资源
	                ps.close();
	                DataBaseUtil.closeConn(conn);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	}
}
