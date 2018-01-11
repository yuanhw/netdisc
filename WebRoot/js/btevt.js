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
	     var tag=fn_ajax_req('del.action',{'cbx':str});
	     if(tag){
	    	 var str=$("#reflush").attr('href');
	    	 window.location.href=str;
	     }
     }
}
function fn_down(){
	var chk_value=[]; 
    $('input[name="cbx"]:checked').each(function(){ 
    	 chk_value.push($(this).val()); 
    });
    if(chk_value.length==1){
    	var ff=document.getElementById(chk_value[0]+"F");
    	if(ff==null){
    		window.location.href='down.action?id='+chk_value[0];
    	}else{
    		alert('不支持文件夹下载！');
    	}
    }else{
   	 	alert('不能批量下载，请选择一个！');
    }
}
function fn_playv(){
	var chk_value=[]; 
    $('input[name="cbx"]:checked').each(function(){ 
    	 chk_value.push($(this).val()); 
    });
    if(chk_value.length==1){
    	var xx=document.getElementById(chk_value[0]+"X");
    	if(xx!=null){
    		$('#vid').val(chk_value[0]);
	   	   	 $('#vname').val($('#'+chk_value[0]).val());
	   	   	 $('#playw').dialog('open');
    	}else{
    		alert('不能发布，类型错误！');
    	}
    }else{
   	 	alert('不能批量发布视频，请选择一个！');
    }
}
function hide(){
	$('#file').val('');
	$('#error').hide();
	$('#name').text('');
	$('#size').text('');
	$('#outer').text("");
	$('#show3').css({width:"0%"});
}
function fn_upload(vid){
	hide();
	$("#finash").hide();
	$("#vid").val(vid);
	$( "#uploadw").dialog("open");
}

function addf(){
	$('#su').text('');
	$('#err0').hide();
	$( "#addw").dialog("open");
}
function yes(preid){
	var fname=$("#fname").val().trim();
	if(fname!=''){
		var rt=fn_ajax_req('addf.action',{'fname':fname,'pid':preid});
		if(rt){
			$('#err0').show();
		}else{
			$('#su').text('创建成功！');
		}
	}
}
function v_yes(){
	var title=$('#vname').val();
	var typeid=$('#type').val();
	var vid=$('#vid').val();
	var data={'vid':vid,'typeid':typeid,'title':title};
	if(title!=''){
		var rt=fn_ajax_req('../open/open_view.action',data);
		alert(rt);
		$('#playw').dialog('close');
	}else{
		alert('不能为空！');
	}
}
function v_close(){
	$('#playw').dialog('close');
}