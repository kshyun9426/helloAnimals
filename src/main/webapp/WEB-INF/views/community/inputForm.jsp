<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}
</style>

	<div class="container">
    <div class="row justify-content-md-center">
      <div class="col-lg-9 mb-4 mt-4">
        <h3>커뮤니티 글 등록</h3>
        <form action="/helloanimal/community/input" method="post" novalidate>
          <div class="control-group form-group">
            <div class="controls">
              <label>제목:</label>
              <input type="text" class="form-control" name="community_title" 
              	required 
              	data-validation-required-message="제목을 입력해주세요">
              <p class="help-block"></p>
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>작성자:</label>
              <input type="text" class="form-control" name="community_writer" 
              	required 
              	data-validation-required-message="작성자명을 입력해주세요">
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>비밀번호:</label>
              <input type="password" class="form-control" name="community_password" 
              	required 
              	data-validation-required-message="비밀번호를 입력해주세요">
            </div>
          </div>
          <div class="control-group form-group">
            <div class="controls">
              <label>내용:</label>
              <textarea rows="10" 
              		cols="100" 
              		class="form-control" 
              		name="community_content"
              		required 
              		data-validation-required-message="내용을 입력해주세요"
              		style="resize:none"></textarea>
            </div>
          </div>
        </form>
        <div class="custom-file">
    		<input type="file" class="custom-file-input" id="customFile" name="uploadFile" multiple>
    		<label class="custom-file-label" for="customFile">Choose file</label>
  		</div>
        
        <div class="uploadResult">
        	<ul>
        		<!-- 이미지 프리뷰 공간 -->
        	</ul>
        </div>

        
        <button type="button" class="btn btn-primary" data-oper="regist">등  록</button>
    	<button type="button" class="btn btn-info" data-oper="list">목  록</button>
      </div>
    </div>
	</div>
	
<script>
	
$(document).ready(function(){
	
	var actionForm = $("form");
	var regex = new RegExp("(.*?)\.(jpg|gif|png|bmp|jpeg)$");
	var maxSize = 5242880;
	var cloneInputTypeFile = $(".custom-file").clone();
	var uploadResult = $(".uploadResult");
	
	function checkExtension(fileName, fileSize){
		if(fileName > maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		if(!regex.test(fileName)){
			console.log(regex.test(fileName));
			alert("이미지 파일만 업로드 가능합니다.");
			return false;
		}
		return true;
	}
	
	
	$("button").on("click",function(e){
		var oper = $(this).data("oper");
		var str="";
		if(oper === 'list'){
			window.location.href="http://localhost:8080/helloanimal/community/list";
		}
		if(oper ==='regist'){
			$(".uploadResult ul li").each(function(i,obj){
				var jobj = $(obj);
				console.dir(jobj);
				str += "<input type='hidden' name='attachList["+i+"].imagename' value='"+jobj.data("imagename")+"'>";
				str += "<input type='hidden' name='attachList["+i+"].uuid' value='"+jobj.data("uuid")+"'>";
				str += "<input type='hidden' name='attachList["+i+"].uploadpath' value='"+jobj.data("uploadpath")+"'>";
			});
			actionForm.append(str).submit();
		}
	});
	
	
	$('input[type="file"]').on("change", function() {
		var filenames = [];
		var files = document.getElementById("customFile").files;
	    if (files.length > 1) {
	      filenames.push("이미지 갯수 (" + files.length + ")");
	    } else {
	      for (let i in files) {
	        if (files.hasOwnProperty(i)) {
	          filenames.push(files[i].name);
	        }
	      }
	    }
	    $(this)
	      .next(".custom-file-label")
	      .html(filenames.join(","));
	    
	    var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		for(var i=0; i<files.length; i++){
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}
			formData.append("uploadFile", files[i]);
		}
		$.ajax({
			url : "/helloanimal/uploadAjaxAction",
			processData : false,
			contentType : false,
			data : formData,
			type : "post",
			dataType : "json",
			success : function(result){
				console.log(result);
				$(".custom-file").html(cloneInputTypeFile.html());
				showUploadResult(result);
			}
		});
	});
	
	function showUploadResult(uploadResultArr){
		if(!uploadResultArr || uploadResultArr.length == 0){return;}
		var uploadUL = $(".uploadResult ul");
		var str = "";
		$(uploadResultArr).each(function(i,obj){
			var fileCallPath = encodeURIComponent(obj.uploadpath+"/s_"+obj.uuid+"_"+obj.imagename);
			str += "<li data-uploadpath='"+obj.uploadpath+"' data-uuid='"+obj.uuid+"' data-imagename='"+obj.imagename+"'><div>";
			str += "<span> "+obj.imagename+"</span>&nbsp&nbsp";
			str += "<button type='button' data-file=\'"+fileCallPath+"\' class='btn btn-warning btn-circle'><i class='fa fa-times'></i></button><br>";
			str += "<img src='/helloanimal/display?fileName="+fileCallPath+"' style='width=100%;heigth=100px'>";
			str += "</div>";
		});
		uploadUL.append(str);
	}
	
	$(".uploadResult").on("click", "button", function(e){
		var targetFile = $(this).data("file");
		var targetLi = $(this).closest("li");
		$.ajax({
			url : "/helloanimal/deleteFile",
			data : {fileName:targetFile},
			dataType : "text",
			type : "post",
			success : function(result){
				//alert(result); //제대로 나옴
				targetLi.remove();
			}
		});
	});
	
});

</script>



<%@ include file="../common/footer.jsp"%>
























