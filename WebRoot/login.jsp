<%@ page language="java" pageEncoding="utf-8"%>
<%
	if(session.getAttribute("user")!=null){
		response.sendRedirect("main/main.action");
	}
%>
<!DOCTYPE html>
<html>
  <head>
    <title>登录</title>
    <meta name="content-type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" type="text/css" href="jeasyui1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jeasyui1.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jeasyui1.5/demo/demo.css">
	
	<!-- 此页面css -->
	<link rel="stylesheet" type="text/css" href="css/login_ui.css">
	
	<script type="text/javascript" src="jeasyui1.5/jquery.min.js"></script>
	<script type="text/javascript" src="jeasyui1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jeasyui1.5/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="js/comms.js"></script>
  </head>
  <body>
    <h1>Cloud Person</h1>
    <form id="login" class="easyui-panel" title="登录界面" style="width:400px;" autocomplete="off">
    	<p>账号：<input id="uid" class="easyui-textbox" /></p>
    	<p>密码：<input id="password" class="easyui-textbox" type="password" /></p>
    	<p><input id="bt1" class="easyui-linkbutton" value="登录"/>&nbsp;&nbsp;
    	   <a href="regpage.action"><input id="bt2" class="easyui-linkbutton" value="注册"/></a></p>
    </form>
    <script type="text/javascript">
    $(document).ready(function(){
		$('#uid').textbox({
			width:173,
			prompt:'用户名/手机号',
			iconCls:'icon-man',
			iconWidth:38
		});
		$('#password').textbox({
			width:173,
			prompt:'PassWord',
			iconCls:'icon-lock',
			iconWidth:38
		});
		$("#bt1").bind('click',function(){
			var uid=$("#uid").textbox('getValue').trim();
			var password=$("#password").textbox('getValue').trim();
			if(uid==''||password==''){
				$.messager.alert('警告','账号或密码不能为空！');
			}else{
				var rt=fn_ajax_req('login.action',{"uinfo":uid,"password":password});
				if(rt){
					window.location.href='main/main.action';
				}else{
					$.messager.alert('警告',"账号或密码错误！");
				}
			}
		});
	});
    </script>
  </body>
</html>

