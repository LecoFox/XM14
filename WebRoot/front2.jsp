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
<title>车辆管理(普通用户)</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta name="keywords"
	content="Architect Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />



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
	href='http://fonts.lug.ustc.edu.cn/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
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




<body style="overflow: hidden;height:100%">

	<!--header-->
	<div class="header-top" id="home">
		<div class="container">
			<div class="header-logo">
				<a href="front2.jsp"><img src="images/logo.png" alt="" /></a>
			</div>

			<div class="top-nav">
				<span class="menu"><img src="images/menu-icon.png" alt="" /></span>
				<ul class="nav1">
					<li><a href="allocation_driver.jsp">车辆分配</a></li>
					<li><a href="showallRegVehicle.jsp">车辆信息登记</a></li>
					<li><a href="reg_driver.jsp">驾驶员信息登记</a></li>
					<li><a href="overspeed2.jsp">超速统计</a></li>
					<li><a href="mileage2.jsp">里程统计</a></li>
					<li><a href="delete_account.jsp">删除账号</a></li>
					<li><a id="#b01" href="">一键提醒</a></li>
					<li><a href="javascript:openWin('normalgettrack.jsp')">轨迹回放</a></li>
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
				<li id="remainTime" style="color:white;">平台将于<span
					style="color:red">10</span>s后刷新
				</li>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="header-info-right">
			<div class="header cbp-spmenu-push">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1"> 
					<a href="allocation_driver.jsp">车辆分配</a>
					<a href="showallRegVehicle.jsp">车辆信息登记</a>
					<a href="reg_driver.jsp">驾驶员信息登记</a>
					<a href="overspeed2.jsp">超速统计</a>
					<a href="mileage2.jsp">里程统计</a>
					<a href="delete_account.jsp">删除账号</a>
					<a id="#b01" href="">一键提醒</a>
					<a href="javascript:openWin('normalgettrack.jsp')">轨迹回放</a> 
					<a href="SendYuejie">越界提醒</a> 
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

			<div style="height:10%;width:290px">
				<div class="dropdown" style="float:left">
					<button class="dropbtn">排序</button>
					<div class="dropdown-content">
						<div>
							<input type="radio" id="speeds" name="pages" onclick = "sorttable()"> <label for="times">按速度</label>
						</div>
						<div>
							<input type="radio" id="letters" name="pages" onclick = "sorttable()"> <label for="letters">按字母</label>
						</div>
					</div>
				</div>

				<div class="dropdown" style="float:left">
					<button class="dropbtn">状态</button>
					<div class="dropdown-content">
						<div>
							<input type="radio" id="showall" name="pages" checked="true" onclick = "getRecord()"> <label for="showall">显示全部</label>
						</div>
						<div>
							<input type="radio" id="showonline" name="pages" onclick = "getRecord()"> <label for="10rows">显示行驶</label>
						</div>
						<div>
							<input type="radio" id="showoffline" name="pages" onclick = "getRecord()"> <label for="5rows">显示离线</label>
						</div>
					</div>
				</div>
				
				<div class="dropdown" style="float:left">
					<button class="dropbtn">显示</button>
					<div class="dropdown-content">
						<div>
							<input class="checkbox_func" type="checkbox" id="lock"
								onclick="setDeviceStatus();"> <label for="lock">锁定设备</label>
						</div>
						<div>
							<input class="checkbox_func" type="checkbox" id="label">
								<label for="label">设备名称</label>
						</div>
						<div>
							<input class="checkbox_func" type="checkbox" id="device"
								onclick="statusAlert();"><label for="device">状态提醒</label>
						</div>
						<div>
							<input class="checkbox_func" type="checkbox" id="track"
								onclick="setDeviceTrack();"> <label for="track">轨迹红线</label>
						</div>

					</div>
				</div>
			</div>
			
				<div style="position:relative;height:-webkit-calc(100% - 100px);width:290px;overflow:auto;" id="deviceTable"></div>
			
			<div class="page_btn clear" style="position:relative;height:20px;width:290px;font-size:3px;color:white;float:center;visibility: visible;display:block;"> 
            	<span class="page_box" style="clear:both height:100%"> 
                	<a class="prev" style="margin-left:25%">上一页</a>
                	<span class="num"><span class="current_page">1</span><span style="padding:0 3px;">/</span><span class="total"></span></span>
                	<a class="next">下一页</a> 
            	</span> 
        	</div>
		</div>
		<div style="height:100%;border:#ccc solid 1px;" id="dituContent"></div>
	</div>

	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>

	<script type="text/javascript" src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrap-table.js"></script>
	<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
	
	
</body>




<script type="text/javascript">
	jQuery(document).ready(function($) {
		$('#record_list').bootstrapTable({
		columns: [{
			field: 'name',
			title: 'name'
		}, {
			field: 'speed',
			title: 'speed'
		}, {
			field: 'status',
			title: 'status'
		}, {
			field: 'location',
			title: 'location'
		}, {
			field: 'time',
			title: 'time'
		},]
	});
});

	//创建和初始化地图函数：
	function initMap() {
		createMap(); //创建地图
		setMapEvent(); //设置地图事件
		addMapControl(); //向地图添加控件
	}

	//创建地图函数：
	function createMap() {
		var map = new BMap.Map("dituContent"); //在百度地图容器中创建一个地图
		var point = new BMap.Point(108.946465, 34.347269); //定义一个中心点坐标
		map.centerAndZoom(point, 5); //设定地图的中心点和坐标并将地图显示在地图容器中
		window.map = map; //将map变量存储在全局
		var Markers=[];
		window.markers=Markers;
		var infos=[];
		window.infos=infos;
		var oldid = 'row1';
		window.oldid=oldid;
		var cpage=1;
		window.cpage=cpage;
	}

	//地图事件设置函数：
	function setMapEvent() {
		map.enableDragging(); //启用地图拖拽事件，默认启用(可不写)
		map.enableScrollWheelZoom(); //启用地图滚轮放大缩小
		map.enableDoubleClickZoom(); //启用鼠标双击放大，默认启用104.989321, 36.063785yboard(); //启用键盘上下左右键移动地图
	//Polyline.disableMassClear();
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
	function addCarMarker(lon, lat, png, title, location, status, speed, direction, deviceid,_index) {
		var point = new BMap.Point(lon, lat);
		var iconImg = createMyIcon(png);
		var marker = new BMap.Marker(point, {
			icon : iconImg,
			rotation : 0,
		});
		
		var label = new BMap.Label(title, {
			"offset" : new BMap.Size(9, -20)
		});
		label.setStyle({
			display : "none",
			color : "red",
			fontSize : "12px",
			height : "20px",
			lineHeight : "20px",
			fontFamily : "微软雅黑"
		});
		marker.setLabel(label);
		marker.addEventListener("mouseover", function(e) {
			var label = this.getLabel();
			label.setStyle({
				display : "block"
			});
		});
		marker.addEventListener("mouseout", function(e) {
			var label = this.getLabel();
			label.setStyle({
				display : "none"
			});
		});
		marker.setRotation(direction);
		map.addOverlay(marker);
		label.setStyle({
			borderColor : "#808080",
			color : "#333",
			cursor : "pointer"
		});
		(function() {
			var index = 0;
			var _iw = createInfoWindow(lon, lat, title, location, status, speed, deviceid);
			var _marker = marker;
			var index = title.substring(2);
			//console.log(index);
			var id = 'row'+_index;
			//console.log(id);
			markers.unshift(marker);
			infos.unshift(_iw);
			_marker.addEventListener("click", function() {
				setLock(lon, lat, title, status);
				this.openInfoWindow(_iw);
				rowchange(oldid,false);
				rowchange(id,true);
				oldid=id;
				console.log(oldid);
			});
			_iw.addEventListener("open", function() {
				_marker.getLabel().hide();
			})
			_iw.addEventListener("close", function() {
				_marker.getLabel().show();
			})
			label.addEventListener("click", function() {
				setLock(lon, lat, title, status);
				_marker.openInfoWindow(_iw);
			})
			if (!!this.isOpen) {
				label.hide();
				_marker.openInfoWindow(_iw);
			}
		})	()
		
	}
	//创建InfoWindow
	function createInfoWindow(lon, lat, title, location, status, speed, deviceid) {
		var iw = new BMap.InfoWindow("<b class='iw_poi_title'>" + title + "</b>" +
			"<div class='iw_poi_content'>防护:" + " " + "</div>" +
			"<div class='iw_poi_content'>GPS:" + " " + "</div>" +
			"<div class='iw_poi_content'>GSM:" + " " + "</div>" +
			"<div class='iw_poi_content'>位置:" + location + "</div>" +
			"<div class='iw_poi_content'>经度:" + lon + "</div>" +
			"<div class='iw_poi_content'>纬度:" + lat + "</div>" +
			"<div class='iw_poi_content'>状态:" + status + "</div>" +
			"<div class='iw_poi_content'>速度:" + speed + "</div>" +
			"<a class='iw_button' onclick=\"setCenterAndZoom(" + lon + "," + lat + "," + speed + ")\">跟踪</a>&nbsp;&nbsp;" +
			"<a type='submit' class='iw_button' href='quanjing.jsp?lo=" + lon + "&id=" + deviceid + "&la=" + lat + "' target='blank'>查看全景</a>&nbsp;&nbsp;" +
			"<a type='submit' class='iw_button' href='gettrack2.jsp?tname=\"" + title + "\"' target='blank'>轨迹回放</a>&nbsp;&nbsp;" +
			"<a class='iw_button' onclick=\"openwl(" + lon + "," + lat + "," + deviceid + ")\">围栏</a>&nbsp;&nbsp;"
		);

		return iw;
	}
	//创建一个Icon
	function createMyIcon(png) {
		var icon = new BMap.Icon(png, new BMap.Size(16, 32), {
			imageOffset : new BMap.Size(0, 0),
			infoWindowOffset : new BMap.Size(10, 1),
			offset : new BMap.Size(6, 32)
		})
		return icon;
	}

	initMap(); //创建和初始化地图z
</script>
</html>



<script>
	var second = 10;
	var lock_device = false;
	var horizen = false;
	var alert_device = false;
	var alert_name = null;
	var alert_status = null;
	var show_track = false;
	var have_old = false;
	function setRemainTime() {
		if (second > 0) {
			second -= 1;
		} else {
			getRecord();
			second = 10;
		}
		setTimeout(setRemainTime, 1000);
		$("#remainTime").html("平台将于 <span style='color:red'>" + second + "</span>s后刷新");
	}
	function getRecord() {
		var url = "SearchRecord2";
		var type= "0";
		if(document.getElementById("showall").checked){
			type="全部";
		}
		else if(document.getElementById("showonline").checked){
			type="行驶";
		}
		else if(document.getElementById("showoffline").checked){
			type="停止";
		}
		$.post(url, {show:type},function(json) {
			console.log("running getRecord()");
			//window.map.clearOverlays();
			var poly_old = [];
			var allOverlay = map.getOverlays();

			for(var i = 0 ; i < allOverlay.length ; i++){
		    	if (allOverlay[i] instanceof BMap.Marker) {
		    		//体验25,24,...1
		    		if(have_old){
		    			var marker = allOverlay[i];
		    			//console.log(marker.getLabel().content);
		    			var lon = marker.point.lng;
		    			var lat = marker.point.lat;
		    			poly_old.push(new BMap.Point(lon, lat));
		    		}
		    		map.removeOverlay(allOverlay[i]);
		    		markers = [];
		    		infos=[];
            	}

			}
			var list = json.aaData;
			if (list.length == 0) {
				console.log("Connecting--bcxgps.com");
			} else {
				//console.log(list);
				for (var i = 0; i < list.length; i++) {
					//体验25,24,...1
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
					addCarMarker(lon.toFixed(5), lat.toFixed(5), carImg, name, location, status, speed.toFixed(2), direction, deviceid,list.length-i);
					setDeviceTrack();
					if (have_old && show_track) {
						var young = new BMap.Point(lon, lat);
						drawTrack(poly_old[i], young);
					}
					if (name == alert_name && status != alert_status) {
						alert("状态变化!\n" + name + ": " + alert_status + "变为" + status);
						alert_status = status;
					}
				}
				

				var tableInfos = document.getElementById('deviceTable');

				var code = '';
				for (var i = list.length-1; i >=0; i--) {
					var lon = Number(list[i].lon);
					var lat = Number(list[i].lat);
					var name = list[i].name;
					var speed = Number(list[i].speed);
					var start = list[i].start;
					var status = start.substr(0, 2);
					var location = list[i].location;

					var time = list[i].GPS_time;				
					var index = list.length-i;
					var row = 'row' + index;
					//1~25
					code += '<div id="' + row + '" data-s = "'+speed+'" data-n = "'+ name +'" class = "devicelist" style="height:50px;" index = "' + index + '" onmouseover=rowchange("'+ row +'",'+true+') onmouseout = rowchange("'+row+'",'+false+')>';
					code += '<div style="float:left;width:50px;font-size:3px;color:white" id="column' + index + '-1">' + name + '</div>';
					code += '<div style="float:left;width:20px;font-size:3px;color:white" id="column' + index + '-2">' + speed + '</div>';
					code += '<div style="float:left;width:30px;font-size:3px;color:white" id="column' + index + '-3">' + status + '</div>';
					code += '<div style="float:left;width:80px;font-size:3px;color:white" id="column' + index + '-4">' + location + '</div>';
					code += '<div style="float:left;width:80px;font-size:3px;color:white" id="column' + index + '-5">' + time + '</div>';
					code += '</div>';

				}
				tableInfos.innerHTML = code;
				for (var i =1; i<=list.length; i++){
					$("#row" + i).click(function(){
					//这里用前面title、address、telephone三个数组中存放的值来拼信息窗里的html代码，存在变量content中，然后：
					 //var info = createInfoWindow(Number(list[i].lon).toFixed(5), Number(list[i].lat).toFixed(5), list[i].name, list[i].location, list[i].start.substr(0, 2), Number(list[i].speed).toFixed(2),list[i].device_id);
					//利用在第一个问题中的markerArr数组设置触发函数，但注意数组的索引值不能用i，因为函数运行时i已不存在，因此在构造结果面板时，每个节点我添加了一个index属性，并用下面的代码获取，设置属性的代码类似于：<div id='poi5' index='5'></div>                        
					var index = $(this).attr("index")-1;
					setLock(Number(list[list.length-1-index].lon).toFixed(5), Number(list[list.length-1-index].lat).toFixed(5)); 
					markers[index].openInfoWindow(infos[index]);
					})
				}
				sorttable();
				fenye(10);
			}
		});
		document.getElementById("label").checked = false;
	}
	setTimeout("getRecord(); have_old = true;", 3000);
	setRemainTime();

	function openWin(openjsp) {
		window.open(openjsp, '_blank', '');
	}
	function setCenterAndZoom(lon, lat, speed) {
		var center = new BMap.Point(lon, lat);
		var size = 18;
		if (speed > 80) {
			size = 15;
		} else if (50 <= speed <= 80) {
			size = 16;
		}
		lock_device = true;
		horizen = true;
		setLock(lon, lat);
		map.centerAndZoom(center, size);
	}
	function setDeviceStatus() {
		if (document.getElementById("lock").checked) {
			alert("Invalid! Please select the target device.");
			lock_device = false;
			document.getElementById("lock").checked = lock_device;
		} else {
			unlockDevice();
		}
	}
		function setLock(lon, lat, title, status) {
		lock_device = true;
		document.getElementById("lock").checked = lock_device;
		var point = new BMap.Point(lon, lat);
		if (!horizen) {
			map.panTo(point);
		}
		alert_name = title;
		alert_status = status;
	}
	function unlockDevice() {
		var point = new BMap.Point(108.946465, 34.347269);
		if (horizen) {
			map.centerAndZoom(point, 5);
			horizen = false;
		} else {
			map.panTo(point);
		}
		document.getElementById("device").checked = false;
		alert_device = false;
		alert_name = null;
		alert_status = null;
	}
	function setLabelStatus() {
		if (document.getElementById("label").checked) {
			showDeviceLabel();
		} else {
			hideDeviceLabel();
		}
		setTimeout(setLabelStatus, 500);
	}
	function showDeviceLabel() {
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length; i++) {
			if (allOverlay[i] instanceof BMap.Marker) {
				allOverlay[i].getLabel().show();
			}
		}
	}
	function hideDeviceLabel() {
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length; i++) {
			if (allOverlay[i] instanceof BMap.Marker) {
				allOverlay[i].getLabel().hide();
			}
		}
	}
	setLabelStatus();

	function statusAlert() {
		if (document.getElementById("device").checked) {
			if (document.getElementById("lock").checked) {
				alert_device = true;
				alert_msg = "锁定设备:" + alert_name + " 当前状态:" + alert_status + "\n状态变化将通知您";
				alert(alert_msg);
			} else {
				alert("未锁定设备! ");
				document.getElementById("device").checked = false;
			}
		} else {
			alert_device = false;
		}
	}
	function setDeviceTrack() {
		if (document.getElementById("track").checked) {
			show_track = true;
		} else {
			show_track = false;
		}
	}
	function drawTrack(old, young) {
		var polyline = new BMap.Polyline([ old, young ], {
			strokeWeight : 3,
			strokeColor : "red",
			strokeStyle : "solid"
		});
		map.addOverlay(polyline);
	}

	function getRealtime_data() {
		var url = "BcxData";
		$.post(url, function(json) {
			console.log("running getRealtime_data()");
		});
	}
	getRealtime_data();
</script>

<script>
	function sendMessage() {
		console.log("run sendMessage()");
		realtime = setTimeout(sendMessage, 50000);
	}
	//sendMessage();
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
	function openwl(lon, lat, deviceid) {
		var itop = (window.screen.availHeight - 530) / 2;
		var ileft = (window.screen.availWidth - 810) / 2;
		console.log("----open weilan----");
		var setweilan = "setwl.jsp?" + "lon=" + lon + "&lat=" + lat + "&deviceid=" + deviceid;
		window.open(setweilan, '设置围栏', "fullscreen=0,height=500,width=800,toolbar=0,location=0,directories=0,status=0,menubar=0,resizable=0,top=" + itop + ",left=" + ileft + ",scrollbars=yes")
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
<script>
function rowchange(id,flag){
	if(flag==true){
		document.getElementById(id).style.backgroundColor="grey";
	}
	else if(flag==false){
		document.getElementById(id).style.backgroundColor="#383a3c";
	}
}
</script>

<<script type="text/javascript">
function sorttable(){
	//console.log("sorttable2");
	var test1 = document.getElementById("speeds").checked;
	var test2 = document.getElementById("letters").checked;
	//console.log(test);
    if(test1){
    	var aDiv = document.getElementsByClassName('devicelist');
    	    var arr = [];
    	    for(var i=0;i<aDiv.length;i++)
    	    {
    			//console.log(aDiv[i].getAttribute('data-id'));
    	        arr.push(aDiv[i]);  //aDiv是元素的集合，并不是数组bai，所以不能直接用数组的sort进行排序。
    	    }
    		arr = arr.sort(function compareFunction(a, b) {
    		    return a.getAttribute('data-s')-b.getAttribute('data-s');
    		});
    	    for(var i=0;i<arr.length;i++)
    	    {
    			//console.log(aDiv[i].getAttribute('data-id'));
    	        document.getElementById('deviceTable').appendChild(arr[i]); //将排好序的元素，重新塞到body里面显示。
    	    }
     }else{
        if(test2){
        	var aDiv = document.getElementsByClassName('devicelist');
        	    var arr = [];
        	    for(var i=0;i<aDiv.length;i++)
        	    {
        			//console.log(aDiv[i].getAttribute('data-id'));
        	        arr.push(aDiv[i]);  //aDiv是元素的集合，并不是数组bai，所以不能直接用数组的sort进行排序。
        	    }
        		arr = arr.sort(function compareFunction(a, b) {
        		    return a.getAttribute('data-n').localeCompare(b.getAttribute('data-n'));
        		});
        	    for(var i=0;i<arr.length;i++)
        	    {
        			//console.log(aDiv[i].getAttribute('data-id'));
        	        document.getElementById('deviceTable').appendChild(arr[i]); //将排好序的元素，重新塞到body里面显示。
        	    }
        }
      }
    }
</script>

 <script type="text/javascript" >
	function fenye(size){

		//实现分页思路:
		var pageSize=size;      //每页显示数据条数
		var currentPage=cpage;   //当前页数
		//var totalSize=$("#devicelist").index(); //获取总数据
		var aDiv = document.getElementsByClassName('devicelist');
		var totalSize=aDiv.length;
		var totalPage=Math.ceil(totalSize/pageSize); //计算总页数
		console.log(totalSize);
		//$(".devicelist:gt(9)").hide(); //设置首页显示10条数据
		$(".total").text(totalPage);  //设置总页数
		//$(".current_page").text(currentPage); //设置当前页数
		$(".current_page").text(cpage);  //当前页数先-1
		 var start=pageSize*(currentPage-1);
		 var end=pageSize*currentPage;
		 $.each($('.devicelist'),function(index,item){
			   if(index >=start && index < end){
					$(this).show();
					}
					else{
						$(this).hide();
						}
			 });
		//实现下一页
		$(".next").click(function(){
			if(currentPage ==totalPage){ //当前页数==最后一页，禁止下一页
				   return false;
				}else{//不是最后一页，显示应该显示的数据.
				    $(".current_page").text(++currentPage);  //当前页数先+1
					var start=pageSize*(currentPage-1);
					var end=pageSize*currentPage;
					$.each($('.devicelist'),function(index,item){
							if(index >=start && index < end){
								$(this).show();
								}
								else{
									$(this).hide();
									}
						});
					cpage=currentPage;
					}
			});
			
			//实现上一页
		$(".prev").click(function(){
			if(currentPage == 1){//当前页数==1，禁止上一页
			     return false;
				}else{
					 $(".current_page").text(--currentPage);  //当前页数先-1
					 var start=pageSize*(currentPage-1);
					 var end=pageSize*currentPage;
					 $.each($('.devicelist'),function(index,item){
						   if(index >=start && index < end){
								$(this).show();
								}
								else{
									$(this).hide();
									}
						 });
					 cpage=currentPage;
					}
			
			});	
		};

</script>


