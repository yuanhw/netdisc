<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
  <head>
    <title>注册</title>
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
    <h1>注册</h1>
    <s:fielderror></s:fielderror>
    <form id="login" action="reg.action" class="easyui-panel" title="Cloud注册界面" style="width:400px;" autocomplete="off" method="post">
    	<p>用户名：<input id="uname" name="username" class="easyui-textbox">&nbsp;<span id="promt1" style="color:red;display:none;">已存在</span></p>
    	<p>手机号：<input id="phone" name="phone" class="easyui-textbox">&nbsp;<span id="promt2" style="color:red;display:none;">无效</span></p>
    	<p><input id="send" class="easyui-linkbutton" value="发送验证码" style="width:80px;height:22px;margin-left:45px;font-size:12px;">
    	<span>&nbsp;&nbsp;&nbsp;</span>
    	<input id="vcode" class="easyui-textbox" style="width:75px;"></p>
    	<p>密&nbsp;&nbsp;&nbsp;码：<input name="password" id="password" class="easyui-textbox" type="password"></p>
    	<p><input id="bt1" class="easyui-linkbutton" value="确定">&nbsp;&nbsp;
    	   <a href="login.jsp"><input id="bt2" class="easyui-linkbutton" value="返回"></a></p>
    </form>
    <script type="text/javascript">
    
    $(document).ready(function(){
    	var vcode="";
    	$('#login').panel({
    		width:430
    	});
		$('#uname').textbox({
			width:173,
			prompt:'中文字母数字',
			iconCls:'icon-man',
			iconWidth:38
		});
		$('#password').textbox({
			width:173,
			prompt:'password',
			iconCls:'icon-lock',
			iconWidth:38
		});
		$('#phone').textbox({
			width:173,
			prompt:'没注册过的手机号'
		});
		$("input",$("#uname").next("span")).blur(function(){
			var uinfo=$("#uname").textbox('getValue').trim();
	 		if(uinfo!=''){
	 			var tag=fn_ajax_req('checkname.action',{'uinfo':uinfo});
	 			fn_hide('promt1',!tag);
	 		}
	 	});
		$("input",$("#phone").next("span")).blur(function(){
			var phone=$("#phone").textbox('getValue').trim();
			if(!fn_vphone(phone)){
				fn_hide('promt2',false);
			}else{
				var tag=fn_ajax_req('checkphone.action',{'uinfo':phone});
	 			fn_hide('promt2',!tag);
			}
	 	});
		$('#send').click(function(){
			var phone=$("#phone").textbox('getValue').trim();
			if(fn_vphone(phone)){
				var rt=fn_ajax_req('sendvcode.action',{'uinfo':phone});
				if(rt.code=='200'){
					vcode=rt.obj;
					fn_time(70);
				}else{
					$.messager.alert('警告',rt.msg);
				}
			}
		});
		$("#bt1").bind('click',function(){
			var uid=$("#uname").textbox('getValue').trim();
			var phone=$("#phone").textbox('getValue').trim();
			var password=$("#password").textbox('getValue').trim();
			var code=$("#vcode").textbox('getValue').trim();
			if(uid==''||password==''||code==''||phone==''){
				$.messager.alert('警告','字段均不能为空！');
			}else if(vcode==code){
				$('form').submit();
			}else{
				$.messager.alert('警告','验证码错误！');
			}
		});
	});
    function fn_vphone(phone){
    	return /^1[3|4|5|7|8][0-9]{9}$/.test(phone);
    }
    function fn_time(num){
    	var send=document.getElementById("send");
    	function fn(){
    		send.value=num+'s重新发送';
    		num--;
    		if(num>0){
    			setTimeout(fn,1000);
    		}else{
    			send.value='点击重新发送';
    		}
    	}
    	setTimeout(fn,1000);
    }
    </script>
  </body>
</html>

