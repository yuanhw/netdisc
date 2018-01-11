<%@ page language="java"  pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Open Video</title>
	<link rel="stylesheet" type="text/css" href="../jeasyui1.5/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../jeasyui1.5/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../jeasyui1.5/demo/demo.css">
	
	<script type="text/javascript" src="../jeasyui1.5/jquery.min.js"></script>
	<script type="text/javascript" src="../jeasyui1.5/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../jeasyui1.5/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../js/comms.js"></script>
	<style>
		#ts{
			width:900px;
			margin:0 auto;
		}
		.bk{
			float:left;
			width:200px;
			height:200px;
			border:1px solid #abcdef;
			margin:5px;
			overflow: hidden;
		}
		.bk img{
			width:100%;
			height:120px;
		}
		.bk input{
			border-style:none;
		}
		.clear{
			clear:both;
		}
		#ss {
			margin:10px;
		}
		#ss span{
			font-weight:bold;
			color:#4caf50;
			margin:10px;
		}
		#ss input{
			width:105px;
		}
	</style>
</head>
<body id='main' class="easyui-layout" data-options="fit:true" style="margin: 1px 1px 1px 1px;">
	<div id='top' class='easyui-panel' data-options="region:'north'" style="overflow:auto; height:115px; padding: 1px 1px 1px 1px;text-align:center;">
		<div class="conts" style="display:none;">
			<h1>Open Video 精美视频分享</h1>
			<form id="sortf" action="openmain.action">
				<input class="easyui-textbox" type="text" id="names" name="keyword">
				<input class="easyui-combobox" type="text" id="category" name="typeid">
			</form>
			<div style="text-align: right;padding-right:100px;"><a href="../login.jsp" target="_black" style="font-weight: bold;">Cloud Person(发布视频)</a></div>
		</div>
		
	</div>	
	
	<div id='left' class='easyui-panel' data-options="region:'west', split:true" style="overflow:auto; width:250px;text-align: center;">
		<div class="conts" style="display:none;padding-top:20px;">
		<h2>热搜榜</h2>
		<ol>
			<c:forEach items="${rslist}" var="vc">
				<li><span style="color:red;">${sum}&nbsp;&nbsp;</span>
				<a href="../video/comms.action?id=${vc.id}" target="_blank">${vc.title}</a></li>
			</c:forEach>
		</ol>
		</div>
	</div>
	<div id='bottom' class='easyui-panel' data-options="region:'south'" style="height:50px; overflow:auto; padding:8px 0px 0px 20px;"><div style="margin:10px;display:none;" class="conts">点击右上角链接进入个人空间，可以分享自己的视频 ~ ~ ~</div>
	</div>
	<div id='middle' class='easyui-panel' data-options="region:'center', split:true" style="overflow:auto;text-align:center;padding:10px;">
		<div style="display:none;" class="conts">
			<div id="ts" style="">
				<c:forEach items="${mylist}" var="vc">
					<div class="bk">
						<a href="../video/comms.action?id=${vc.id}" target="_blank"><img alt="no img!" src=""></a>
					 	<a href="../video/comms.action?id=${vc.id}" target="_blank"><input type=text" value="${vc.title}" style="color:#9e9e9e;"></a>
					 	<input type=text" value="发布者：${vc.uname}">
					 	<input type=text" value="观看次数：${vc.playcount}">
					 	<input type=text" value="发布日期：${vc.altertime}">
					</div>
				</c:forEach>
				<div class="clear"></div>
			<div>
			<div id="ss">
				<a href="openmain.action?keyword=${keyword}&typeid=${typeid}&currentPage=${(currentPage-1)<1?currentPage:currentPage-1}">
				<input type="button" value="上一页"></a>
				第<span>${currentPage}</span>页
				<a  href="openmain.action?keyword=${keyword}&typeid=${typeid}&currentPage=${(currentPage+1)>totalPage?currentPage:currentPage+1}"><input type="button" value="下一页"></a>
				共<span>${totalPage}</span>页， <span>${totalRow}</span>个视频
			</div>
		</div>
		
	</div>
<script>
$(document).ready(function() {

	$("#names").textbox({
		width:346,
		prompt:'输入要搜索的视频名称',
		buttonIcon:'icon-search',
		onClickButton:function(e){
			var keyword=$("#names").textbox('getValue').trim();
			var tid=$("#category").combobox('getValue');
			if(keyword!=''){
				fn_ajax_req('upss.action',{'keyword':keyword,'tid':tid});
			}
			$("#sortf").submit();
		}
	});
$('#names').textbox('textbox').keydown(function (e) {
    if (e.keyCode == 13) {
      var keyword=$("#names").textbox('getValue').trim();
			var tid=$("#category").combobox('getValue');
			if(keyword!=''){
				fn_ajax_req('upss.action',{'keyword':keyword,'tid':tid});
			}
			$("#sortf").submit();
    }
	});
	$('#category').combobox({
		width:75,
		panelHeight:'auto',
		valueField:'id',
    	textField:'val',
    	data:[{id:1,val:'电视剧'},{id:2,val:'电影'},{id:3,val:'短视频'},{id:4,val:'动漫'},{id:5,val:'其它'},{id:0,val:'全部'}]
	});
	var typeid='${typeid}';
	if(typeid==''){
		$('#category').combobox('setValue',0);
	}else{
		$('#category').combobox('setValue',typeid);
	}
	var keyword='${keyword}';
	$('#names').textbox('setValue',keyword);
	$(".conts").show();
	
});
function down_fn(){
	alert('main/down.action?id=8');
}
</script>
</body>
</html>
