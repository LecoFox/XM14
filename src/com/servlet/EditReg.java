package com.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.RegVehicleDao;
import com.dao.UserDao;
import com.model.User;
import com.utils.DataBaseUtil;

/**
 * Servlet implementation class EditReg
 */
@WebServlet("/EditReg")
public class EditReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditReg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String engineid = request.getParameter("engineid");
        String driverid = request.getParameter("driverid");
        String deviceid = request.getParameter("deviceid");
        System.out.println("engineid:"+engineid);
        System.out.println("driverid:"+driverid);
        System.out.println("deviceid:"+deviceid);
        
        RegVehicleDao dao=new RegVehicleDao();
        JSONObject json = new JSONObject();
        try{
        	if(dao.UpdateReg(engineid, driverid, deviceid)){
            	json.put("result_msg", "ok"); // ���������������ó�"error"��
    			json.put("result_code", 200);
            }
            else{
            	json.put("result_msg", "error"); // ���������������ó�"error"��
    			json.put("result_code", 0);
            }
        } catch (JSONException e){
        	e.printStackTrace();
        }
        
        response.setContentType("application/json; charset=UTF-8");
        try {
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
