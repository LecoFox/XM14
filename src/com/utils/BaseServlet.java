package com.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * ͨ�õ�servlet
 * @author ����������
 *
 */
@SuppressWarnings("all")
public class BaseServlet extends HttpServlet {
	//����ǰ׺
	private static String prefix;
	//�����׺
	private static String suffix;
	{
		prefix="/";
		suffix=".jsp";
	}
	/**
	 * �ύ����  ִ�з���ʱ�� User?method=login, ������Ҫд req,resp
	 * 		 ��ת��ĳ��ҳ�棬 User?jsp=toLogin, ��������Ҫд req,resp
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			req.setCharacterEncoding("utf-8");
			
			//��ʶ 0�Ƿ���,1�ǽ��� 2 ��Ĭ��
			int flag=0;
			String method = req.getParameter("method");
			//�ж����methodΪ��
			if(method == null) {
				method=req.getParameter("jsp");
				if(method==null){
					method = "execute";
					flag=2;
				}else{
					flag=1;
				}
			}
			//�õ���ǰ�����servlet����ǰ���е���,�õ�UserServlet���Class
			Class clazz = this.getClass();
			
			String result="";
			
			if(flag==0||flag==2){
				//���ݵõ����ݹ��������ƣ������ƶ�Ӧ�ķ���ȥִ��
				Method m1 = clazz.getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
				//�÷���ִ��
				result= (String) m1.invoke(clazz.newInstance(), req,resp);
			}else if(flag==1){
				//���ݵõ����ݹ��������ƣ������ƶ�Ӧ�ķ���ȥִ��
				Method m1 = clazz.getMethod(method);
				//�÷���ִ��
				result= (String) m1.invoke(clazz.newInstance());
			}else{
				//��Ĭ�ϵ�
				result="";
			}
			if(result != null) {
				//ת������
				if(result.indexOf("?method=")==-1){
					result=prefix+result+suffix;  
				}
				req.getRequestDispatcher(result).forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * ��ָ��Java����תΪjson������Ӧ���ͻ���ҳ��
	 * @param o
	 * @param exclueds
	 */
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
	
	
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	
}
