<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
import="com.model.RegVehicle" import="com.model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Cache-Control" content="no-cache">
<title>登录信息</title>
<style type="text/css">
input.form-control {-webkit-text-fill-color: #555}
</style>
<script src="https://cdn.bootcdn.net/ajax/libs/jquery/3.5.1/jquery.js"></script>


<script src="js/bootstrap.js"></script>
<link href="css/bootstrap.css" rel="stylesheet" />


<script src="js/bootstrap-table.js"></script>
<link href="css/bootstrap-table.css" rel="stylesheet" />
<script src="js/bootstrap-table-zh-CN.js"></script>
<script src="js/bootstrap-table-export.js"></script>
<script src="js/bootstrap-table-print.js"></script>

<link href="css/bootstrap-editable.css" rel="stylesheet" />
<script src="js/bootstrap-table-editable.js"></script>
<script src="js/bootstrap-editable.js"></script>

<script src="js/jquery.base64.js"></script>
<script src="js/html2canvas.min.js"></script>
<script src="js/base64.js"></script>

<script src="js/pdfmake.min.js"></script>
<script src="js/gbsn00lp_fonts.js"></script>
<script src="js/FileSaver.min.js"></script>
<script src="js/xlsx.core.min.js"></script>

<script src="js/tableExport.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<link href="css/style.css" rel='stylesheet' type='text/css' />

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
	<div class="panel-body" style="padding-bottom:0px;">
		<div class="panel panel-default" style="height:150px;">
			<div class="panel-heading">查询条件</div>
			<div class="panel-body">
				<form id="formSearch" class="form-horizontal">
					<div class="form-group" style="margin-top:15px">
						<label class="control-label col-sm-1"
							for="txt_search_departmentname">用户名</label>
						<div class="col-sm-3">
							<input type="text" class="form-control"
								id="txt_search_departmentname">
						</div>
						<label class="control-label col-sm-1" for="txt_search_statu">角色</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" id="txt_search_statu">
						</div>
						<div class="col-sm-4" style="text-align:left;">
							<button type="button" style="margin-left:50px" id="btn_query"
								class="btn btn-primary">查询</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		<div id="toolbar" class="btn-group">
			<button id="btn_add" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
		</div>
		<div id="toolbar" class="btn-group">
			<button id="btn_print" type="button" class="btn btn-default">
				<span class="glyphicon glyphicon-print" aria-hidden="true"></span>打印
			</button>
		</div>
		<table id="tb_departments"></table>
	</div>
	
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="addModalLabel" data-backdrop="false">
		<div class="modal-dialog" role="document" style="margin-top:10%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="addModalLabel">添加信息</h4>
				</div>
				<div class="modal-body">
					<form id="add_form_modal" class="form-horizontal">
					
						<div class="form-group" id="for_hide">
							<div class="col-sm-3">
								<label class="control-label" for="add_owner">用户名</label>
							</div>

							<div class="col-sm-9">
								<select class="form-control" id="selowner" name="username">
									<option class="form-control">请选择</option>
								</select>
								
							</div>
						</div>
						
						<div class="form-group">
							<div class="col-sm-3">
								<label class="control-label" for="add_role">角色类型</label>
							</div>

							<div class="col-sm-9">
								<select class="form-control" name="role" id="selrol">
								<option class="form-control">请选择</option>
								</select>
								
							</div>
							<label class="err-info-modal"></label>
						</div>


					</form>

					<div class="modal-footer">
						<button type="button" class="btn btn-default btn_reset_c"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
						</button>

						<button type="button" id="btn_add_reset"
							class="btn btn-default btn_reset_c">
							<span class="glyphicon glyphicon-share-alt" aria-hidden="true"></span>重置
						</button>

						<button type="button" id="btn_add_submit" class="btn btn-primary"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
	
<script>
$(function () {

    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();

    //2.初始化Button的点击事件
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});

var TableInit= function(){
	var oTableInit = new Object();
	columns = [
                  {
                      checkbox: true
                  },
                  {
                      field: 'id',
                      title: '用户编号',
                      align: 'center',
                      width:300,
                      //sortable:true
                  }, {
                      field: 'username',
                      title: '用户名',
                      align: 'center',
                      sortable:true,
                  }, {
                      field: 'role',
                      title: '角色',
                      align: 'center',
                      sortable:true,
                      editable:{
                      type:'select',
                    	title: '角色',
                    	source:[{value:"管理员",text:"管理员"},{value:"经理",text:"经理"},{value:"普通职员",text:"普通职员"}]
                      }
                  }, 
                   {
                      field: 'description',
                      title: '权限描述',
                      align: 'center',
                      sortable:true
                  },
                  {
                      field: 'creator',
                      title: '创建者',
                      align: 'center',
                      sortable:true
                  },  
                  {
                        field: 'id',
                        title: '操作',
                        width: 120,
                        align: 'center',
                        valign: 'middle',
                        formatter: actionFormatter,
                   }
              ];
    //初始化Table
    oTableInit.Init = function () {
    	$('#tb_departments').bootstrapTable({
          url: 'Role?method=list',   //url一般是请求后台的url地址,调用ajax获取数据。此处我用本地的json数据来填充表格。
          method: "post",                     //使用get请求到服务器获取数据
          dataType: "json",
          contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortName:"id",
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            uniqueId: "id",
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber:1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            columns:columns,
            showExport: true,  //是否显示导出按钮
        	exportDataType: "all",              //basic', 'all', 'selected'.
        	//exportDataType: $(this).val(),//显示导出范围
            exportTypes: ['json','png', 'txt', 'excel'],//导出格式
            exportOptions: {//导出设置
            				ignoreColumn: [0,6],
                            fileName: 'TableRole',//下载文件名称
                            onMsoNumberFormat: DoOnMsoNumberFormat
            },
        	showPrint:true,
            onEditableSave: function (field, row, oldValue, $el) {
                    //可进行异步操作
					console.log(row.id);
					console.log(row.role);
					console.log(oldValue);
                    $.ajax({
                        type: "post",
                        url: "Role?method=update",
                        data: {
                        	uid:row.id,
                        	role:row.role
                        },
                        success: function (data) {
                            if (data.result_code == 200) {
                                alert('修改成功');
                            }
                        },
                        error: function () {
                            alert('编辑失败');
                        },
                        complete: function () {
                        }
                    });
                }
      });
    };
    oTableInit.queryParams = function (params) {
    	console.log(params)
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,   //页面大小
            offset: params.offset,  //页码
            order: params.order,
        	ordername: params.sort,
            username: $("#txt_search_departmentname").val(),
            role: $("#txt_search_statu").val(),
        };
        return temp;
    };
    return oTableInit;
    function actionFormatter(value, row, index) {
                var id = value;
                var role = row.role;
                var result = "";
                //result += '<button id="dupUser" class="btn btn-xs blue" deviceid='+value+' onclick="updUser(' + value + ')" title="编辑"><span class="glyphicon glyphicon-pencil"></span></button>';
                result += '<button id="delUser" class="btn btn-xs red" role='+role+' uid='+value+' onclick="delUser(this)" title="删除"><span class="glyphicon glyphicon-remove"></span></button>';
                return result;
            }
    function DoOnMsoNumberFormat(cell, row, col) {
        		var result = "";
        	if (row > 0 && col == 0)
            	result = "\\@";
        	return result;
    		}
};
var ButtonInit = function () {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        //初始化页面上面的按钮事件
        $('#btn_query').on('click',function(){
			$("#tb_departments").bootstrapTable('refresh');
		});
		// 当点击【新增】按钮后，把模态框弹出来
		$("#btn_add").on("click",function (){
			$("#addModal").modal({
				keyboard: true
			});
			clearForm("#add_form_modal");// 清除表单上一次的输入数据	
		});
 		
 		$("#btn_print").on("click",function (){
				console.log("print");
				printpage();
		});
		
	// helper: 重置表单
		function clearForm(form){
    		$(form)[0].reset();
		};
 
	// 当点击【保存】按钮后，将表单中的数据提交到后台
		$("#btn_add_submit").on("click",function (){
			console.log($("#add_form_modal").serializeArray());
			$.ajax({
				type: "post",
				url: "Role?method=add",
				data: $("#add_form_modal").serializeArray(),              // 收集表单中的数据
				dataType: "text",
				success: function (d){
					var mymessage = confirm(JSON.parse(d).result_msg);
					$("#tb_departments").bootstrapTable('refresh');
				}
			});
		});
    };
    
    return oInit;
};
function printpage(){
    	var printData =$('#tb_departments').parent().html();

    	window.document.body.innerHTML = printData;
    	
        // 开始打印
        window.print();
        window.location.reload(true);
};
    
function delUser(dom) {
   
    var mymessage = confirm("确认删除嘛？");
    console.log($(this).attr("uid"));
    console.log($(this).attr("role"));
    if (mymessage == true) {
        $.ajax({
            url :'Role?method=delete',
            type : 'post',
            data:{uid:$(dom).attr('uid'),
            	  role:$(dom).attr('role')
            },
            success : function(data) {
                $(dom).parent().parent().hide();
            },
            error : function(data){
                alert("服务器繁忙")
            }
        });
    }
}

</script>
<script>	
  $(document).ready(function () {
  	console.log("运行listunalc");
    var url="Device?method=listunalc"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
    $.ajax({
        type:"post",
        url:url,
        datatype:"json",
        success:function(userList){
            var unitObj=$("#seldev"); //页面上的<html:select>元素
            //var parsedJson = jQuery.parseJSON(userList);
            //console.log(data[0].Device_id);
            var list = userList.data;
            if(list!=null){ //后台传回来的select选项
                for(var i=0;i<list.length;i++){
                    //遍历后台传回的结果，一项项往select中添加option
                    unitObj.append("<option>"+list[i].device_id+"</option>");
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
 $(document).ready(function () {
    var url="User?method=down"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
    $.ajax({
        type:"post",
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
 $(document).ready(function () {
    var url="Role?method=listrolename"; //访问后台去数据库查询select的选项,此处需填写后台接口路径
    $.ajax({
        type:"post",
        url:url,
        datatype:"json",
        success:function(userList){
            var unitObj=$("#selrol"); //页面上的<html:select>元素
            var parsedJson = jQuery.parseJSON(userList);
            //console.log(data[0].Device_id);
            if(parsedJson!=null){ //后台传回来的select选项
                for(var i=0;i<parsedJson.length;i++){
                    //遍历后台传回的结果，一项项往select中添加option
                    unitObj.append("<option>"+parsedJson[i].name+"</option>");
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
/**
 * 
 */

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