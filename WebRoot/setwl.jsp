<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'setwl.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<div id="wloptionone">
	      <div style="width:255px;*width:260px;margin-left: 20px;*margin-left:15px;margin-top: 5px;float:left">
	      
	           <div style="height: 18px;line-height: 18px;margin-top: 2px;">
	                                     邮件接收地址：<input type="text" id="recemail" style="width: 135px;" required>
		  	      
	           </div>
	           <div style="height: 18px;line-height: 18px;margin-top: 2px;">
	                                     区域中心经度：<input type="text" id="lon" style="width: 135px;" required>
		  	      
	           </div>
	           <div style="height: 18px;line-height: 18px;margin-top: 2px;">
	                                     区域中心维度：<input type="text" id="lat" style="width: 135px;" required>
		  	      
	           </div>
	           <div style="height: 18px;line-height: 18px;margin-top: 2px;">
	                                     区域大小：<input type="text" id="radius" style="width: 135px;" required>
		  	      
	           </div>
	           <div style="height: 18px;line-height: 18px;margin-top: 2px;">
	                                     设备ID：<input type="text" id="deviceid" style="width: 135px;" disabled=disabled>
		  	      
	           </div>
	           
	       </div>

	      <div style="margin-left: 10px;width:280px;height: 1px;background:url(images/imgbcxtwo/wlfgx.png);margin-top: 40px; "></div>

	      <div style="width:255px;*width:260px;margin-left: 20px;*margin-left:15px;margin-top: 10px;">
		      <div style="float: left;width:170px;margin-top: 5px">
		      	<span id="telmessg" style="color:red;"></span>
		      </div>
		      <div style="margin-right: 10px;">
			      <input type="button" id="allbutupt" value="保存" style="margin-left: 10px; width: 50px; cursor: pointer; line-height: 20px; height: 20px; border: 0px; color: black; display: inline-block;">
		      </div>
	      </div>
</div>
</body>
</html>
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
         //遍历获取url中的所有参数的方法
window.onload = function(){//页面加载时 触发此方法
    var variables = window.location.search; //获取url中携带的参数
    if (variables != null && variables != "")
    {                                                         //判断数据是否有效
        var variablesDe = decodeURI(variables);               //重新编码,防止中文参数乱码
        var variableArray = variablesDe.substr(1).split("&"); //将参数进行分割到数组中
        var skrkhhm;
        var skrmc;
        for (var i = 0; i < variableArray.length; i++)
        {                                               //遍历数组
            var variable = variableArray[i].split("="); //参数名key与参数值按=号进行分割成数组
            switch (variable[0])
            {                                        //将参数分解开来
            case "lon":                      //参数 AccountName
                $("#lon").attr("value",variable[1]); //variable[1]  参数 AccountName 的值
                break;
            case "lat": //参数 TransferType
                $("#lat").attr("value",variable[1]); //variable[1] 参数 TransferType 的值
                break;
            case "deviceid":                      //参数 skrkhhm
                $("#deviceid").attr("value",variable[1]); //variable[1] 参数 skrkhhm 的值
                break;
            default:
            }
        }
    }
}
</script>
<script>
$(document).ready(function() {
		$("#allbutupt").click(function() {
			var lon = $('#lon').val();
			var lat = $('#lat').val();
			var radius = $('#radius').val();
			var deviceid = $('#deviceid').val();
			var email = $('#recemail').val();
			$.ajax({
				type : "get",
				url : "/XM14/Savewl",
				datatype : "json",
				data : {
					lon : lon,
					lat : lat,
					radius : radius,
					deviceid:deviceid,
					email:email
				},
				success : function(result) {
						alert("保存成功！");
					
				},
				error : function() {
						alert('Error');
				}
			})
		});
	});
</script>