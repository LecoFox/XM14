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
<base href="<%=basePath%>">
<title>里程统计</title>
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
					<li><a href="showall.jsp">用户注册信息</a></li>
					<li><a href="loginstatus.jsp">用户在线信息</a></li>
					<li><a href="allocation_device.jsp">设备分配</a></li>
					<li><a href="overspeed.jsp">超速统计</a></li>
					<li><a href="javascript:openWin('gettrack.jsp')">轨迹回放</a></li>
					<li><a href="mileage.jsp">里程统计</a></li>
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
				<li id="remainTime" style="color:white;">平台将于<span
					style="color:red">10</span>s后刷新
				</li>
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
					id="cbp-spmenu-s1"> 
					<a href="showall.jsp">用户注册信息</a> 
					<a href="loginstatus.jsp">用户在线信息</a>
					<a href="allocation_device.jsp">设备分配</a>
					<a href="overspeed.jsp">超速统计</a> 
					<a href="javascript:openWin('gettrack.jsp')">轨迹回放</a> 
					<a href="mileage.jsp">里程统计</a> </nav>
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
					<h1>里程统计</h1>
					<br>
				</center>
				<div>
					<div
						style="display: flex;justify-content: space-around;flex-wrap: wrap; flex-direction: row;">

						<div class="input_outer3">
							<input id="datetime1" type="text"
								style="BACKGROUND-COLOR: transparent;" name="StartTime"
								class="text" placeholder="请输入起始时间">
						</div>
						<div class="input_outer3">
							<input id="datetime2" type="text"
								style="BACKGROUND-COLOR: transparent;" name="EndTime"
								class="text" placeholder="请选择结束时间">
						</div>
						<div>
								<select class="input_outer2" id="sel" name="setDeviceId"
									class="box"><option>选择设备ID</option>
									<option></option></select>

						</div>

					</div>

					<center>
						<button id="queren" class="act-but1 submit1" style="color: #FFFFFF">确认</button>
					</center>
					<br></br>

				</div>
				<div style="width:100%;" id="tableInfo" ; class="speedtable"></div>

			</div>
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
			format : 'Y-m-d H:i:00',
			lang : 'zh'
		});
		jQuery('#datetime2').datetimepicker({
			format : 'Y-m-d H:i:00',
			lang : 'zh'
		});
	</script>
</html>
<script>
	$(document).ready(function() {
		$("#queren").click(function() {
			var device_id = $('#sel').val();
			var Time1 = $('#datetime1').val();
			var Time2 = $('#datetime2').val();
			$.ajax({
				type : "get",
				url : "/XM14/mileage",
				datatype : "json",
				data : {
					setDeviceId : device_id,
					StartTime : Time1,
					EndTime : Time2
				},
				success : function(result) {
					var parsedJson = jQuery.parseJSON(result);
					if (parsedJson != null && parsedJson.length > 0) {
						var tableInfos = document.getElementById('tableInfo'); //生成动态表格
						var code = '<div class="table100"><table id="theTable" class="table1"><thead><tr class="table100-head">';
						code += '<TH>车辆ID</TH><th>车辆名称</th><th>参考里程</th><th>参考油耗</th><TH>最高时速</TH><th>时间段</th><th>总里程</th><th>总油耗</th></TR></thead><tbody>';
						for (var i = 0; i < parsedJson.length; i++) {
							code += '<TR><TD>' + parsedJson[i].device_id + '</TD><TD>' + parsedJson[i].car_name + '</TD><TD>' +
							 parsedJson[i].refer_mile + '</TD><TD>' + parsedJson[i].refer_gasoline + '</TD><TD>' +
							 parsedJson[i].maxspeed + '</TD><TD>' + parsedJson[i].btime + '~' + parsedJson[i].etime + '</TD><TD>' + 
							 parsedJson[i].total_mile + '</TD><TD>' + parsedJson[i].total_gasoline + '</TD></TR>';
						}
						tableInfos.innerHTML = code + '</tbody></TABLE>';
					} else {
						alert("无数据！");
					}
				},
				error : function() {
					J.alert('Error');
				}
			})
		});
	});
</script>
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