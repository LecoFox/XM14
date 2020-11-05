package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.model.RegVehicle;
import com.utils.DataBaseUtil;

public class RegVehicleDao {
	public JSONArray SelectUnregVeh() throws JSONException {//查询未注册车辆
        //实例化一个车辆对象
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
	public void saveVehicle(RegVehicle vehicle) {//注册车辆
        //获取数据库连接
        Connection conn = DataBaseUtil.getConn();
        //插入信息的sql语句
        String sql = "insert into reg_device(carimg,owner,chepai,brand,device_id,engine_id,model) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicle.getCarImg());
            ps.setString(2, vehicle.getOwner());
            ps.setString(3, vehicle.getChepai());
            ps.setString(4, vehicle.getBrand());
            ps.setString(5, vehicle.getDevice_id());
            ps.setString(6,vehicle.getEngine_id());
            ps.setString(7, vehicle.getModel());
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
