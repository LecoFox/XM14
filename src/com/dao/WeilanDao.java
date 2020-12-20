package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.model.WeiLan;
import com.utils.DataBaseUtil;

public class WeilanDao {
	public JSONArray testyuejie() throws JSONException{
		JSONArray array = new JSONArray();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "SELECT x.Device_id, 6378.138*2*ASIN(SQRT(POW(SIN((x.Lat*PI()/180 - weilan.lat*PI()/180)/2),2) + COS(x.Lat*PI()/180)*COS(weilan.lat*PI()/180)*POW(SIN((x.Lon* PI()/ 180 - weilan.lon* PI()/180)/2),2))) As dis FROM bcx_data x,weilan where weilan.deviceid=x.Device_id and 6378.138*2*ASIN(SQRT(POW(SIN((x.Lat*PI()/180 - weilan.lat*PI()/180)/2),2) + COS(x.Lat*PI()/180)*COS(weilan.lat*PI()/180)*POW(SIN((x.Lon* PI()/ 180 - weilan.lon* PI()/180)/2),2)))*1000>weilan.radius";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			//preparedStatement.setString(1, setSpeed);
			//preparedStatement.setString(2, StartTime);
			//preparedStatement.setString(3, EndTime);
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
	public void savewl(WeiLan weilan) {//注册车辆
        //获取数据库连接
        Connection conn = DataBaseUtil.getConn();
        //插入信息的sql语句
        String sql = "insert into weilan(deviceid,radius,lat,lon,email) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setString(1, vehicle.getCarImg());
            ps.setString(1, weilan.getDeviceid());
            ps.setString(2, weilan.getRadius());
            ps.setString(3, weilan.getLat());
            ps.setString(4, weilan.getLon());
            ps.setString(5, weilan.getEmail());
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
