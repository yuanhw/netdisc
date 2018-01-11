<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
  <head>
    <title>Cloud Person</title>
    <meta name="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../jsui/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="../jsui/jquery-ui.theme.min.css">
    <link rel="stylesheet" type="text/css" href="../css/allfile.css">
  	
  	<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
  	<script type="text/javascript" src="../js/spark-md5.min.js"></script>
	<script type="text/javascript" src="../jsui/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../js/comms.js"></script>
	<script type="text/javascript" src="../js/upfile.js"></script>
	<script type="text/javascript" src="../js/btevt.js"></script>
  </head>
  <body style="">
  <div style="text-align: center;">
  	<h1>视频发布私有空间</h1>
  	<div>
  		欢迎&nbsp;<span>${user.username}</span>&nbsp;登入！
  		<a href="../open/openmain.action" target="_black">转到在线视频共享网</a>
  	</div>
  </div>
  <div id="mylist" style="width:780px;margin:0 auto;">
  	<div id="tools" style="position:fixed;width:780px;z-index:3">
  		<div class="ds">
	    	<button id="addfolder" onclick="addf()">新建文件夹</button>
	    	<button id="upload" onclick="fn_upload(${pid})">上传</button>
	    	<button id="download" onclick="fn_down()">下载</button>
	    	<button id="delete" onclick="fn_del()">删除</button>
	    	<button id="playv" onclick="fn_playv()">发布视频</button>
	    	<a href="main.action?pid=${pid}" id="reflush"><button>刷新</button></a>
	    	<a href="../open/opened.action"><button>已发布列表</button></a>
	    	<a  href="../return.action" style="color:#ff8922;"><button>退出</button></a>
   		</div>
    	<div class="ds"><a href="mainp.action?pid=${pid}"><button id="back">返回上一级</button></a></div>
	</div>
	<div id="content" style="position:relative;top:77px;">
   		<table style="width:100%;">
	   		<thead>
	   			<th><input id="all" type="checkbox"></th>
	   			<th></th>
	   			<th>目录或文件名</th>
	   			<th>文件大小</th>
	   			<th>修改时间</th>
	   		</thead>
    		<c:forEach items="${list}" var="vc">
    			<tr>
    				<td><input value="${vc.id}" type="checkbox" name="cbx"></td>
	    			<c:choose>
		   			<c:when test="${vc.cltype.title=='vfile'}">  
		    			<td><img src="../${vc.cltype.imgPath}"></td>
		    			<td><a href="main.action?pid=${vc.id}" id="${vc.id}F">${vc.title}</a></td>
		   			</c:when>
	   				<c:otherwise> 
	   					<c:choose>
				   			<c:when test="${vc.support}">  
				   			<td><img src="../image/video.png"></td>
				    			<td><a target="_blank" href="../video/plain.action?id=${vc.id}" id="${vc.id}X">${vc.title}</a></td>
				   			</c:when>
	   					<c:otherwise>
	   					<td><img src="../${vc.cltype.imgPath}"></td>
	   					<td>${vc.title}</td>
	   					</c:otherwise>
	   					</c:choose>
	   				</c:otherwise>
				</c:choose>
	    			<td>${vc.trans}</td>
	    			<td>${vc.altertime}<input id="${vc.id}" type="hidden" value="${vc.title}"></td>
    			</tr>
    		</c:forEach>
   		</table>
    </div>
  </div>
  <div id="uploadw" title="上传文件窗口" style="display:none;">
  	<form id="upfile" action="" enctype="multipart/form-data" method="post" style="margin-top:50px;text-align:center;">
		<p><input type="file" name="myfile" id="file" style="width:300px;" onclick="hide()">
	  	<input id="bt" type="button" value="提交"></p>
	  	<span id="name"></span><br/>
		<span id="size"></span><span id="error" style="color:red;display:none;">error: 文件大小不能超过225MB！</span>
	  	<input name="pid" id="pid" type="hidden" value="${pid}">
	  	<input id="md5" name="md5" type="hidden" value="">
	  	<div id="outer" style="margin-top:5px;"></div>
	 </form>
	 <div style="height:10px;" id="mid"><div id="show3" style="background-color:#00ff00;width:0%;height:100%;"></div>
	 <div id="finash" style="display:none;text-align:center;margin-top:10px;">
	 <a href="main.action?pid=${pid}"><input type="button" value="完成"></a></div>
  </div>
  <div id="addw" style="display:none;" title="新建文件夹">
	  <form style="margin-top:50px;text-align:center;">
	  	<p>文件夹名：<input type="text" id="fname" style="width:195px;"/><span id="err0" style="color:red;display:none;margin-left:10px;">已存在</span></p>
	  	<p id="su"></p>
	  	<p><input type="button" value="创建" onclick="yes(${pid})" style="width:85px;margin-right:50px;">
	  	<a href="main.action?pid=${pid}"><input type="button" value="关闭" style="width:85px;"/></a>
	  	</p>
	  </form>
  </div>
   <div id="playw" style="display:none;" title="发布视频">
	  <form style="margin-top:50px;text-align:center;">
	  	<p>视频名称：<input type="text" id="vname" style="width:195px;"/></p>
	  	<p>视频类别： 
	  	<select sytle="width:150px;" id="type">
		    <option value="1">电视剧</option>
		    <option value="2">电影</option>
		    <option value="3">短视频</option>
		    <option value="4">动漫</option>
		    <option value="5">其它</option>
		  </select></p>
	  	<p id="show_info"></p>
	  	<input id="vid" type="hidden" value="">
	  	<p><input type="button" value="确定" onclick="v_yes()" style="width:85px;margin-right:50px;">
	  		<input type="button" value="取消" onclick="v_close()" style="width:85px;"/></a>
	  	</p>
	  </form>
  </div>
  <script>
  document.getElementById("X")
  $('#bt').click(function(){
		fn_getFileInfo('file');
	});
	$( "#uploadw,#addw,#playw" ).dialog({
		width:480,
		height:300,
	      autoOpen: false,
	      show: {
	        effect: "blind",
	        duration: 1000
	      },
	      hide: {
	        effect: "explode",
	        duration: 1000
	      }
	});
	$("#fname").focus(function(){
		$('#su').text('');
		$('#err0').hide();
	});
	$("#all").click(function() {
        $('input[name="cbx"]').prop("checked",this.checked); 
    });
	
	
  </script>
  </body>
</html>