package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.utils.DataBaseUtil;

public class RoleDao {
	public JSONArray SelectRole() throws JSONException{
		System.out.println("selectrole函数");
		JSONArray array = new JSONArray();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select * from role";
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
