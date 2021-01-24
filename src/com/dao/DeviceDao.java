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

import com.model.Device;
import com.model.User;
import com.utils.DataBaseUtil;

public class DeviceDao {
	//查询数据库信息

	/**
	 * 在用户提交注册信息是，需要判断该驾驶证号是否存在
	 *
	 * @param driver_id
	 * @return
	 */
	
	public JSONArray SelectOnesDevice(String username) throws JSONException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
        Connection conn = DataBaseUtil.getConn();
        String sql = "SELECT DISTINCT device_id,car_name FROM allocation WHERE username = ?";
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
	
	public JSONArray SelectAllDevice() throws JSONException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
        Connection conn = DataBaseUtil.getConn();
        String sql = "SELECT DISTINCT device_id,car_name FROM bcx_data";
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
	
	public boolean deviceAvailable(String deviceid) {
        Connection conn = DataBaseUtil.getConn();
        //根据指定的用户名查询信息
        String sql = "select * from allocation where device_id = ?";

        try {
            //获取PreparedStatement对象，用于执行数据库查询
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, deviceid);
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
	
	public void saveAlc(Device dv) {
        //获取数据库连接
        Connection conn = DataBaseUtil.getConn();
        //插入信息的sql语句
        	String sql = "insert into allocation(device_id,username,adate) values(?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, dv.getDeviceId());
                ps.setString(2, dv.getUsername());
                ps.setString(3, dv.getAdate());
                //执行更新操作
                System.out.println(sql);
                ps.executeUpdate();
                //释放资源
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
	
	public boolean delete(String deviceid){
        Connection conn = DataBaseUtil.getConn();
        String sql = "delete from allocation where device_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, deviceid);
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
        return false;
    }
	public boolean Update(String username, String deviceid) {
		// TODO Auto-generated method stub
		Connection conn = DataBaseUtil.getConn();
        String sql = "update allocation set username=? where device_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, deviceid);
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
        return false;
	}

	public JSONArray SelectAllUnregDevice() throws JSONException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
        Connection conn = DataBaseUtil.getConn();
        String sql = "SELECT DISTINCT Device_id FROM bcx_data WHERE Device_id NOT IN (SELECT Device_id FROM reg_device)";
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

	public JSONArray SelectOnesUnregDevice(String userName) throws JSONException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
        Connection conn = DataBaseUtil.getConn();
        String sql = "SELECT DISTINCT Device_id FROM allocation WHERE username=? and Device_id NOT IN (SELECT Device_id FROM reg_device)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            //执行查询获取结果集
            ps.setString(1, userName);
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

	
}

