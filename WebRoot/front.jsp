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
<title>车辆管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<meta name="keywords"
		content="Architect Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

	<!--引用百度地图API-->
	<style type="text/css">
html, body {
	margin: 0;
	padding: 0;
}

.iw_poi_title {
	color: #CC5522;
	font-size: 14px;
	font-weight: bold;
	overflow: hidden;
	padding-right: 13px;
	white-space: nowrap
}

.iw_poi_content {
	font: 12px arial, sans-serif;
	overflow: visible;
	padding-top: 4px;
	white-space: -moz-pre-wrap;
	word-wrap: break-word
}

.iw_button {
	font: 13px arial, sans-serif;
	color: #CC5522;
	overflow: visible;
	padding-top: 4px;
	white-space: -moz-pre-wrap;
	word-wrap: break-word
}
</style>
	<script type="text/javascript"
		src="http://api.map.baidu.com/getscript?v=3.0&ak=awORzYNz3svIeWeQ9pGPLnmZletmqfog"></script>

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
		<!--start-smoth-scrolling-->
		<link rel="stylesheet" type="text/css" href="css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="css/demo.css" />
		<!--必要样式-->
		<link rel="stylesheet" type="text/css" href="css/component.css" />
</head>



<body style="overflow: hidden;">
	<!--header-->
	<div class="header-top" id="home">
		<div class="container">
			<div class="header-logo">
				<a href="front.jsp"><img src="images/logo.png" alt="" /></a>
			</div>

			<div class="top-nav">
				<span class="menu"><img src="images/menu-icon.png" alt="" /></span>
				<ul class="nav1">
					<li><a href="SearchallRegVehicle">车辆注册信息</a></li>
					<li><a href="Searchall">用户注册信息</a></li>
					<li><a href="loginstatus.jsp">用户在线信息</a></li>
					<li><a href="overspeed.jsp">超速统计</a></li>
					<li><a href="mileage.jsp">里程统计</a></li>
					<li><a id="#b01" href="">一键提醒</a></li>
					<li><a href="javascript:openWin('gettrack.jsp')">轨迹回放</a></li>
					<li><a href="SendYuejie">越界提醒</a></li>
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
					id="cbp-spmenu-s1"> 
					<a href="SearchallRegVehicle">车辆注册信息</a>
					<a href="Searchall">用户注册信息</a>
					<a href="loginstatus.jsp">用户在线信息</a>
					<a href="overspeed.jsp">超速统计</a>
					<a href="mileage.jsp">里程统计</a>
					<a id="#b01" href="">一键提醒</a>
					<a href="javascript:openWin('gettrack.jsp')">轨迹回放</a>
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

	<div id="large-header" class="large-header">
		<div style="float:right;height:100%;width:290px;border:#cccsolid 1px">
			<div style="height:5%;width:290px">
				<div style="float:right;width:70px;font-size:3px;color:white">锁定设备</div>
				<div style="float:right;width:70px;font-size:3px;color:white">设备名称</div>
				<div style="float:right;width:70px;font-size:3px;color:white">状态提醒</div>
				<div style="float:right;width:70px;font-size:3px;color:white">轨迹红线</div>
			</div>
			<div style="position:absolute;height:95%;width:290px;overflow:auto" id="deviceTable"></div>

		</div>

		<div style="height:100%;border:#ccc solid 1px;" id="dituContent"></div>

	</div>


	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>
</body>


<script type="text/javascript">

	//创建和初始化地图函数：
	function initMap() {
		createMap(); //创建地图
		setMapEvent(); //设置地图事件
		addMapControl(); //向地图添加控件
	}

	//创建地图函数：
	function createMap() {
		var map = new BMap.Map("dituContent"); //在百度地图容器中创建一个地图
		var point = new BMap.Point(104.989321, 36.063785); //定义一个中心点坐标
		map.centerAndZoom(point, 5); //设定地图的中心点和坐标并将地图显示在地图容器中
		window.map = map; //将map变量存储在全局
		
	}

	//地图事件设置函数：
	function setMapEvent() {
		map.enableDragging(); //启用地图拖拽事件，默认启用(可不写)
		map.enableScrollWheelZoom(); //启用地图滚轮放大缩小
		map.enableDoubleClickZoom(); //启用鼠标双击放大，默认启用(可不写)
		map.enableKeyboard(); //启用键盘上下左右键移动地图
		Polyline.disableMassClear();

	}

	//地图控件添加函数：
	function addMapControl() {
		//向地图中添加缩放控件
		var ctrl_nav = new BMap.NavigationControl({
			anchor : BMAP_ANCHOR_TOP_LEFT,
			type : BMAP_NAVIGATION_CONTROL_LARGE
		});
		map.addControl(ctrl_nav);
		//向地图中添加缩略图控件
		var ctrl_ove = new BMap.OverviewMapControl({
			anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
			isOpen : 1
		});
		map.addControl(ctrl_ove);
		//向地图中添加比例尺控件
		var ctrl_sca = new BMap.ScaleControl({
			anchor : BMAP_ANCHOR_BOTTOM_LEFT
		});
		map.addTileLayer(new BMap.PanoramaCoverageLayer());
		var stCtrl = new BMap.PanoramaControl({
			anchor : BMAP_ANCHOR_TOP_RIGHT
		}); //构造全景控件
		stCtrl.setOffset(new BMap.Size(20, 20));
		map.addControl(stCtrl); //添加全景控件



	}
	//创建marker
	function addCarMarker(lon, lat, png, title, location, status, speed, direction,deviceid) {
		var point = new BMap.Point(lon, lat);
		var iconImg = createMyIcon(png);
		var marker = new BMap.Marker(point, {
			icon : iconImg,
			rotation : 0
		});
		var iw = createInfoWindow(title, location);
		var label = new BMap.Label(title, {
			"offset" : new BMap.Size(5 - 6 + 10, -20)
		});
		marker.setLabel(label);
		marker.setRotation(direction);
		map.addOverlay(marker);
		label.setStyle({
			borderColor : "#808080",
			color : "#333",
			cursor : "pointer"
		});
		(function() {
			var index = 0;
			var _iw = createInfoWindow(lon, lat, title, location, status, speed,deviceid);
			var _marker = marker;
			_marker.addEventListener("click", function() {
				this.openInfoWindow(_iw);
			});
			_iw.addEventListener("open", function() {
				_marker.getLabel().hide();
			})
			_iw.addEventListener("close", function() {
				_marker.getLabel().show();
			})
			label.addEventListener("click", function() {
				_marker.openInfoWindow(_iw);
			})
			if (!!this.isOpen) {
				label.hide();
				_marker.openInfoWindow(_iw);
			}
		})()
	}
	//创建InfoWindow
	function createInfoWindow(lon, lat, title, location, status, speed,deviceid) {
		var iw = new BMap.InfoWindow("<b class='iw_poi_title'>" + title + "</b>" +
			"<div class='iw_poi_content'>位置:" + location + "</div>" +
			"<div class='iw_poi_content'>经度:" + lon + "</div>" +
			"<div class='iw_poi_content'>纬度:" + lat + "</div>" +
			"<div class='iw_poi_content'>状态:" + status + "</div>" +
			"<div class='iw_poi_content'>速度:" + speed + "</div>" +
			"<a class='iw_button' onclick=\"setCenterAndZoom(" + lon + "," + lat + ")\">跟踪</a>&nbsp;&nbsp;" +
			"<a type='submit' class='iw_button' href='quanjing.jsp?lo=" + lon + "&id=" + deviceid + "&la=" + lat + "' target='blank'>查看全景</a>&nbsp;&nbsp;" +
			"<a type='submit' class='iw_button' href='gettrack2.jsp?tname=\"" + title + "\"' target='blank'>轨迹回放</a>&nbsp;&nbsp;"+
			"<a class='iw_button' onclick=\"openwl(" + lon + "," + lat + "," + deviceid + ")\">围栏</a>&nbsp;&nbsp;"
		);

		return iw;
	}
	//创建一个Icon
	function createMyIcon(png) {
		var icon = new BMap.Icon(png, new BMap.Size(32, 32), {
			imageOffset : new BMap.Size(0, 0),
			infoWindowOffset : new BMap.Size(10, 1),
			offset : new BMap.Size(6, 32)
		})
		return icon;
	}

	initMap(); //创建和初始化地图z
</script>
</html>


<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
<script>
	function openWin(openjsp) {
		window.open(openjsp, '_blank', '');
	}
	function setCenterAndZoom(lon, lat) {
		var center = new BMap.Point(lon, lat);
		map.centerAndZoom(center, 18);
	}
	function getRealtime_data() {
		var url = "BcxData";
		$.post(url, function(json) {
			console.log("running getRealtime_data()");
		});
	}
	function getRecord() {
		var url = "SearchRecord";
		$.post(url, function(json) {
			console.log("running getRecord()");
			window.map.clearOverlays();
			var list = json.aaData;
			if (list.length == 0) {
				console.log("Connecting--bcxgps.com");
			} else {
				//console.log(list);
				for (var i = 0; i < list.length; i++) {
					var location = list[i].location;
					var lon = Number(list[i].lon);
					var lat = Number(list[i].lat);
					var name = list[i].name;
					var status = list[i].start;
					var speed = Number(list[i].speed);
					var carImg = list[i].carImg;
					var direction = Number(list[i].direction);
					var deviceid = list[i].device_id;
					//var warningImg = list[i].warningImg;

					addCarMarker(lon.toFixed(5), lat.toFixed(5), carImg, name, location, status, speed.toFixed(2), direction,deviceid);
					
					
				}

				var tableInfos = document.getElementById('deviceTable');
				var code = '';
				for (var i = list.length - 1; i >= 0; i--) {
					var lon = Number(list[i].lon);
					var lat = Number(list[i].lat);
					var name = list[i].name;
					var speed = Number(list[i].speed);
					var start = list[i].start;
					var status = start.substr(0, 2);
					var location = list[i].location;
					var time = list[i].GPS_time;

					var row = 'row' + i;
					code += '<div id="' + row + '" style="height:50px;overflow:auto">';
					code += '<div style="float:left;width:50px;font-size:3px;color:white" id="column' + i + '1">&nbsp;&nbsp;' + name + '</div>';
					code += '<div style="float:left;width:20px;font-size:3px;color:white" id="column' + i + '2">' + speed + '</div>';
					code += '<div style="float:left;width:30px;font-size:3px;color:white" id="column' + i + '3">' + status + '</div>';
					code += '<div style="float:left;width:80px;font-size:3px;color:white" id="column' + i + '4">' + location + '</div>';
					code += '<div style="float:left;width:80px;font-size:3px;color:white" id="column' + i + '5">' + time + '</div>';
					code += '</div>';

				}
				tableInfos.innerHTML = code;

			}
		});
		
		realtime = setTimeout(getRecord, 10000);
	}

	getRealtime_data();
	getRecord();
	
</script>

<script>
	function sendMessage() {
		
		realtime = setTimeout(sendMessage, 50000);
	}
	sendMessage();
</script>

<script>
	$(document).ready(function() {
		$("#b01").click(function() {
			$.ajax({
				type : "get",
				url : "/XM14/UserEmailServlet",
				datatype : "json",
			})
		});
	});
</script>
<script>
	function openwl(lon,lat,deviceid)
	{
		var itop = (window.screen.availHeight-530)/2;
	    var ileft = (window.screen.availWidth-810)/2;
		console.log("----open weilan----");
		var setweilan = "setwl.jsp?" + "lon=" + lon + "&lat=" + lat + "&deviceid=" + deviceid;
		window.open(setweilan,'设置围栏',"fullscreen=0,height=500,width=800,toolbar=0,location=0,directories=0,status=0,menubar=0,resizable=0,top="+itop+",left="+ileft+",scrollbars=yes")
	}
</script>
<script>
	function sendYuejieMessage() {
		var url = "UserEmailServlet";
		$.get(url, function(json) {
			console.log("running sendMessage()");
		});
		var url = "SendYuejie";
		$.get(url, function(json) {
			console.log("running sendYuejieMessage()");
		});
	}
	window.setInterval(sendYuejieMessage,300000);
</script>


