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
<title>修改密码</title>
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
				<div class="logo_box">
				<!-- 这里的action要改 -->
					<form id="pswupdate" action="PasswordUpdate" method="post"
						onsubmit="return reg(this)">
						
						
						
						<h1>修改密码</h1>
						<table align="center" border="0" width="330">
							<tr>
								<td class="td3">用户名：</td>
								<td class="td4">
									<div class="input_outer2">
										<input class="text1" type="text" name="username"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td3">原密码：</td>
								<td class="td4">
									<div class="input_outer2">
										<input class="text1" type="text" name="password"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td3">新密码：</td>
								<td class="td4">
									<div class="input_outer2">
										<input class="text1" type="text" name="new_pwd1"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td3">确认新密码：</td>
								<td class="td4">
									<div class="input_outer2">
										<input class="text1" type="text" name="new_pwd2"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" height="40"><input
									type="submit" value="确认修改" class="act-but1 submit2" onclick="document:pswupdate.submit()"> <input
									type="reset" value="重置" class="act-but1 submit2"></td>
							</tr>
						</table>
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
