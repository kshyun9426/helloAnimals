<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/header.jsp"%> 

<!-- Page Content -->
  <div class="container">
    <h1 class="mt-4 mb-3"><c:out value="${communityInfo.community_title}"/></h1>
    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="" class="backToList">목록으로</a>
      </li>
      <li class="breadcrumb-item active">작성자: <c:out value="${communityInfo.community_writer}"/></li>
    </ol>
    <div class="row d-flex flex-wrap justify-content-center">
      <div class="col-lg-10">
      	<!-- 이미지 갯수만큼 출력 -->
      	<div class="imageSection">
      		<img class="img-fluid rounded" src="/helloanimal/resources/img/noimage.gif" alt="" style="width:100%; height:300px">
      	</div>
        <hr>
        <p>Posted on <fmt:formatDate pattern="yy/MM/dd" value="${communityInfo.community_regdate}"/></p>
        <hr>
        <p class="lead" style="white-space:pre-line;"><c:out value="${communityInfo.community_content}"/></p>
        <hr>
        <!-- 댓글 공간 -->
        <div class="card my-4">
          <h5 class="card-header">Leave a Comment:</h5>
          <div class="card-body">
            <form action="" method="post" name="replyForm">
              <div class="form-group">
                <textarea class="form-control" name="reply_content" rows="3" placeholder="내용"></textarea>
              </div>
              <div class="form-group">
              	작성자: <input type="text" name="reply_writer" class="form-control col-sm-6"/>
              </div>
              <div class="form-group">
              	비밀번호: <input type="password" name="reply_password" class="form-control col-sm-6"/>
              </div>
              <button type="button" class="btn btn-primary replyBtn">등록</button>
            </form>
          </div>
        </div>

        <!-- Single Comment -->
        <div class="replySection">
        
        </div>
        
      </div>
    </div>
    <!-- /.row -->
</div>

<!-- Modal -->
<div class="modal fade" id="replyDeleteModal" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">댓글 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form name="replyDeleteForm">
          <div class="form-group">
            <label for="message-text" class="col-form-label">비밀번호:</label>
            <input type="password" class="form-control" name="reply_password" />
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary processDelete">삭 제</button>
      </div>
    </div>
  </div>
</div>
<!-- /.Modal -->

<script type="text/javascript" src="/helloanimal/resources/js/reply.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		var replyForm = $("form[name='replyForm']");
		var replyDeleteModal = $(".modal");
		var replyInputPassword = replyDeleteModal.find("input[name='reply_password']");
		var replyNo;
		var replySection = $(".replySection");
		var community_no = "<c:out value='${communityInfo.community_no}'/>";
		var preModalBodyHtml = $(".modal-body").html();
		
		showReplyList();
		
		//댓글 등록버튼 클릭 시
		$(".replyBtn").on("click", function(e){
			e.preventDefault();
			var reply = {
					community_no:community_no,
					reply_content:replyForm.find("textarea[name='reply_content']").val(),
					reply_writer:replyForm.find("input[name='reply_writer']").val(),
					reply_password:replyForm.find("input[name='reply_password']").val()
				}
				
			reviewReplyService.add(reply, function(result){
				replyForm.find("textarea[name='reply_content']").val("");
				replyForm.find("input[name='reply_writer']").val("");
				replyForm.find("input[name='reply_password']").val("");
				showReplyList();
			});
		});
		
		//댓글 출력
		function showReplyList(){
			reviewReplyService.getList({community_no:community_no},function(result){
				//댓글 출력
				var str="";
				for(var i=0, len=result.length||0; i<len; i++){
					str += '<div class="media mb-4">';
					str += '<img class="d-flex mr-3 rounded-circle" src="/helloanimal/resources/img/usericon.jpg"' 
							+ 'alt="" style="width:50px; height:50px"/>';
					str += '<div class="media-body">';
					str += '<a href="'+result[i].reply_no+'" class="float-right replyDelete">X</a>';
					str += '<h5 class="mt-0">'+result[i].reply_writer+'</h5>';
					str += '<div style="white-space:pre-line;">'+result[i].reply_content+'</div></div></div>';
				}
				replySection.html(str);
			});
		}
		
		//댓글의 X클릭시 
		replySection.on("click", "a", function(e){
			e.preventDefault();
			replyNo = $(this).attr("href");
			//비밀번호 쓰게 입력창 만들어주고 비밀번호 입력됐다면 비밀번호 확인하고 맞다면 삭제 아니면 alert(비밀번호 틀림)
			$("#replyDeleteModal").modal("show");
			replyDeleteModal.find("input").val("");
			$(".processDelete").show();
		});
		
		//모달에서 삭제버튼 클릭 시
		$(".processDelete").on("click", function(e){
			var password = replyDeleteModal.find("input[name='reply_password']").val();
			var replyInfo = {
				reply_no:replyNo,
				reply_password:password
			}
			reviewReplyService.remove(replyInfo,function(result){ 
				if(result === "notMatchPassword"){
					$(".modal-body").html("비밀번호가 일치하지 않습니다.");
					$(".processDelete").hide();
				}
				if(result === "remove success"){
					replyDeleteModal.modal("hide");
					showReplyList();
				}
					
			});
		});
		
		//모달이 없어졌을때 modal-body값 초기화
		replyDeleteModal.on("hidden.bs.modal", function(e){
			$(".modal-body").html("<form name='replyDeleteForm'><div class='form-group'>" +
				"<label for='message-text' class='col-form-label'>비밀번호:</label><input type='password' class='form-control'"+
				" name='reply_password'/></div></form>");
		});
		
		
		//상세보기에서 이미지를 출력하기위한 즉시 실행 함수
		(function(){
			var communityNo = "<c:out value='${communityInfo.community_no}'/>";
			$.getJSON("/helloanimal/attachList", {community_no:communityNo}, function(arr){
				var tempStr = "";
				var index = -1;
				$(arr).each(function(i, attach){
					var fileCallPath = encodeURIComponent(attach.uploadpath+"/"+attach.uuid+"_"+attach.imagename);
					tempStr += "<img src='/helloanimal/display?fileName="+fileCallPath+"' class='mb-3' style='width:100%; height:300px'/>";
					index++;
				});
				if(index != -1){
					$(".imageSection").html(tempStr);
				}
			});
		})();
		
	});
</script>

<%@ include file="../common/footer.jsp"%> 
















