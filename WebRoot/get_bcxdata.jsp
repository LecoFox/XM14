<%@page contentType="text/html; charset=UTF-8" language="java"
	import="java.text.*,org.json.JSONObject,org.json.JSONArray,java.util.ArrayList,java.io.PrintWriter"
	import="java.util.HashMap,java.util.List,java.sql.*,java.util.Map,java.io.IOException"%>
<%@page import="java.net.*,java.io.*"%>
<%
	//String keyValue=request.getParameter("key_value");
	//System.out.println("收到的参数是："+keyValue);

	//说明：引入json.jar包和jdbc包，拷贝到Tomcat的common/lib下重启Tomcat或者应用的WEB-INF/lib下
	//开始链接数据库
	List jsonList = new ArrayList();
	JSONObject j=new JSONObject();
	StringBuffer sb = new StringBuffer("");
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException classnotfoundexception) {
		classnotfoundexception.printStackTrace();
	}
	//System.out.println("成功加载驱动!");

	try {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xm14?user=XM14&password=123456&useUnicode=true&characterEncoding=UTF-8");
		Statement statement = conn.createStatement();
		//System.out.println("连接数据库Ok！！！");

		//访问伴车星
		String loginSessionId=null;
		HttpURLConnection urlConn=null;
		if(session.getAttribute("login_session")==null){
			URL urlLogin=new URL("http://www.bcxgps.com/page/login/BcxLoad.action?r=g");
			urlConn = (HttpURLConnection)urlLogin.openConnection();
			String sessionValue=urlConn.getHeaderField("Set-Cookie");
			String[] sessionId = sessionValue.split(";");
			session.setAttribute("login_session", sessionId[0]);
			loginSessionId=sessionId[0];
			//System.out.println("登录了！！！！");
		}else{
			loginSessionId=(String)session.getAttribute("login_session");
		}
		URL url=new URL("http://www.bcxgps.com/page/vip/showTerminal.action?currentPage=1&type=1&pageCount=1&search=1&startType=1&line=on");
		urlConn = (HttpURLConnection)url.openConnection();
		urlConn.setRequestProperty("Cookie", loginSessionId);
		urlConn.setRequestMethod("GET");
		urlConn.connect();

		//System.out.println(loginSessionId);
		int length = urlConn.getContentLength();
		if(length>0){
			//System.out.println("======Content=====");
			BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
			String line;
			while((line = br.readLine())!=null){
				sb.append(line);
			}
			br.close();
			//System.out.println(sb.toString());
		}
		urlConn.disconnect();

		j=new JSONObject(sb.toString());
		//System.out.println("总数:"+j.getString("total"));
		JSONArray l=j.getJSONArray("list");
		//System.out.println("总数:"+l.length());
		for(int i=0;i<l.length();i++){
			JSONObject jj=(JSONObject)l.get(i);
			String deviceId=jj.getString("sn");
			String gpsTime=jj.getString("gpstime");
			String nowTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
			String recvTime = nowTime;
			String lon=jj.getString("lon");
			String lat=jj.getString("lat");
			String oldlon=jj.getString("oldlon");
			String oldlat=jj.getString("oldlat");
			String direction=jj.getString("dir");
			String location=jj.getString("address");
			String speed=jj.getString("spe");
			String start=jj.getString("start");
			String carname=jj.getString("tname");

			String sql="insert into bcx_data(Device_id, GPS_time, Rev_time, Lon, Lat, oldLon, oldLat, Direction, Location, Speed, Start, car_name)";
			sql = sql+"values('"+deviceId+"','"+gpsTime+"','"+recvTime+"','"+lon+"','"+lat+"','"+oldlon+"','"+oldlat+"','"+direction+"','"+location+"','"+speed+"','"+start+"','"+carname+"')";
			//System.out.println(sql);
			statement.executeUpdate(sql);
		}

		//构造sql语句，根据传递过来的查询条件参数
		String sql="select * from bcx_data order by id DESC limit 25;";
		//System.out.println("构造出来的sql语句是："+sql);
		ResultSet rs = statement.executeQuery(sql);
		int count=0;
		while (rs.next()) {
			count=count+1;
			Map map = new HashMap();
			map.put("index",count);
			map.put("device_id",rs.getString("Device_id"));
			map.put("location",rs.getString("Location"));
			map.put("name",rs.getString("car_name"));
			map.put("lon",rs.getString("Lon"));
			map.put("lat",rs.getString("Lat"));
			map.put("start",rs.getString("Start"));
			map.put("speed",rs.getString("Speed"));
			jsonList.add(map);
		}
		statement.close();
		conn.close();
		//System.out.println("数据库关闭了！！！");
	} catch (SQLException sqlexception) {
		System.out.println("出错了！！！错误原因如下：");
		sqlexception.printStackTrace();
	}
	//////////数据库查询完毕，得到了json数组jsonList//////////
	//下面开始构建返回的json
	JSONObject json=new JSONObject();
	json.put("aaData",jsonList);
	json.put("result_msg","ok");	//如果发生错误就设置成"error"等
	json.put("result_code",0);	//返回0表示正常，不等于0就表示有错误产生，错误代码
	//System.out.println("最后构造得到的json是："+json.toString());
	response.setContentType("application/json; charset=UTF-8");
	try {
		response.getWriter().print(json);
		response.getWriter().flush();
		response.getWriter().close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	//System.out.println("返回结果给调用页面了。");

%>
