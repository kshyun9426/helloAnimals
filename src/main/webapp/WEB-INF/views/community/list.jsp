<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../common/header.jsp"%>    
    
    <!-- Page Content -->
<div class="container">

    <!-- Page Heading/Breadcrumbs -->
    <h1 class="mt-4 mb-3">HelloAnimal 커뮤니티</h1>

    <ol class="breadcrumb">
      <li class="breadcrumb-item">
        <a href="/helloanimal/community/inputForm" class="btn btn-primary">커뮤니티 글 남기기</a>
      </li>
    </ol>


    <c:forEach items="${list}" var="community">
    <div class="card mb-4">
      <div class="card-body">
        <div class="row">
          <div class="col-lg-7">
            <a href="/helloanimal/community/detailPage">
              <img class="img-fluid rounded" src="/helloanimal/resources/img/noimage.gif" alt="" 
              	style="width:100%; height:300px">
            </a>
          </div>
          <div class="col-lg-5 d-flex flex-column">
            <h2 class="card-title"><c:out value="${community.community_title}"/></h2>
            <p class="card-text">
            	작성자: <c:out value="${community.community_writer}"/><br>
            	조회수: <c:out value="${community.community_hits}"/><br>
            </p>
            <a href="${community.community_no}" class="btn btn-primary mt-auto detailTag">상세 보기 &rarr;</a>
          </div>
        </div>
      </div>
      <div class="card-footer text-muted">
        Posted on <fmt:formatDate pattern="yy/MM/dd" value="${community.community_regdate}"/>
        <button type="button" class="btn btn-primary float-right">수정 &rarr;</button>
        <button type="button" class="btn btn-primary float-right mr-3">삭제 &rarr;</button>
      </div>
    </div>
 	</c:forEach>
 	
 	
    <div class="row d-flex flex-wrap justify-content-center">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center mt-3 mb-5">
    			<li class='page-item ${pageMaker.prev == true ? "" : "disabled"}'>
    				<a class="page-link" href="${pageMaker.startPage-1}" tabindex="-1">Previous</a>
    			</li>	
    			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
    				<li class='page-item ${pageMaker.cri.pageNum == num ? "active" : ""}'>
    					<a class='page-link' href="${num}">${num}</a>
    				</li>
    			</c:forEach>
    			<li class='page-item ${pageMaker.next == true ? "" : "disabled"}'>
    	  			<a class="page-link" href="${pageMaker.endPage+1}">Next</a>
    			</li>
  			</ul>
		</nav>
	</div>
</div>

<form id="detailPageForm" action="/helloanimal/community/page" method="get">
	<input type="hidden" name="no" value=""/>
</form>

<!-- Modal 추가 -->
<div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="infoModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="infoModalLabel">알림</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">처리가 완료되었습니다.</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
	$(document).ready(function(){
		
		var result = '<c:out value="${result}"/>';
		checkModal(result); 
		history.replaceState({},null,null); 
		function checkModal(result) {
			
			if(result === '' || history.state){
				return;
			}
			$(".modal-body").html(result);
			$("#infoModal").modal("show"); 
		}
		
		$(".detailTag").on("click", function(e){
			e.preventDefault();
			$("#detailPageForm").find("input[name='no']").val($(this).attr("href"));
			$("#detailPageForm").submit();
		});
		
		
	});
</script>
    
<%@ include file="../common/footer.jsp"%>    
    
    
    
    
    
    
    
    
    
    
    
    
    