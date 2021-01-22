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
<title>设备分配</title>
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
				<ul class="nav1" id ="clh-uni">
				
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

	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<form id="updatevehicle" action="/XM14/UpdateVehicleServlet"
								method="post">
				<center>
					<h1>车辆分配</h1>
					<br>
				</center>
				<div>
					<div
						style="display: flex;justify-content: space-around;flex-wrap: wrap; flex-direction: row;">

						<div>
								<select class="input_outer2" id="sel1" name="driverid"
									class="box"><option>选择设备号</option>
								</select>

						</div>
						<div>
								<select class="input_outer2" id="sel" name="engineid"
									class="box"><option>选择分配用户</option>
								</select>

						</div>

					</div>

					<center>
						<button id="queren" class="act-but1 submit1" style="color: #FFFFFF">确认</button>
					</center>
					<br></br>

				</div>
				<div style="width:100%;" id="tableInfo" ; class="speedtable"></div>
				</form>
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

</html>

<script>
$(document).ready(function () {
    var url="Device?method=list"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
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
                    unitObj.append("<option>"+parsedJson[i].engine_id+"</option>");
                }
            }
        },
        error:function(){
            J.alert('Error');
        }
    })
})
</script>

<script>
$(document).ready(function () {
    var url="User:list"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
    $.ajax({
        type:"get",
        url:url,
        datatype:"json",
        success:function(userList){
            var unitObj=$("#selowner"); //页面上的<html:select>元素
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
            J.alert('Error');
        }
    });
    
})
</script>

<script>
function getAllPrivilege(){
    //取出当前登录的用户信息
	   var userId='${sessionScope.user.id}';
	   console.log("id:"+userId);
	   
	   $.post("PrivilegeServlet?method=getPrivilegeByUId",{userId:userId},function(data){
		   //查询出权限
		   var allPrivilegeList=data.data;
		   
		   createToolByData($("#cbp-spmenu-s1"),allPrivilegeList);
		   
		   createMenuByData($("#clh-uni"),allPrivilegeList);
	   })
    }
	//执行获取权限的方法
    getAllPrivilege();
    //渲染到页面里面
    function createToolByData(target,allPrivilegeList){
    	
    	target.empty();
    	
    	var firstMenus=[];
    	
    	var secondMenus=[];
    	
    	$.each(allPrivilegeList,function(idx,item){
    		//有父
    		if(item.pid){
    			secondMenus.push(item);
    		}else{
    			firstMenus.push(item);
    		}
    	})
    	
    	$.each(firstMenus,function(idx,item){
    		var $a=$('<a href="'+item.url+'" id="'+item.id+'">'+item.name+'</a>')
    		target.append($a);
    		
    	})
    }
function createMenuByData(target,allPrivilegeList){
    	
    	target.empty();
    	
    	var firstMenus=[];
    	
    	var secondMenus=[];
    	
    	$.each(allPrivilegeList,function(idx,item){
    		//有父
    		if(item.pid){
    			secondMenus.push(item);
    		}else{
    			firstMenus.push(item);
    		}
    	})
    	
    	$.each(firstMenus,function(idx,item){
    		var $a=$('<li><a href="'+item.url+'">'+item.name+'</a></li>')
    		target.append($a);
    		
    	})
    }
</script>