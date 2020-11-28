<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'overspeed.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/demo.css" />
<!--必要样式-->
<link rel="stylesheet" type="text/css" href="css/component.css" />
</head>

<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header">
				<canvas id="demo-canvas"></canvas>
				<div class="logo_box5">
					<div class="input_outer3">
						<input id="speed" type="text"
							style="BACKGROUND-COLOR: transparent;" name="setSpeed"
							class="text" placeholder="请输入超速范围，如40">
					</div>
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
				
					<button class="submit3" style="color: #FFFFFF">确认</button>
					<div style="width:100%;height:400px;overflow:auto;" id="tableInfo"
						; class="speedtable"></div>
					
					<button class="submit3" style="color: #FFFFFF">图表统计</button>
					<div style="width:100%;height:400px;overflow:auto;" id="chartInfo"
						; class="speedtable"></div>
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
$(document).ready(function(){
	$("button").click(function(){
		var speed = $('#speed').val();
		var Time1 = $('#datetime1').val();
		var Time2= $('#datetime2').val();
		$.ajax({
			type : "get",
			url : "/XM14/overspeed",
			datatype : "json",
			data:{
				setSpeed:speed,
				StartTime:Time1,
				EndTime:Time2
			},
			success : function(result) {
				var parsedJson = jQuery.parseJSON(result);
				if (parsedJson != null && parsedJson.length > 0) {
					var tableInfos = document.getElementById('tableInfo');//生成动态表格
					var code = '<TABLE style="width:100%;height:350px;color:FFFFFF;" cellpadding="0" cellspacing="1" border="1" bordercolor="pink">';
					code += '<TR><TH>车辆ID</TH><TH>最高时速</TH><th>超速时间段</th></TR>';
					for (var i = 0; i < parsedJson.length; i++) {
						code += '<TR><TD style="color: #FFFFFF;">' + parsedJson[i].device_id + '</TD><TD style="color: #FFFFFF;">' + parsedJson[i].maxspeed + '</TD><TD style="color: #FFFFFF;">' + parsedJson[i].btime + '~' + parsedJson[i].etime + '</TD></TR>';
					}
					tableInfos.innerHTML = code + '</TABLE>';
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