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
        String sql = "SELECT DISTINCT device_id FROM allocation WHERE username = ?";
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
	
}

