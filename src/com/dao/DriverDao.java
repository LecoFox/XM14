	package com.dao;

import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.model.Driver;
import com.utils.DataBaseUtil;

public class DriverDao {
	//查询数据库信息

	/**
	 * 在用户提交注册信息是，需要判断该驾驶证号是否存在
	 *
	 * @param driver_id
	 * @return
	 */
	public boolean DriverIdAvailable(String driver_id) {
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select * from driver where driver_id = ?";

		try {
			//获取PreparedStatement对象，用于执行数据库查询
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, driver_id);
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

	public JSONArray SelectOnesDriver(String username) throws JSONException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
        Connection conn = DataBaseUtil.getConn();
        String sql = "SELECT DISTINCT name,driver_id FROM driver WHERE creator=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
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
	
	/**
	 * 在用户提交注册信息时，如果注册成功需要将，需要将用户注册的信息存入数据库
	 */
	public void saveDriver(Driver driver) {
		//获取数据库连接
		Connection conn = DataBaseUtil.getConn();
		//插入信息的sql语句
		String sql = "insert into driver(driver_id,name,sex,birthday,validity_period,phone_number,email,creator) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, driver.getDriverId());
			ps.setObject(2, driver.getName());
			ps.setObject(3, driver.getSex());
			ps.setObject(4, driver.getBirthday());
			ps.setObject(5, driver.getValidityPeriod());
			ps.setObject(6, driver.getPhoneNumber());
			ps.setObject(7, driver.getEmail());
			ps.setObject(8, driver.getCreator());
			//执行更新操作
			System.out.println(sql);
			ps.executeUpdate();
			//释放资源
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

