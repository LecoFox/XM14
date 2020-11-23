<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>所有用户</title>
<script type="text/javascript" src="js/tableSort.js"></script>

</style>

</head>


<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box4">

					<table id="theTable" align="center" border="1" bordercolor="pink">
						<thead>
							<tr>
								<td class="td1" onclick="sort(theTable,0,'int')">ID</td>
								<td class="td1" onclick="sort(theTable,1,'int')">用户名</td>
								<td class="td1" onclick="sort(theTable,2,'int')">性别</td>
								<td class="td1" onclick="sort(theTable,3,'int')">邮箱</td>

							</tr>
						</thead>



						<tbody>
							<c:forEach var="U" items="${userAll}">
								<form action="UpdateServlet" method="post">
									<tr>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.id}" name="id"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.username}"
											name="username"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.sex}" name="sex"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.email}" name="email"></td>
									</tr>
								</form>
							</c:forEach>
						</tbody>
					</table>
					
					<form action="excel">
					<div class="mb2">
						<input value="导出" type="submit" class="act-but submit" style="color: #FFFFFF">
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


<script type="text/javascript">
function aa(){
alert("我执行了");
}

</script>