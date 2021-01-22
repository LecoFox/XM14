package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.model.RegVehicle;
import com.model.User;
import com.model.Vehicle;
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
	
	public List<RegVehicle> getRegVehicleAll() {
		List<RegVehicle> list = new ArrayList<RegVehicle>();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select * from reg_device";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			//执行查询获取结果集
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				RegVehicle regVehicle = new RegVehicle();
				regVehicle.setDevice_id(resultSet.getString("device_id"));
				//regVehicle.setCarImg(resultSet.getString("carImg"));
				regVehicle.setOwner(resultSet.getString("owner"));
				regVehicle.setChepai(resultSet.getString("chepai"));
				regVehicle.setBrand(resultSet.getString("brand"));
				regVehicle.setModel(resultSet.getString("model"));
				regVehicle.setEngine_id(resultSet.getString("engine_id"));
				regVehicle.setDriver_id(resultSet.getString("driver_id"));
				list.add(regVehicle);
			}
			//释放资源,后创建的先销毁
			resultSet.close();
			preparedStatement.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void saveVehicle(RegVehicle vehicle) {//注册车辆
        //获取数据库连接
        Connection conn = DataBaseUtil.getConn();
        //插入信息的sql语句
        //String sql = "insert into reg_device(carimg,owner,chepai,brand,device_id,engine_id,model) values(?,?,?,?,?,?,?)";
        String sql = "insert into reg_device(owner,chepai,brand,device_id,engine_id,model,driver_id) values(?,?,?,?,?,?,NULL)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            //ps.setString(1, vehicle.getCarImg());
            ps.setString(1, vehicle.getOwner());
            ps.setString(2, vehicle.getChepai());
            ps.setString(3, vehicle.getBrand());
            ps.setString(4, vehicle.getDevice_id());
            ps.setString(5, vehicle.getEngine_id());
            ps.setString(6, vehicle.getModel());
            //ps.setString(7, vehicle.getDriver_id());
            //执行更新操作
            System.out.println(sql);
            ps.executeUpdate();
            System.out.println("注册车辆成功！");
            //释放资源
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public void allocateVehicle(RegVehicle vehicle) {//分配车辆
        //获取数据库连接
        Connection conn = DataBaseUtil.getConn();
        //插入信息的sql语句
        //String sql = "insert into reg_device(carimg,owner,chepai,brand,device_id,engine_id,model) values(?,?,?,?,?,?,?)";
        String sql = "update reg_device set driver_id = ? where engine_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vehicle.getDriver_id());
            ps.setString(2, vehicle.getEngine_id());
            //执行更新操作
            System.out.println(sql);
            ps.executeUpdate();
            System.out.println("注册车辆成功！");
            //释放资源
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public JSONArray getOverSpeed(String StartTime, String EndTime, String setSpeed) throws JSONException{
		JSONArray array = new JSONArray();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select DISTINCT device_id,MAX(speed) as maxspeed,MIN(GPS_time) as btime,MAX(GPS_time) as etime from bcx_data where speed >= ? AND GPS_time>=? AND GPS_time<=? group by device_id";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, setSpeed);
			preparedStatement.setString(2, StartTime);
			preparedStatement.setString(3, EndTime);
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
	
	public JSONArray getOverSpeed2(String StartTime, String EndTime, String setSpeed,String username) throws JSONException{
		JSONArray array = new JSONArray();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select DISTINCT device_id,MAX(speed) as maxspeed,MIN(GPS_time) as btime,MAX(GPS_time) as etime from bcx_data natural join reg_device where speed >= ? AND GPS_time>=? AND GPS_time<=? AND owner = ? group by device_id";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, setSpeed);
			preparedStatement.setString(2, StartTime);
			preparedStatement.setString(3, EndTime);
			preparedStatement.setString(4, username);
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
	
	public JSONArray getMileage(String StartTime, String EndTime, String setDeviceId) throws JSONException{
		JSONArray array = new JSONArray();
		Connection conn = DataBaseUtil.getConn();
		//根据指定的用户名查询信息
		String sql = "select DISTINCT device_id, car_name,"
				+ " ROUND((AVG(speed) / 60 * TIMESTAMPDIFF(MINUTE,MIN(GPS_time),MAX(GPS_time))), 2) as refer_mile ,"
				+ " ROUND(((AVG(speed) / 60 * TIMESTAMPDIFF(MINUTE,MIN(GPS_time),MAX(GPS_time)))*0.08), 2) as refer_gasoline ,"
				+ " ROUND(Max(speed), 2) as maxspeed,MIN(GPS_time) as btime,MAX(GPS_time) as etime, "
				+ "ROUND((AVG(speed) / 60 * TIMESTAMPDIFF(MINUTE,MIN(GPS_time),MAX(GPS_time)) + 123.67), 2) as total_mile, "
				+ "ROUND(((AVG(speed) / 60 * TIMESTAMPDIFF(MINUTE,MIN(GPS_time),MAX(GPS_time)) + 123.67)*0.08), 2) as total_gasoline "
				+ "from bcx_data where device_id = ? AND GPS_time>=? AND GPS_time<=? group by device_id";
		System.out.println("sql语句是： " + sql);
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, setDeviceId);
			preparedStatement.setString(2, StartTime);
			preparedStatement.setString(3, EndTime);
			System.out.println("sql语句是： " + sql);
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

	public JSONArray SelectOnesDevice(String username) throws JSONException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
        Connection conn = DataBaseUtil.getConn();
        String sql = "SELECT DISTINCT car_name,device_id FROM bcx_data natural join reg_device WHERE owner=?";
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

	public JSONArray SelectOnesCar(String username) throws JSONException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
        Connection conn = DataBaseUtil.getConn();
        String sql = "SELECT DISTINCT car_name,engine_id FROM bcx_data natural join reg_device WHERE owner=?";
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
	
	public boolean engineAvailable(String engine_id) {
		Connection conn = DataBaseUtil.getConn();
        //根据指定的用户名查询信息
        String sql = "select * from reg_device where engine_id = ?";

        try {
            //获取PreparedStatement对象，用于执行数据库查询
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, engine_id);
            //执行查询获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                //如果没有此数据，证明该发动机号可用
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

	
	public boolean driverAvailable(String driver_id) {
		Connection conn = DataBaseUtil.getConn();
        //根据指定的用户名查询信息
        String sql = "select * from reg_device where driver_id = ?";

        try {
            //获取PreparedStatement对象，用于执行数据库查询
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, driver_id);
            //执行查询获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                //如果没有此数据，证明该发动机号可用
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
	
	
	public JSONArray GetAllReg() throws JSONException {
		// TODO Auto-generated method stub
		JSONArray array = new JSONArray();
        Connection conn = DataBaseUtil.getConn();
        String sql = "select * from reg_device";
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
	public boolean delete(String deviceid){
        Connection conn = DataBaseUtil.getConn();
        String sql = "delete from reg_device where device_id = ?";
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
	public boolean UpdateReg(String engineid, String driverid,String deviceid) {
        //实例化一个用户对象
        Connection conn = DataBaseUtil.getConn();
        //String sql = "select * from tb_user where username = ? and password = ?";
        String sql= "update reg_device set engine_id = ?, driver_id = ? where device_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, engineid);
            ps.setString(2, driverid);
            ps.setString(3, deviceid);
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

	public boolean deviceAvailable(String device_id) {
		Connection conn = DataBaseUtil.getConn();
        //根据指定的用户名查询信息
        String sql = "select * from reg_device where device_id = ?";

        try {
            //获取PreparedStatement对象，用于执行数据库查询
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, device_id);
            //执行查询获取结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                //如果没有此数据，证明该发动机号可用
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
	
	
}
