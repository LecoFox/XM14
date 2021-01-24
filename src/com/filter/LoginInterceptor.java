package com.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PrivilegeDao;
import com.model.Privilege;
import com.model.User;
import com.servlet.PrivilegeServlet;


//过滤所有的请求
@WebFilter("/*")
public class LoginInterceptor implements Filter{

	//不需要登录验证的url
	private static List<String> noLoginValidateUrl;
	//不需要权限验证的url
	private static List<String> noPriValidateUrl;
	
	//跳转到的登录页面
	private static String LOGIN_URL;
	//没有权限的界面
	private static String NO_PRIVILEGE_URL;
	
	static{
		noLoginValidateUrl=new ArrayList<String>();
		
		//静态资源
		noLoginValidateUrl.add("/css/");
		noLoginValidateUrl.add("/fonts/");
		noLoginValidateUrl.add("/images/");
		noLoginValidateUrl.add("/img/");
		noLoginValidateUrl.add("/js/");
		noLoginValidateUrl.add("jquery-1.8.3.min.js");
		noLoginValidateUrl.add("carImg.png");
		noLoginValidateUrl.add("car_bd.png");
		noLoginValidateUrl.add("car_Img.png");
		noLoginValidateUrl.add("end.png");
		noLoginValidateUrl.add("start.png");
		noLoginValidateUrl.add("stop.png");
		noLoginValidateUrl.add("stopImg.png");
		noLoginValidateUrl.add("warningImg.png");
		noLoginValidateUrl.add("warningImg1.png");
		noLoginValidateUrl.add("warningImg2.png");
		//登录页面
		noLoginValidateUrl.add("/XM14/login.jsp");
		//登录方法
		noLoginValidateUrl.add("LoginServlet");
		noLoginValidateUrl.add("message.jsp");
		noLoginValidateUrl.add("register.jsp");
		noLoginValidateUrl.add("registermessage.jsp");
		noLoginValidateUrl.add("RegServlet");
		noLoginValidateUrl.add("BcxServlet");
		
		noLoginValidateUrl.add("VerificationServlet");
		noLoginValidateUrl.add("pdw_update.jsp");
		
		noPriValidateUrl=new ArrayList<String>();
		
		noPriValidateUrl.add("front.jsp");
		//查询权限
		noLoginValidateUrl.add("Record?method=list");
		noPriValidateUrl.add("PrivilegeServlet?method=getPrivilegeByUId");
		noPriValidateUrl.add("User?method=down");
		noPriValidateUrl.add("Device?method=selectunreg");
		noPriValidateUrl.add("Device?method=listunalc");
		noPriValidateUrl.add("Device?method=select");
		noPriValidateUrl.add("SelectVehicleServlet");
		noPriValidateUrl.add("SearchTrack");
		noPriValidateUrl.add("loginstatus");
		//退出
		//noPriValidateUrl.add("/User?method=logout");
		
		LOGIN_URL="login.jsp";
		
		NO_PRIVILEGE_URL="noPrivilege.jsp";
	}
	
	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		if(arg0 instanceof HttpServletRequest){
			//请求。
			HttpServletRequest req=(HttpServletRequest)arg0;
			
			//获取Session
			HttpSession session=req.getSession();
			
			//请求路径
			String uri=req.getRequestURI();
			//请求参数
			String reqParam=req.getQueryString();
			
			//真正的请求路径
			String realPath="";
			
			if(reqParam==null||"".equals(reqParam)){
				realPath=uri;
			}else{
				realPath=uri+"?"+reqParam;
			}
			
			System.out.println("地址是:"+realPath);
			
			//验证是否在 不需要验证登录的url里面
			if(isContain(realPath,1)){
				arg2.doFilter(arg0, arg1);
				return ;
			}
			//如果为空，表示没有登录
			if(session.getAttribute("user")==null){
				req.getRequestDispatcher(LOGIN_URL).forward(req,(HttpServletResponse)arg1);
			}else{
				
				//不需要验证权限
				if(isContain(realPath,2)){
					arg2.doFilter(arg0, arg1);
					return ;
				}
				//如果不为空，表示登录了。
				PrivilegeDao ps=new PrivilegeDao();
				//重新获取全部权限 ， 需要缓存， 这儿不用缓存。
				User user=(User)session.getAttribute("user");
				List<Privilege> privilegeList=ps.getPrivilegeByUId(user.getId(),null);
				boolean isHavePri=false;
				for(Privilege pri:privilegeList){
					if(pri.getUrl()!=null){
						if(realPath.contains(pri.getUrl())){
							isHavePri=true;
							break;
						}
					}
				}
				if(isHavePri){
					//放行
					arg2.doFilter(arg0, arg1);
				}else{
					req.getRequestDispatcher(NO_PRIVILEGE_URL).forward(req,(HttpServletResponse)arg1);
				}
				
			}
			
		}
	}
	
	private boolean isContain(String realPath,int type){	
		List<String> urls;
		if(type==1){
			urls=noLoginValidateUrl;
		}else{
			urls=noPriValidateUrl;
		}
		
		boolean flag=false;
		
		for(String url:urls){
			//包括，返回-1
			if(realPath.indexOf(url)!=-1){
				flag=true;
				break;
			}
		}
		return flag;
		
	}
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		
	}
}

