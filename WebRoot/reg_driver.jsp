<%@ page contentType="text/html;charset=UTF-8" language="java"%>
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
<title>驾驶员注册</title>
</head>
<body>


	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box">
					<form id="regdriver"action="/XM14/RegDriverServlet" method="post"
						onsubmit="return reg(this)">
						<table align="center" border="0" width="330">
							<tr>
								<td class="td1">驾驶证ID：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="driver_id"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">姓名：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="name"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">性别：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="sex"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">生日：</td>
								<td class="td2">
									<div class="input_outer2">
										<input id="datetime1" type="text" name="birthday"
										style="BACKGROUND-COLOR: transparent;">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">驾驶证有效期：</td>
								<td class="td2">
									<div class="input_outer2">
										<input id="datetime2" type="text" name="validity_period"
										style="BACKGROUND-COLOR: transparent;">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">电话号码：</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="phone_number"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center" height="40"><input
									type="submit" value="注册" class="act-but1 submit1" onclick="document:regdriver.submit()"> <input
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

<link
	href="https://cdn.bootcss.com/jquery-datetimepicker/2.5.17/jquery.datetimepicker.min.css"
	rel="stylesheet">
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/jquery-datetimepicker/2.5.17/jquery.datetimepicker.full.min.js"></script>
<script>
	jQuery('#datetime1').datetimepicker({
		format : 'Y-m-d',
		//changeHour: false,
		lang : 'zh'
	});
	jQuery('#datetime2').datetimepicker({
		format : 'Y-m-d',
		lang : 'zh'
	});
</script>

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

