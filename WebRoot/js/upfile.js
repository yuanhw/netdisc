function fn_getFileInfo(id){
  	var files=document.getElementById(id).files;
	if(files.length>0){
		var info=new Object();
		info.name=files[0].name;
		info.type=files[0].type;
		info.size=files[0].size;
		if(info.size>1024*1024*225){
			$('#error').show();
			return -1;
		}
		$('#name').text('文件名：'+info.name);
		var s=trans(info.size);
		$('#size').text('大小：'+s);
		
		var blobSlice = File.prototype.slice || File.prototype.mozSlice || File.prototype.webkitSlice;
		var chunkSize = 1024*1024*10;
        var chunks = Math.ceil(files[0].size / chunkSize);
        var currentChunk = 0;
        var spark = new SparkMD5.ArrayBuffer();
        var reader=new FileReader();
        reader.onload = function (e){
	        spark.append(e.target.result);           
	        currentChunk++;
	        $('#outer').text("md5校验中："+parseInt(100*currentChunk/chunks)+"%");
	        if(currentChunk < chunks) {
	          loadNext();
	        }else{
	          info.hashMD5=spark.end();
	          var md5=info.hashMD5.substr(0, 32);
	          $('#md5').val(md5);
	          $('#outer').text("文件开始上传：");
	          $('#mid').css({border:'1px solid #00ff00'});
	          var tag=fn_ajax_req("checkmd5.action",{"md5":md5});
	          if(tag){
	        	  var pid=$("#pid").val();
	        	  var data={'myfileFileName':info.name,'myfileContentType':info.type,'myfileSize':info.size,'md5':md5,'pid':pid};
	        	  var tag1=fn_ajax_req("upfiles.action",data);
	        	  if(tag1){
	        		$('#outer').text("文件上传成功！");
	        		$('#show3').css({width:"100%"});
	        		$('#finash').show();
	        	  }
	          }else{
	        	  send_file();
	          }
	        }
        };
        function loadNext() {
        	var start = currentChunk * chunkSize,
        		end = start + chunkSize >= files[0].size ? files[0].size : start + chunkSize;
        		reader.readAsArrayBuffer(blobSlice.call(files[0], start, end));
        }
        loadNext();
	}
}
function trans(size){
	var rt='';
	if(size<1024){
		rt=size+'B';
	}else if(size<1024*1024){
		rt=parseInt(size/1024)+'kB';
	}else{
		rt=parseInt(size/1024/1024)+'MB';
	}
	return rt;
}

function send_file(){
	var formData = new FormData($("#upfile")[0]);
	$.ajax({ 
		url : "upfile.action", 
		type : 'post', 
		data : formData, 
		processData : false, 
		contentType : false,
		success:function(data) { 
			var dt=eval("("+data+")");
			if(dt.rt){
				$('#outer').text("文件已上传");
				$('#show3').css({width:"100%"});
				$('#finash').show();
			}else{
				alert('上传失败！');
			}
		}, 
		error:function() { 
			alert("error!");
		},
		xhr: function(){
		    var xhr = $.ajaxSettings.xhr();
		    if(onprogress && xhr.upload) {
		    	xhr.upload.addEventListener("progress" , onprogress, false);
		    	return xhr;
		    }
		} 
	});
}
function onprogress(evt){
	var loaded = evt.loaded; 
	var tot = evt.total;  
	var per = Math.floor(100*loaded/tot);
	if(per<98){
		$('#outer').text("文件上传进度："+per+"%");
		$('#show3').css({width:per+"%"});
	}
}