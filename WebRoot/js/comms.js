function fn_ajax_req(url,data){
	var tag;
	$.ajax({
		url: url,
		data: data,
		async: false, 
		method: 'post',    
		success: function(data) {
			var dt=eval("("+data+")");
			tag=dt.rt;
		},
		error: function(err){
			$.messager.alert('警告',"服务器出现错误！");
		}
	});
	return tag;
}
function fn_hide(id,tag){
	if(tag){
		$("#"+id).hide();
	}else{
		$("#"+id).show();
	}
}