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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>轨迹回放</title>
<!--引用百度地图API-->
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	font-family: "微软雅黑";
}

#map_canvas {
	width: 100%;
	height: 500px;
}

#result {
	width: 100%
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

.h {
	height: 52px;
	line-height: 52px;
	background-image: url(img/demo-1-bg.jpg);
	background-color: #A6C0DD;
	font-size: 13px;
	font-family: "微软雅黑";
	color: white;
}

.btnh
{
    height: 23px;
    width: 50px;
    background-color: #e7e7e7; 
    color: black;
}

.btnm
{
    height: 23px;
    width: 73px;
    background-color: #008CBA;
    color: white;
}
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=3.0&ak=awORzYNz3svIeWeQ9pGPLnmZletmqfog"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/library/LuShu/1.2/src/LuShu_min.js"></script>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
</head>
<body>
	<div id="map_canvas"></div>
	<div id="result"></div>
	<div class="h">
		<div style="float:left;width:100%;" align="center">
			<form action="SearchTrack" method="post">
				<select id="tname" name="tname" style="width: 110px">
					
				</select>
				<label for="start">开始时间：</label>
				<input id="start" name="start" type="datetime" value="2020-12-01 00:00:00" />
				&nbsp;&nbsp;
				<label for="end">结束时间：</label>
				<input id="end" name="end" type="datetime" value="2020-12-31 23:59:59" />
				&nbsp;&nbsp; 
				<input type="button" class="btnh" value="查询" onclick="getTrack();">
				&nbsp;&nbsp;
				<label id="distance_label"></label>
			</form>
			<button id="run" class="btnm" onclick="startrun()">开始回放</button>
			&nbsp;&nbsp;
			<button id="stop" class="btnm" onclick="stoprun()">停止回放</button>
			&nbsp;&nbsp;
			<button id="pause" class="btnm" onclick="pauserun()">暂停回放</button>
		</div>
	</div>
</body>
<script>
	function initMap() {
		var map = new BMap.Map('map_canvas');
		map.enableScrollWheelZoom();
		map.centerAndZoom(new BMap.Point(116.404, 39.915), 13);
		window.map = map; //将map变量存储在全局
	}
	//创建marker
	function addLandMarker(lon, lat, png, title, location, GPS_time, loc) {
		var point = new BMap.Point(lon, lat);
		var iconImg = createMyIcon(png);
		var marker = new BMap.Marker(point, {
			icon : iconImg,
		});
		var iw = createInfoWindow(loc, location);
		var label = new BMap.Label(loc, {
			"offset" : new BMap.Size(5 - 6 + 10, -20)
		});
		marker.setLabel(label);
		map.addOverlay(marker);
		label.setStyle({
			borderColor : "#808080",
			color : "#333",
			cursor : "pointer"
		});
		(function() {
			var index = 0;
			var _iw = createInfoWindow(lon, lat, location, GPS_time, loc);
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
	function createInfoWindow(lon, lat, location, GPS_time, loc) {
		var iw = new BMap.InfoWindow("<b class='iw_poi_title'>" + loc + "</b>" +
			"<div class='iw_poi_content'>位置:" + location + "</div>" +
			"<div class='iw_poi_content'>经度:" + lon + "</div>" +
			"<div class='iw_poi_content'>纬度:" + lat + "</div>" +
			"<div class='iw_poi_content'>时间:" + GPS_time + "</div>"
		);
		return iw;
	}
	function createCarInfoWindow(lon, lat, tname, GPS_time) {
		var iw = new BMap.InfoWindow("<b class='iw_poi_title'>" + tname + "</b>" +
			"<div class='iw_poi_content'>经度:" + lon + "</div>" +
			"<div class='iw_poi_content'>纬度:" + lat + "</div>" +
			"<div class='iw_poi_content'>时间:" + GPS_time + "</div>"
		);
		return iw;
	}
	//创建一个Icon
	function createMyIcon(png) {
		var icon = new BMap.Icon(png, new BMap.Size(32, 38), {
			anchor: new BMap.Size(14, 38)
		});
		return icon;
	}
	initMap();
</script>

<link
	href="https://cdn.bootcss.com/jquery-datetimepicker/2.5.17/jquery.datetimepicker.min.css"
	rel="stylesheet">

<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/jquery-datetimepicker/2.5.17/jquery.datetimepicker.full.min.js"></script>
<script>
	jQuery('#start').datetimepicker({
		dateFmt:"yyyy-MM-dd HH-mm-ss",  
		lang : 'zh'
	});
	jQuery('#end').datetimepicker({
		dateFmt:"yyyy-MM-dd HH-mm-ss",  
		lang : 'zh'
	});
</script>
</html>
<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
<script>
	var lushu;
	var points = []; //点信息数组
	var start = undefined;
	var end = undefined;
	var start_time = undefined;
	var end_time = undefined;
	var length = undefined;
	var flag = false;
	var timer_on = false;
	var distance = 0;
	var tname = undefined;

	//查询点集
	function getTrack() {
		console.log("running getTrack()");
		var url = "SearchTrack";
		tname = document.getElementById("tname").value;
		start_time = document.getElementById("start").value;
		end_time = document.getElementById("end").value;
		console.log(tname);
		window.map.clearOverlays();
		$.post(url, {
			"tname" : tname,
			"start" : start_time,
			"end" : end_time
		}, function(json) {
			var list = json.aaData;
			length = list.length;
			addRoute(list);
		});
		flag = true;
	}

	//显示轨迹
	function showTrack() {
		console.log("running showTrack()");
		var polyline = new BMap.Polyline(points, {
			strokeWeight : 5,
			strokeColor : "blue",
			strokeStyle : "solid"
		});
		map.addOverlay(polyline);
	}
	
	function setCenterAndZoom(){
	 	var view = map.getViewport(eval(points));  
        var mapZoom = view.zoom;   
        var centerPoint = view.center;   
        map.centerAndZoom(centerPoint,mapZoom);  
	}
	
	function calculateDis(){
		var short_dis = 0;
		for (var i = 0; i < length - 1; i++) {
			short_dis += map.getDistance(points[i],points[i+1]);
		}
		return short_dis/1000;
	}

	//初始化路线
	function addRoute(data) {
		points = [];
		var landmarkPois = [];
		var stay = true;
		for (var i = 0; i < data.length; i++) {
			//console.log(data[i].lon, data[i].lat, data[i].time, data[i].start);
			if (i == 0) { //起点
				start = new BMap.Point(data[i].lon, data[i].lat);
				addLandMarker(data[i].lon, data[i].lat, "start.png", data[i].name, data[i].location, data[i].time, "起点");
				startTime = data[i].time;
			} else if (i == length - 1) { //终点
				end = new BMap.Point(data[i].lon, data[i].lat);
				addLandMarker(data[i].lon, data[i].lat, "end.png", data[i].name, data[i].location, data[i].time, "终点");
			} else{
				var sub = data[i].start.substring(0,2);
				if(sub == "停止"){
					if(stay){
						addLandMarker(data[i].lon, data[i].lat, "stop.png", data[i].name, data[i].location, data[i].time, "停止");
						stay = false;
					}
				} else{
					stay = true;
				}
			}
			var p = new BMap.Point(data[i].lon, data[i].lat);
			points.push(p);
		}
		
		//自适应缩放
		setCenterAndZoom();
		
		//画轨迹
		showTrack();
		
		//里程数
		distance = calculateDis();
		distance_label = "总里程数: " + distance.toFixed(4) + "公里";
		document.getElementById('distance_label').innerHTML = "" + distance_label;
		
		var speed = 1000;
		if(length < 40){
			speed = 100;
		}

		//路书
		lushu = new BMapLib.LuShu(map, points, {
			defaultContent : tname,
			autoView : true, // 是否开启自动视野调整，如果开启那么路书在运动过程中会根据视野自动调整
			icon : new BMap.Icon('car_bd.png', new BMap.Size(52, 26), {
				anchor : new BMap.Size(27, 13)
			}),
			speed : speed,
			enableRotation : true, // 是否设置marker随着道路的走向进行旋转
			landmarkPois : landmarkPois
		});
	}

	// 添加点
	function addMarker(point, gpsTime, b) {
		var marker = new BMap.Marker(point);

		map.addOverlay(marker);
		if (b)
			marker.hide();

		var label = new BMap.Label("时间:" + gpsTime + "<br/>位置:" + marker.getPosition().lng + "," + marker.getPosition().lat,
			{
				offset : new BMap.Size(20, -10)
			});
		marker.setLabel(label);
		label.hide();
	}

	function startrun() {
		console.log("startrun...");
		if (flag == false) {
			alert("请先进行查询!");
		} else {
			console.log("lushu.start()");
			lushu.start();
			//lushu.showInfoWindow();
		}
	}
	function stoprun() {
		console.log("stoptrun...");
		if (flag == false) {
			alert("请先进行查询!");
		} else {
			console.log("lushu.stop()");
			lushu.stop();
			alert("结束回放!");
		}
	}
	function pauserun() {
		console.log("pauserun...");
		if (flag == false) {
			alert("请先进行查询!");
		} else {
			console.log("lushu.pause()");
			lushu.pause();
			alert("暂停回放!");
		}
	}
</script>
<script src="jquery-1.8.3.min.js"></script>
<script>
$(document).ready(function () {
    var url="Device?method=select"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
    var userId='${sessionScope.user.id}';
    var userName='${sessionScope.user.username}';
    $.ajax({
        type:"get",
        url:url,
        datatype:"json",
        data:{
        	uid:userId,
        	username:userName
        },
        success:function(userList){
            var unitObj=$("#tname"); //页面上的<html:select>元素
            var parsedJson = jQuery.parseJSON(userList);
            //console.log(data[0].Device_id);
            if(parsedJson!=null){ //后台传回来的select选项
                for(var i=0;i<parsedJson.length;i++){
                    //遍历后台传回的结果，一项项往select中添加option
                    unitObj.append("<option value="+ parsedJson[i].car_name+ ">"+parsedJson[i].car_name+"</option>");
                }
            }
        },
        error:function(){
            J.alert('Error');
        }
    })
})
</script>