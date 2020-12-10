<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginstatus.jsp' starting page</title>
    
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
							<select class="input_outer2" id="sel"
									name="username" class="box"><option>选择指定用户日志</option><option></option></select> 
						</div> 
						<button class="submit3" style="color: #FFFFFF">确认</button>
						<div style="width:100%;height:400px;overflow:auto;" id = "tableInfo"; class="speedtable"></div>
					</div>
				</div>
			</div>
		</div><!-- /container -->
		<script src="js/TweenLite.min.js"></script>
		<script src="js/EasePack.min.js"></script>
		<script src="js/rAF.js"></script>
		<script src="js/demo-1.js"></script>  
  </body>
</html>
<script src="https://cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script>
$(document).ready(function(){
		var url="/XM14/SelectUser"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
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
                    unitObj.append("<option>"+parsedJson[i].username+"</option>");
                }
            }
        },
        error:function(){
            alert('Error');
        }
    })
	
		
});
</script>
<script>
$("button").click(function(){
		var setusername=$("#sel option:selected").text();
		$.ajax({
			type : "get",
			url : "/XM14/loginstatus",
			datatype : "json",
			data:{
				username:setusername,
			},
			success : function(result) {
				var parsedJson = jQuery.parseJSON(result);
				if (parsedJson != null && parsedJson.length > 0) {
					var tableInfos = document.getElementById('tableInfo');//生成动态表格
					var code = '<TABLE style="width:100%;height:350px;color:FFFFFF;" cellpadding="0" cellspacing="1" border="1" bordercolor="pink">';
					code += '<TR><TH>用户名</TH><TH>登录时间</TH></TR>';
					for (var i = 0; i < parsedJson.length; i++) {
						code += '<TR><TD style="color: #FFFFFF;">' + parsedJson[i].username + '</TD><TD style="color: #FFFFFF;">' + parsedJson[i].login_time + '</TD></TR>';
					}
					tableInfos.innerHTML = code + '</TABLE>';
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
$(document).ready(function(){
		$.ajax({
			type : "get",
			url : "/XM14/loginstatus",
			datatype : "json",
			
			success : function(result) {
				var parsedJson = jQuery.parseJSON(result);
				if (parsedJson != null && parsedJson.length > 0) {
					var tableInfos = document.getElementById('tableInfo');//生成动态表格
					var code = '<TABLE style="width:100%;height:350px;color:FFFFFF;" cellpadding="0" cellspacing="1" border="1" bordercolor="pink">';
					code += '<TR><TH>在线用户名</TH><TH>登录时间</TH></TR>';
					for (var i = 0; i < parsedJson.length; i++) {
						code += '<TR><TD style="color: #FFFFFF;">' + parsedJson[i].username + '</TD><TD style="color: #FFFFFF;">' + parsedJson[i].login_time + '</TD></TR>';
					}
					tableInfos.innerHTML = code + '</TABLE>';
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
