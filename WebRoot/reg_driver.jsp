<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	import="com.model.RegVehicle" import="com.model.User"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html>
<head>
<title>驾驶员注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="keywords"
	content="Architect Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="te
xt/javascript"
	src="http://api.map.baidu.com/getscript?v=3.0&ak=awORzYNz3svIeWeQ9pGPLnmZletmqfog">
</script>

<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/style.css" rel='stylesheet' type='text/css' />
<script src="js/jquery-1.11.0.min.js"></script>
<link
	href='http://fonts.useso.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<!---- start-smoth-scrolling---->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>


<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
<!--start-smoth-scrolling-->
</head>



<body>
	<!--header-->
	<div class="header-top" id="home">
		<div class="container">
			<div class="header-logo">
				<a href="front2.jsp"><img src="images/logo.png" alt="" /></a>
			</div>

			<div class="top-nav">
				<span class="menu"><img src="images/menu-icon.png" alt="" /></span>
				<ul class="nav1">
					<li><a href="reg_vehicle.jsp">车辆注册</a></li>
					<li><a href="delete_account.jsp">删除账号</a></li>
					<li><a href="reg_driver.jsp">驾驶员信息注册</a></li>
				</ul>
				<!-- script-for-menu -->
				<script>
					$("span.menu").click(function() {
						$("ul.nav1").slideToggle(300, function() {
							// Animation complete.
						});
					});
				</script>
				<!-- /script-for-menu -->
			</div>
			<div class="social-icons">
				<ul>
					<li><a href="#"><span class="twit"> </span></a></li>
					<li><a href="#"><span class="fb"> </span></a></li>
					<li><a href="#"><span class="g"> </span></a></li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="search-box">
			<div id="sb-search" class="sb-search">
				<form>
					<input class="sb-search-input"
						placeholder="Enter your search term..." type="search"
						name="search" id="search"> <input class="sb-search-submit"
						type="submit" value=""> <span class="sb-icon-search">
					</span>
				</form>
			</div>
		</div>
		<div class="header-info-right">
			<div class="header cbp-spmenu-push">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left"
					id="cbp-spmenu-s1"> <a href="reg_vehicle.jsp">车辆注册</a> <a
					href="delete_account.jsp">删除账号</a> <a href="reg_driver.jsp">驾驶员信息注册</a>
				</nav>
				<!--script-nav -->
				<script>
					$("span.menu").click(function() {
						$("ul.navigatoin").slideToggle("300", function() {});
					});
				</script>
				<script type="text/javascript">
					jQuery(document).ready(function($) {
						$(".scroll").click(function(event) {
							event.preventDefault();
							$('html,body').animate({
								scrollTop : $(this.hash).offset().top
							}, 1000);
						});
					});
				</script>
				<div class="clearfix"></div>
				<!-- /script-nav -->
				<div class="main">
					<button id="showLeftPush">
						<img src="images/menu.png" /><span>Menu</span>
					</button>
				</div>
				<!-- Classie - class helper functions by @desandro https://github.com/desandro/classie -->
				<script src="js/classie.js"></script>
				<script>
					var menuLeft = document.getElementById('cbp-spmenu-s1'),
						showLeftPush = document.getElementById('showLeftPush'),
						body = document.body;
				
					showLeftPush.onclick = function() {
						classie.toggle(this, 'active');
						classie.toggle(body, 'cbp-spmenu-push-toright');
						classie.toggle(menuLeft, 'cbp-spmenu-open');
						disableOther('showLeftPush');
					};
				</script>
			</div>
		</div>
	</div>
	<!--//header-->


	<%
		User user = (User) session.getAttribute("user");
	%>
	<div id="large-header" class="large-header">
		<canvas id="demo-canvas"></canvas>
		<div class="logo_box">
					<form id="regdriver"action="/XM14/RegDriverServlet" method="post"
						onsubmit="return reg(this)">
						<table align="center" border="0" width="330">
							<tr>
								<td class="td1">驾驶证ID:</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="driver_id"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">姓名:</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="name"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">性别:</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="sex"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">生日:</td>
								<td class="td2">
									<div class="input_outer2">
										<input id="datetime1" class="text1" type="text" name="birthday"
										style="BACKGROUND-COLOR: transparent;">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">驾驶证有效期:</td>
								<td class="td2">
									<div class="input_outer2">
										<input id="datetime2" class="text1"  type="text" name="validity_period"
										style="BACKGROUND-COLOR: transparent;">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td1">电话号码:</td>
								<td class="td2">
									<div class="input_outer2">
										<input class="text1" type="text" name="phone_number"
											style="color: #FFFFFF !important" class="box">
									</div>
								</td>
							</tr>
							<tr>
								<td class="td5" colspan="2"><input
									type="submit" value="注册" class="act-but1 submit1" onclick="document:regdriver.submit()"> <input
									type="reset" value="重置" class="act-but1 submit1"></td>
							</tr>
						</table>
					</form>
				</div>
	</div>

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
