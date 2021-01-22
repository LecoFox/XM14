package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.PrivilegeDao;
import com.dao.RegVehicleDao;
import com.dao.UserDao;
import com.model.Privilege;
import com.model.RegVehicle;
import com.utils.BaseServlet;
import com.utils.DataBaseUtil;
/**
 * Servlet implementation class PrivilegeSertlet
 */
@WebServlet("/Privilege")
public class PrivilegeServlet extends BaseServlet {

		private static final long serialVersionUID = 1L;
		//private PrivilegeDao privilegeService;
		public void getPrivilegeByUId(HttpServletRequest req,HttpServletResponse resp){
			//接收用户的编号
			Integer uId=Integer.parseInt(req.getParameter("userId"));
			PrivilegeDao privilegeService=new PrivilegeDao();
			//查询全部的菜单
			List<Privilege> privilegeList=privilegeService.getPrivilegeByUId(uId,1);
			
			super.java2Json(resp, privilegeList, new String[]{});
		}
		
	}

