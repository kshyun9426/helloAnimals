<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp"%>

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
          <div id="success"></div>
          <button type="button" class="btn btn-primary" data-oper="regist">등  록</button>
          <button type="button" class="btn btn-info" data-oper="list">목  록</button>
        </form>
      </div>

    </div>
	</div>
	
<script>
	
$(document).ready(function(){
	
	var actionForm = $("form");
	
	$("button").on("click",function(e){
		var oper = $(this).data("oper");
		if(oper === 'list'){
			window.location.href="http://localhost:8080/helloanimal/community/list";
		}
		if(oper ==='regist'){
			actionForm.submit();
		}
	});
	
});
	
</script>



<%@ include file="../common/footer.jsp"%>
























