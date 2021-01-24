<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>全景</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />

<!--引用百度地图API-->
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
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

#panorama {
	width: 100%;
	height: 100%;
}

#result {
	width: 100%;
	font-size: 12px;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=3.0&ak=awORzYNz3svIeWeQ9pGPLnmZletmqfog"></script>
</head>

<body>
	<div style="float:right;height:100%;width:50%;" id="panorama"></div>
	<div style="float:right;height:100%;width:50%;" id="dituContent"></div>
</body>

<script type="text/javascript"> 
	//var map = new BMap.Map('panorama');
	//map.centerAndZoom(new BMap.Point(120.305456, 31.570037), 12);
	//map.addTileLayer(new BMap.PanoramaCoverageLayer());

	var lonti=<%=request.getParameter("lo")%>
	var lati=<%=request.getParameter("la")%>
	function paro(lontitude,latitude){ 
		var panorama = new BMap.Panorama('panorama'); 
		
	
		panorama.setPov({heading: -40, pitch: 6});
		panorama.setPosition(new BMap.Point(lontitude,latitude)); //根据经纬度坐标展示全景图		
	};
	
	
	
	//创建和初始化地图函数：
	function initMap() {
		createMap(); //创建地图
		setMapEvent(); //设置地图事件
		addMapControl(); //向地图添加控件
	}

	//创建地图函数：
	function createMap() {
		var map = new BMap.Map("dituContent"); //在百度地图容器中创建一个地图
		var point = new BMap.Point(lonti.toFixed(5), lati.toFixed(5)); //定义一个中心点坐标
		map.centerAndZoom(point, 16); //设定地图的中心点和坐标并将地图显示在地图容器中
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
			"<div class='iw_poi_content'>速度:" + speed + "</div>"
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
	
	
	initMap();
	
</script>
</html>

<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
<script>
	var deid=<%=request.getParameter("id")%>
	
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
		var url = "Record?method=list";
		var userId='${sessionScope.user.id}';
		var userName='${sessionScope.user.username}';
		var type = "全部";
		$.post(url,
		 {
			show : type,
			uid:userId,
			username:userName
		},
		function(json) {
			console.log("running getRecord()");
			window.map.clearOverlays();
			var list = json.aaData;
			if (list.length == 0) {
				console.log("Connecting--bcxgps.com");
			} else {
				//console.log(list);
				for (var i = 0; i < list.length; i++) {
				var deviceid = list[i].device_id;
					if(deid==deviceid){
						var location = list[i].location;
						var lon = Number(list[i].lon);
						var lat = Number(list[i].lat);
						var name = list[i].name;
						var status = list[i].start;
						var speed = Number(list[i].speed);
						var carImg = list[i].carImg;
						var direction = Number(list[i].direction);
						
						addCarMarker(lon.toFixed(5), lat.toFixed(5), carImg, name, location, status, speed.toFixed(2), direction,deviceid);
						paro(lon.toFixed(5),lat.toFixed(5));
						setCenterAndZoom(lon.toFixed(5),lat.toFixed(5));
					}
					
					
				}
			}
		});
		realtime = setTimeout(getRecord, 10000);
	}

	getRecord();
	
</script>



