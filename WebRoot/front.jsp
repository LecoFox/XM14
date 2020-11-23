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
<title>车辆管理</title>
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />

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
</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=3.0&ak=awORzYNz3svIeWeQ9pGPLnmZletmqfog"></script>
</head>

<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<!--百度地图容器-->
				<div style="width:1000px;height:100%;border:#ccc solid 1px;"
					id="dituContent"></div>

				<canvas id="demo-canvas"></canvas>
				<div class="logo_box2">
					<div class="mb2">
						<a type="submit" href="reg_vehicle.jsp" class="act-but submit2"
							style="color: #FFFFFF">车辆注册</a>
							<br> 
							</br>
							<br> 
							</br>
   						<a type="submit" href="delete_account.jsp" class="act-but submit2"
							style="color: #FFFFFF">删除账号</a>
							<br> 
							</br>
							<br> 
							</br>
   						<a type="submit" href="reg_driver.jsp" class="act-but submit2"
							style="color: #FFFFFF">驾驶员信息注册</a>
							<br> 
							</br>
							<br> 
							</br>
						<a type="submit" href="SearchallRegVehicle" class="act-but submit2"
							style="color: #FFFFFF">查看车辆注册信息</a>
							<br/>
							<br/>
							<br/>
							<br/>
						<a type="submit" href="overspeed.jsp" class="act-but submit2"
							style="color: #FFFFFF">超速统计</a>
							<br/>
							<br/>
							<br/>
						<h3>全景功能使用步骤:</h3>
						<h3>1.找到蓝色区域</h3>
						<h3>(此为可查看全景路段)</h3>
						<h3>2.放大地图至可看清道路</h3>
						<h3>3.点击右上角全景图标</h3>
						<h3>4.点击呈蓝色路段即可</h3>
					</div>
				</div>
	<!-- /container -->
	<script src="js/TweenLite.min.js"></script>
	<script src="js/EasePack.min.js"></script>
	<script src="js/rAF.js"></script>
	<script src="js/demo-1.js"></script>

</body>
<script type="text/javascript">
    //创建和初始化地图函数：
    function initMap(){
        createMap();//创建地图
        setMapEvent();//设置地图事件
        addMapControl();//向地图添加控件
    }
    
    //创建地图函数：
    function createMap(){
        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
        var point = new BMap.Point(104.989321,38.063785);//定义一个中心点坐标
        map.centerAndZoom(point,6);//设定地图的中心点和坐标并将地图显示在地图容器中
        window.map = map;//将map变量存储在全局
    }
    
    //地图事件设置函数：
    function setMapEvent(){
        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
        map.enableKeyboard();//启用键盘上下左右键移动地图
		
		
    }
    
    //地图控件添加函数：
    function addMapControl(){
        //向地图中添加缩放控件
	var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_LARGE});
	map.addControl(ctrl_nav);
        //向地图中添加缩略图控件
	var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
	map.addControl(ctrl_ove);
        //向地图中添加比例尺控件
	var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
	map.addTileLayer(new BMap.PanoramaCoverageLayer());
	var stCtrl = new BMap.PanoramaControl({anchor:BMAP_ANCHOR_TOP_RIGHT}); //构造全景控件
	stCtrl.setOffset(new BMap.Size(20, 20));
	map.addControl(stCtrl);//添加全景控件
	
    }
    //创建marker
    function addCarMarker(lon, lat, png, title, location, status, speed){
    	var point = new BMap.Point(lon,lat);
		var iconImg = createMyIcon(png);
        var marker = new BMap.Marker(point,{icon:iconImg});
		var iw = createInfoWindow(title, location);
		var label = new BMap.Label(title,{"offset":new BMap.Size(5-6+10,-20)});
		marker.setLabel(label);
        map.addOverlay(marker);
                    label.setStyle({
                        borderColor:"#808080",
                        color:"#333",
                        cursor:"pointer"
            });
			(function(){
				var index = 0;
				var _iw = createInfoWindow(lon, lat, title, location, status, speed);
				var _marker = marker;
				_marker.addEventListener("click",function(){
				    this.openInfoWindow(_iw);
			    });
			    _iw.addEventListener("open",function(){
				    _marker.getLabel().hide();
			    })
			    _iw.addEventListener("close",function(){
				    _marker.getLabel().show();
			    })
				label.addEventListener("click",function(){
				    _marker.openInfoWindow(_iw);
			    })
				if(!!this.isOpen){
					label.hide();
					_marker.openInfoWindow(_iw);
				}
			})()
    }
    //创建InfoWindow
    function createInfoWindow(lon, lat, title, location, status, speed){
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
    function createMyIcon(png){
        var icon = new BMap.Icon(png, new BMap.Size(32,32),{imageOffset: new BMap.Size(0,0),infoWindowOffset:new BMap.Size(10,1),offset:new BMap.Size(6,32)})
        return icon;
    }
    
    

    initMap();//创建和初始化地图z
</script>
</html>
<script type="text/javascript" src="jquery-1.8.3.min.js"></script>
<script>
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
					addCarMarker(lon.toFixed(5), lat.toFixed(5), carImg, name, location, status, speed.toFixed(2));
				}
			}
		});
		realtime = setTimeout(getRecord, 10000);
	}
	getRealtime_data();
	getRecord();
</script>

