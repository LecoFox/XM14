package com.dao;

import java.util.List;
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

import com.model.Privilege;
import com.model.User;
import com.utils.DataBaseUtil;

public class PrivilegeDao {
	@SuppressWarnings("null")
	public ArrayList<Privilege> getPrivilegeByUId(Integer uId,Integer type) {
		//如果类型为空，就查询全部类型的权限
		ArrayList<Privilege> plist=new ArrayList<Privilege>();
		Privilege pl=null;
		Connection conn = DataBaseUtil.getConn();
		if(type==null){
			String sql="select * from privilege a where a.id in ( select rp.pid from user_role ur "
					+ " left join role_privilege rp "
					+"on ur.rid=rp.rid  where ur.uid=? )";
			
			try {
	            //获取PreparedStatement对象，用于执行数据库查询
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setLong(1, uId);
	            //执行查询获取结果集
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {

	                pl = new Privilege();
	                //对用户对象进行复制
	                pl.setId(rs.getInt("id"));
	                pl.setName(rs.getString("name"));
	                pl.setUrl(rs.getString("url"));
	                pl.setType(rs.getInt("type"));
	                pl.setOrderNum(rs.getInt("orderNum"));
	                pl.setPercode(rs.getString("percode"));
	                pl.setIcon(rs.getString("icon"));
	                pl.setPid(rs.getInt("pid"));
	                
	                plist.add(pl);
	            }
	            //释放资源,后创建的先销毁
	            rs.close();
	            ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DataBaseUtil.closeConn(conn);
	        }
			
			
		}else{
			//查询指定类型的权限
			String sql="select * from privilege a where a.id in ( select rp.pid from user_role ur "
					+ " left join role_privilege rp "
					+"on ur.rid=rp.rid  where ur.uid=? ) and a.type=?";
			try {
	            //获取PreparedStatement对象，用于执行数据库查询
	            PreparedStatement ps = conn.prepareStatement(sql);
	            ps.setLong(1, uId);
	            ps.setLong(2, type);
	            //执行查询获取结果集
	            ResultSet rs = ps.executeQuery();
	            while (rs.next()) {

	                pl = new Privilege();
	                //对用户对象进行复制
	                pl.setId(rs.getInt("id"));
	                pl.setName(rs.getString("name"));
	                pl.setUrl(rs.getString("url"));
	                pl.setType(rs.getInt("type"));
	                pl.setOrderNum(rs.getInt("orderNum"));
	                pl.setPercode(rs.getString("percode"));
	                pl.setIcon(rs.getString("icon"));
	                pl.setPid(rs.getInt("pid"));
	                
	                plist.add(pl);
	            }
	            //释放资源,后创建的先销毁
	            rs.close();
	            ps.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DataBaseUtil.closeConn(conn);
	        }
		}
		return plist;
	}
	
}
