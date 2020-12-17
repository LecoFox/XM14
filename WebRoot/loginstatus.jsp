<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<meta name="keywords"
		content="Architect Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
	<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
			<meta http-equiv="expires" content="0">
				<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
					<meta http-equiv="description" content="This is my page">
						<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
<title>在线用户</title>
<script type="text/javascript" src="js/tableSort.js"></script>
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
				<a href="front.jsp"><img src="images/logo.png" alt="" /></a>
			</div>

			<div class="top-nav">
				<span class="menu"><img src="images/menu-icon.png" alt="" /></span>
				<ul class="nav1">

					<li><a href="SearchallRegVehicle">查看车辆注册信息</a></li>
					<li><a href="Searchall">查看用户注册信息</a></li>
					<li><a href="loginstatus.jsp">查看用户在线信息</a></li>
					<li><a href="overspeed.jsp">超速统计</a></li>
					<li><a href="mileage.jsp">里程统计</a></li>
					<li><a href="UserEmailServlet">一键提醒</a></li>
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
					id="cbp-spmenu-s1"> <a href="SearchallRegVehicle">查看车辆注册信息</a>
				<a href="Searchall">查看用户注册信息</a> <a href="loginstatus.jsp">查看用户在线信息</a>
				<a href="overspeed.jsp">超速统计</a><a href="mileage.jsp">里程统计</a> <a href="UserEmailServlet">一键提醒</a>
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

	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<center>
					<h1>用户在线信息</h1>
					<br>
				</center>
				<div>
					<center>

						<select class="input_outer2" id="sel" name="username" class="box"><option>选择指定用户日志</option>
							<option></option></select>

					</center>
					<br/>
					<center>
						<button class="act-but1 submit1"style="color: #FFFFFF">确认</button>
					</center>
					<br></br>

				</div>
				<div style="width:100%;" id="tableInfo"
					; class="speedtable"></div>

			</div>
		</div>
	</div>
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
</body>
</html>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		var url = "/XM14/SelectUser"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
		$.ajax({
			type : "get",
			url : url,
			datatype : "json",
			success : function(userList) {
				var unitObj = $("#sel"); //页面上的<html:select>元素
				var parsedJson = jQuery.parseJSON(userList);
				//console.log(data[0].Device_id);
				if (parsedJson != null) { //后台传回来的select选项
					for (var i = 0; i < parsedJson.length; i++) {
						//遍历后台传回的结果，一项项往select中添加option
						unitObj.append("<option>" + parsedJson[i].username + "</option>");
					}
				}
			},
			error : function() {
				alert('Error');
			}
		})


	});
</script>
<script>
	$("button").click(function() {
		var setusername = $("#sel option:selected").text();
		$.ajax({
			type : "get",
			url : "/XM14/loginstatus",
			datatype : "json",
			data : {
				username : setusername,
			},
			success : function(result) {
				var parsedJson = jQuery.parseJSON(result);
				if (parsedJson != null && parsedJson.length > 0) {
					var tableInfos = document.getElementById('tableInfo'); //生成动态表格
					var code = '<div class="table100"><table id="theTable" class="table1"><thead><tr class="table100-head">';
					code += '<TH>用户名</TH><TH>登录时间</TH></TR></thead><tbody>';
					for (var i = 0; i < parsedJson.length; i++) {
						code += '<TR><TD>' + parsedJson[i].username + '</TD><TD>' + parsedJson[i].login_time + '</TD></TR>';
					}
					tableInfos.innerHTML = code + '</tbody></TABLE>';
				} else {
					alert("无数据！");
				}
			},
			error : function() {
				alert('Error');
			}
		})
	});
</script>
<script>
	$(document).ready(function() {
		$.ajax({
			type : "get",
			url : "/XM14/loginstatus",
			datatype : "json",

			success : function(result) {
				var parsedJson = jQuery.parseJSON(result);
				if (parsedJson != null && parsedJson.length > 0) {
					var tableInfos = document.getElementById('tableInfo'); //生成动态表格
					var code = '<div class="table100"><table id="theTable" class="table1"><thead><tr class="table100-head">';
					code += '<TH>在线用户名</TH><TH>登录时间</TH></TR></thead><tbody>';
					for (var i = 0; i < parsedJson.length; i++) {
						code += '<TR><TD>' + parsedJson[i].username + '</TD><TD>' + parsedJson[i].login_time + '</TD></TR>';
					}
					tableInfos.innerHTML = code + '</tbody></TABLE>';
				} else {
					alert("无数据！");
				}
			},
			error : function() {
				alert('无人在线');
			}
		})
	});
</script>
