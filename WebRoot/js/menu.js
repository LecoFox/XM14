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
