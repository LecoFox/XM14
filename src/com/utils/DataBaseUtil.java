package com.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class DataBaseUtil {

    //连接数据库
    public static Connection getConn() {
        Connection conn = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/xm14?user=XM14&password=123456&useUnicode=true&characterEncoding=UTF-8";
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                //System.out.println(1123);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //关闭数据库
    public static void closeConn(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void java2Json(HttpServletResponse resp,Object o ,String[] exclueds){
		JsonConfig jsonConfig = new JsonConfig();
		//ָ����Щ���Բ���Ҫתjson
 		jsonConfig.setExcludes(exclueds);
		JSONObject objData=JSONObject.fromObject(o,jsonConfig);
		JSONObject objMap=new JSONObject();
		objMap.put("data",objData);
		objMap.put("status",true);
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().print(objMap.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ָ��Java����תΪjson������Ӧ���ͻ���ҳ��
	 * @param o
	 * @param exclueds
	 */
	public void java2Json(HttpServletResponse resp,List o ,String[] exclueds){
		JsonConfig jsonConfig = new JsonConfig();
		//ָ����Щ���Բ���Ҫתjson
		jsonConfig.setExcludes(exclueds);
		JSONArray objData=JSONArray.fromObject(o,jsonConfig);
		JSONObject objMap=new JSONObject();
		objMap.put("data",objData);
		objMap.put("status",true);
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().print(objMap.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��״̬���ص�ǰ̨��ͨ������ӣ�ɾ�������µĲ���������������������롣
	 * @param o
	 * @param exclueds
	 */
	public void map2Json(HttpServletResponse resp,String ... code){
		JsonConfig jsonConfig = new JsonConfig();
		//ָ����Щ���Բ���Ҫתjson
		JSONObject objMap=new JSONObject();
		if(code==null||code.length<1){
			objMap.put("status",true);
		}else{
			objMap.put("status",false);
			objMap.put("error_code",code[0]);
		}
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().print(objMap.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �����Ƿ�ɹ���ֻ����״̬
	 * @param o
	 * @param exclueds
	 */
	public void boolean2Json(HttpServletResponse resp,boolean flag){
		JsonConfig jsonConfig = new JsonConfig();
		//ָ����Щ���Բ���Ҫתjson
		JSONObject objMap=new JSONObject();
		objMap.put("status",true);
		objMap.put("flag",flag);
		resp.setContentType("text/json;charset=utf-8");
		try {
			resp.getWriter().print(objMap.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ������Ĳ�����ת������Ӧ�� JavaBean 
	 * @param req
	 * @param clazz
	 * @return
	 */
	public <T> T reqParamToBean(HttpServletRequest req,Class <T> clazz){
		T t=null;
		try {
			t = clazz.newInstance();
			Map<String ,String []> paramMap=req.getParameterMap();
			BeanUtils.populate(t,paramMap);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		return t;
	}
}

