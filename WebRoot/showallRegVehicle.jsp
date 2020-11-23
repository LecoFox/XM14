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
<title>所有注册车辆</title>
<script type="text/javascript" src="js/tableSort.js"></script>
</head>


<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box3">

					<table id="theTable" align="center" width="1000px" border="1" bordercolor="pink">
						<thead>
							<tr>
								<td class="td1" onclick="sort(theTable,0,'int')">设备ID</td>
								<td class="td1" onclick="sort(theTable,1,'int')">车辆图片</td>
								<td class="td1" onclick="sort(theTable,2,'int')">所有者</td>
								<td class="td1" onclick="sort(theTable,3,'int')">车牌</td>
								<td class="td1" onclick="sort(theTable,4,'int')">品牌</td>
								<td class="td1" onclick="sort(theTable,5,'int')">模型</td>
								<td class="td1" onclick="sort(theTable,6,'int')">发动机号</td>

							</tr>
						</thead>



						<tbody>
							<c:forEach var="U" items="${regVehicleAll}">
								<form action="UpdateServlet" method="post">
									<tr>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.device_id}"
											name="device_id"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.carImg}" name="carImg"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.owner}" name="owner"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.chepai}" name="chepai"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.brand}" name="brand"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.model}" name="model"></td>
										<td><input type="text" style="BACKGROUND-COLOR: transparent;" value="${U.engine_id}"
											name="engine_id"></td>
									</tr>
								</form>
							</c:forEach>
						</tbody>
					</table>

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