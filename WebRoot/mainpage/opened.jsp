<%@ page language="java"  pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>发布列表</title>
    <script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../js/comms.js"></script>
    <style>
    	#main{
    		text-align:center;
    		width:960px;
    		margin:0 auto;
    	}
    	#main div{
    		margin-top:20px;
    	}
    	#bar button{
    		width:105px;
    		margin-right:20px;
    	}
    </style>
  </head>
  <body>
  <div id="main">
  	<h1>我的发布列表</h1>
  	<div id="bar">
  		<button onclick="fn_del()">取消发布</button>
  		<a href="../main/main.action"><button>返回</button></a>
  	</div>
  	<div>
  		<table style="width: 100%;">
  			<thead style="font-weight: bold;">
  				<td><input type="checkbox" id="all"></td>
  				<td>发布名称</td>
  				<td>类型</td>
  				<td>搜索次数</td>
  				<td>观看次数</td>
  				<td>发布时间</td>
  			</thead>
  			<c:forEach items="${mylist}" var="vc">
  			<tr>
  				<td><input type="checkbox" name="cbx" value="${vc.id }"></td>
  				<td>${vc.title }</td>
  				<td>${vc.type.title }</td>
  				<td>${vc.soucount }</td>
  				<td>${vc.playcount }</td>
  				<td>${vc.altertime }</td>
  			</tr>
  			</c:forEach>
  		</table>
  	</div>
  </div>
	<script>
	$("#all").click(function() {
        $('input[name="cbx"]').prop("checked",this.checked); 
    });
	function fn_del(){
		 var chk_value=[]; 
	     $('input[name="cbx"]:checked').each(function(){ 
	     	 chk_value.push($(this).val()); 
	     });
	     if(chk_value.length!=0){
	   	 var str='';
		     for(var i=0;i<chk_value.length-1;i++){
		    	 str+=chk_value[i]+',';
		     }
		     str+=chk_value[i];
		     var tag=fn_ajax_req('delp.action',{'ids':str});
		     if(tag){
		    	 window.location.href='opened.action';
		     }else{
		    	 alert('操作失败！');
		     }
	     }
	}
	</script>
  </body>
</html>
