<%@ page import="com.model.User"%><%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 17-5-11
  Time: 下午4:23
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="textml;charset=UTF-8" language="java"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login</title>
<link rel="stylesheet" type="texts" href="cssrmalize.css" />
<link rel="stylesheet" type="texts" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="texts" href="css/component.css" />
<!--[if IE]>
	<script src="jsml5.js"></script>
	<![endif]-->
</head>
<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box">
					<%
    					/*获取提示信息*/
    					String info = (String) request.getAttribute("info");
    					String type = (String) request.getAttribute("type");
    					String flag = (String) request.getAttribute("flag");
    					
    					if (info != null) {
    				%>
							<h3><%=info%></h3>
					<%
						}
    					/*获取用户的登录信息*/
    					User user = (User) session.getAttribute("user");
    					if(type.equals("login")){
    						if (user != null && flag.equals("1")){
					%>
								<div class="mb2">
									<h3><%=user.getUsername()%>登录成功！</h3>
									<a href="front.jsp" class="act-but submit" style="color: #FFFFFF" id="jump">获取伴车星数据</a>
								</div>
					<%
						//session.invalidate();
    						}
    						else {
    							out.println("<div class='mb2'>");
    							out.println("<h3>对不起您没有登录！</h3>");
        						out.println("<a href='login.jsp' class='act-but submit' style='color: #FFFFFF'>返回登录界面</a>");
        						out.println("<a href='register.jsp'  class='act-but submit' style='color: #FFFFFF'>我是新用户</a>");
        						out.println("</div>");
							}
						}
						else{
							out.println("<div class='mb2'>");
    						out.println("<h3>对不起您没有登录！</h3>");
        					out.println("<a href='login.jsp' class='act-but submit' style='color: #FFFFFF'>返回登录界面</a>");
        					out.println("<a href='register.jsp'  class='act-but submit' style='color: #FFFFFF'>我是新用户</a>");
        					out.println("</div>");
						}
					%>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
</body>
<ml>
<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
<script>
	function access12041701(){
		var source = "<%=type%>";
		if(source=="login"){
			var type = "<%=user.getType()%>";
			console.log(type);
			if(type=="n"){
				$("#jump").attr("href","front2.jsp");
			}
		}
	}
	access12041701();
</script>
