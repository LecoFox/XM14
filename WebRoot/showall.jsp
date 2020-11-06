<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>所有用户</title>
<script type="text/javascript" src="js/tableSort.js"></script>
</head>


<body>
	<table id="theTable" align="center" border="1">
		<thead>
			<tr>
				<td onclick="sort(theTable,0,'int')">ID</td>
				<td onclick="sort(theTable,1,'int')">用户名</td>
				<td onclick="sort(theTable,2,'int')">性别</td>
				<td onclick="sort(theTable,3,'int')">邮箱</td>

			</tr>
		</thead>



		<tbody>
			<c:forEach var="U" items="${userAll}">
				<form action="UpdateServlet" method="post">
					<tr>
						<td><input type="text" value="${U.id}" name="id"></td>
						<td><input type="text" value="${U.username}" name="username"></td>
						<td><input type="text" value="${U.sex}" name="sex"></td>
						<td><input type="text" value="${U.email}" name="email"></td>
					</tr>
				</form>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>


<script type="text/javascript">
function aa(){
alert("我执行了");
}

</script>