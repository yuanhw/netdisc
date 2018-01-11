<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>${vname}</title>
  </head>
  <style>
  	#main{
  		width:960px;
  		margin:65px auto;
  		text-align:center;
  	}
  	#a1{
  		width:730px;
  		margin:0 auto;
  		text-align:center;
  	}
  	.bt{
  		width:105px;
  	}
  </style>
  <body>
  <div id="main">
  <h1>${vname}</h1>
   <div><p><input class="bt" type="button" value="使用flv播放器" onclick="addflash()">
   &nbsp;&nbsp;
   		<input class="bt" type="button" value="使用h5播放器" onclick="addhtml5()">
   	</p></div>
	<div id="a1"></div>
  </div>
   <script type="text/javascript" src="../ckplayer/ckplayer.js" charset="utf-8"></script>
	<script>
	var flashvars={
			f:'../userfiles/${pname}',
			c:0,
			b:1};
	var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
	var video=['../userfiles/${pname}->video/mp4'];
	addhtml5();
	function addflash(){
		if(CKobject.Flash()['f']){
			CKobject._K_('a1').innerHTML='';
			CKobject.embedSWF('../ckplayer/ckplayer.swf','a1','ckplayer_a1','720','480',flashvars,params);
		}
		else{
			alert('该环境中没有安装flash插件，无法切换');
		}
	}
	function addhtml5(){
		if(CKobject.isHTML5()){
			support=['all'];
			CKobject._K_('a1').innerHTML='';
			CKobject.embedHTML5('a1','ckplayer_a1',720,480,video,flashvars,support);
		}
		else{
			alert('该环境不支持html5，无法切换');
		}
	}
	</script>
  </body>
</html>
