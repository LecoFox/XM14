<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 17-5-11
  Time: 下午3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
<!--[if IE]>
	<script src="js/html5.js"></script>
	<![endif]-->
</head>
<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box1">
					<form id="register" action="/XM14/RegServlet" method="post"
						onsubmit="return reg(this)">
						<h1>注册</h1>
						<!-- 用户名 -->
						<div class="input_outer1">
							<input name="username" class="text1" style="color: #FFFFFF !important" type="text" placeholder="用户名">
						</div>
						<!-- 密码 -->
						<div class="input_outer1">
							<input name="password1" class="text1" style="color: #FFFFFF !important" type="password" placeholder="密码">
						</div>
						<!-- 确认密码 -->
						<div class="input_outer1">
							<input name="password2" class="text1" style="color: #FFFFFF !important" type="password" placeholder="确认密码">
						</div>
						<!-- 性别 -->
						<div class="input_outer1">
							<input name="sex" class="text1" style="color: #FFFFFF !important" type="text" placeholder="性别">
						</div>
						<!-- 密码找回问题 -->
						<div class="input_outer1">
							<input name="question" class="text1" style="color: #FFFFFF !important" type="text" placeholder="密码找回问题">
						</div>
						<!-- 密码找回答案 -->
						<div class="input_outer1">
							<input name="answer" class="text1" style="color: #FFFFFF !important" type="text" placeholder="密码找回答案">
						</div>
						<!-- 邮箱 -->
						<div class="input_outer1">
							<input name="email" class="text1" style="color: #FFFFFF !important" type="text" placeholder="邮箱">
						</div>
						
						<div class="mb2">
							<a type="submit" href="javascript:;" class="act-but submit" style="color: #FFFFFF" onclick="document:register.submit()">注册</a>
							<a type="submit" href="login.jsp" class="act-but submit" style="color: #FFFFFF" >切换到登录界面</a>
						</div>
					</form>
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
</html>
