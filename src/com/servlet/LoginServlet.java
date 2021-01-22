package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PrivilegeDao;
import com.dao.UserDao;
import com.model.Privilege;
import com.model.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Created by pc on 17-5-11.
 */

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");

		String username = req.getParameter("username");
		String password = req.getParameter("password");
		UserDao userDao = new UserDao();
		//根据密码查询用户
		User user  = userDao.login(username, password);
		//判断user是否为空
		
		
			if (user != null) {
				//将用户的对象放到session中
				HttpSession session =req.getSession();
				session.setAttribute("user", user);
				session.setAttribute("username", username);
				PrivilegeDao pldao=new PrivilegeDao();
				ArrayList<Privilege> privilegeList= pldao.getPrivilegeByUId(user.getId(),2);
				
				ArrayList<String> priCodeList=new ArrayList<String>();
				for(Privilege pri:privilegeList){
					if(pri.getPercode()!=null){
						//放置标识
						priCodeList.add(pri.getPercode());
					}
				
				}
				JsonConfig jsonConfig = new JsonConfig();
				JSONArray objData=JSONArray.fromObject(priCodeList,jsonConfig);
				JSONObject objMap=new JSONObject();
				objMap.put("data",objData);
				//转换成 json 字符串对象
				session.setAttribute("privilegeList",objMap.toString());
				req.setAttribute("flag", "1");
				//转发到result.jsp页面
			}else {
				//登录失败
				
				req.setAttribute("info","用户名或密码错误！");
				req.setAttribute("flag", "0");
			}
			req.setAttribute("type", "login");
			req.getRequestDispatcher("message.jsp").forward(req, resp);
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}