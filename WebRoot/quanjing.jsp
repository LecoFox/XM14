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
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#panorama {width:100%; height: 100%;}
		#result {width:100%;font-size:12px;}
	</style>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=3.0&ak=awORzYNz3svIeWeQ9pGPLnmZletmqfog"></script>
</head>

<body>
	<div id="panorama"></div>
</body>
</html>
<script type="text/javascript"> 
	//var map = new BMap.Map('panorama');
	//map.centerAndZoom(new BMap.Point(120.305456, 31.570037), 12);
	//map.addTileLayer(new BMap.PanoramaCoverageLayer());

	var panorama = new BMap.Panorama('panorama'); 
	var lon=<%=request.getParameter("lo")%>
	var lat=<%=request.getParameter("la")%>
	
	panorama.setPov({heading: -40, pitch: 6});
	function paro(){ 
		panorama.setPosition(new BMap.Point(lon.toFixed(5), lat.toFixed(5))); //根据经纬度坐标展示全景图		
	};
	
	paro();
</script>
</html>



