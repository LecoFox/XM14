<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="com.model.User"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
<title>车辆注册</title>
</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
	%>

	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box">
					<form id="regvehicle" action="/XM14/RegVehicleServlet"
						method="post" onsubmit="return reg(this)">
						<table align="center" border="0" width="330">
							<tr>
								<td class="td1">设备ID：</td>
								<td class="td2"><select class="input_outer2" id="sel"
									name="deviceid" class="box"><option>请选择</option></select></td>
							</tr>
							<tr>
								<td class="td1">发动机号：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="engineid"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">车主：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="owner"
											value=<%=user.getUsername()%>
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">车牌：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="chepai"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">车型：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="model"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">品牌：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="brand"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" height="40"><input
									type="submit" value="注册" class="act-but1 submit1"
									onclick="document:regvehicle.submit()"> <input
									type="reset" value="重置" class="act-but1 submit1"></td>
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

<script src="jquery-1.8.3.min.js"></script>
<script>
$(document).ready(function () {
    var url="/XM14/SelectVehicleServlet"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
    $.ajax({
        type:"get",
        url:url,
        datatype:"json",
        success:function(userList){
            var unitObj=$("#sel"); //页面上的<html:select>元素
            var parsedJson = jQuery.parseJSON(userList);
            //console.log(data[0].Device_id);
            if(parsedJson!=null){ //后台传回来的select选项
                for(var i=0;i<parsedJson.length;i++){
                    //遍历后台传回的结果，一项项往select中添加option
                    unitObj.append("<option>"+parsedJson[i].Device_id+"</option>");
                }
            }
        },
        error:function(){
            J.alert('Error');
        }
    })
})
</script>

