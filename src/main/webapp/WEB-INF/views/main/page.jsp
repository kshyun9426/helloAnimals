<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp"%>


<div class="container-fluid">
	<div class="row d-flex flex-wrap justify-content-center">
		<div class="col-md-10">
			<div class="card-deck">
				<c:forEach items="${animalList}" var="animalInfo" varStatus="status">
					<div class="col-sm-6 col-lg-4 col-xl-4 mt-5">
						<div class="card">
							<div class="img-responsive">
								<div class="text-center">
								<img src=<c:out value="${animalInfo.popfile}"/> class="card-img-top mt-4"
										alt="dog" style="width: 100%; height: 300px;"/>
								</div>
								<div class="card-body">
									<h5 class="card-title"><c:out value="${animalInfo.kindCd}"/></h5>
									<p class="card-text">
										색상: <c:out value="${animalInfo.colorCd}"/><br>
										나이: <c:out value="${animalInfo.age}"/><br>
										성별: <c:out value="${animalInfo.sexCd}"/><br>
										상태: <c:out value="${animalInfo.processState}"/>
									</p>
								</div>
								<div class="card-footer justify-content-between">
									<small class="text-muted col-5">접수일: <c:out value="${animalInfo.happenDt}"/></small>
									<a href="" class="btn btn-primary col-5 detailBtn" id="detailBtn_${status.index}">알아보기</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>

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



<form id="paginationForm" action="/helloanimal/main/page" method="get">
	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"/>
	<input type="hidden" name="amount" value="${pageMaker.cri.amount}"/>
</form>

<form id="detailForm" action="/helloanimal/detail/page" method="post">
	<input type="hidden" name="kindCd" value=""/>
	<input type="hidden" name="colorCd" value=""/>
	<input type="hidden" name="age" value=""/>
	<input type="hidden" name="sexCd" value=""/>
	<input type="hidden" name="popfile" value=""/>
	<input type="hidden" name="happenDt" value=""/>
	<input type="hidden" name="processState" value=""/>
	<input type="hidden" name="neuterYn" value=""/>
	<input type="hidden" name="specialMark" value=""/>
	<input type="hidden" name="careNm" value=""/>
	<input type="hidden" name="careTel" value=""/>
	<input type="hidden" name="careAddr" value=""/>
	<input type="hidden" name="orgNm" value=""/>
	<input type="hidden" name="chargeNm" value=""/>
	<input type="hidden" name="officetel" value=""/>
	<input type="hidden" name="noticeComment" value=""/>
	<input type="hidden" name="happenPlace" value=""/>
	<input type="hidden" name="weight" value=""/>
	
	<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}"/>
	<input type="hidden" name="amount" value="${pageMaker.cri.amount}"/>
</form>

<script>

	$(document).ready(function(){
		
		var paginationForm = $("#paginationForm");
		var detailForm = $("#detailForm");
		var animalInfoList = new Array();
		
		<c:forEach items='${animalList}' var='animalInfo' varStatus='status'>
			var animalInfoObj = new Object();
			animalInfoObj.kindCd = '${animalInfo.kindCd}';
			animalInfoObj.colorCd = '${animalInfo.colorCd}';
			animalInfoObj.age = '${animalInfo.age}';
			animalInfoObj.sexCd = '${animalInfo.sexCd}';
			animalInfoObj.popfile = '${animalInfo.popfile}';
			animalInfoObj.happenDt = '${animalInfo.happenDt}';
			animalInfoObj.processState = '${animalInfo.processState}';
			animalInfoObj.neuterYn = '${animalInfo.neuterYn}';
			animalInfoObj.specialMark = '${animalInfo.specialMark}';
			animalInfoObj.careNm = '${animalInfo.careNm}';
			animalInfoObj.careTel = '${animalInfo.careTel}';
			animalInfoObj.careAddr = '${animalInfo.careAddr}';
			animalInfoObj.orgNm = '${animalInfo.orgNm}';
			animalInfoObj.chargeNm = '${animalInfo.chargeNm}';
			animalInfoObj.officetel = '${animalInfo.officetel}';
			animalInfoObj.noticeComment = '${animalInfo.noticeComment}';
			animalInfoObj.happenPlace = '${animalInfo.happenPlace}';
			animalInfoObj.weight = '${animalInfo.weight}';
			animalInfoList['${status.index}'] = animalInfoObj;
		</c:forEach>
		
		
		$(".page-item a").on("click", function(e){
			e.preventDefault();
			paginationForm.find("input[name='pageNum']").val($(this).attr("href"));
			paginationForm.submit();
		});
		
		$(".detailBtn").on("click", function(e){
			e.preventDefault();
			var clickedIndex = $(this).attr("id").substring(10,11);
			detailForm.find("input[name='kindCd']").val(animalInfoList[clickedIndex].kindCd);
			detailForm.find("input[name='colorCd']").val(animalInfoList[clickedIndex].colorCd);
			detailForm.find("input[name='age']").val(animalInfoList[clickedIndex].age);
			detailForm.find("input[name='sexCd']").val(animalInfoList[clickedIndex].sexCd);
			detailForm.find("input[name='popfile']").val(animalInfoList[clickedIndex].popfile);
			detailForm.find("input[name='happenDt']").val(animalInfoList[clickedIndex].happenDt);
			detailForm.find("input[name='processState']").val(animalInfoList[clickedIndex].processState);
			detailForm.find("input[name='neuterYn']").val(animalInfoList[clickedIndex].neuterYn);
			detailForm.find("input[name='specialMark']").val(animalInfoList[clickedIndex].specialMark);
			detailForm.find("input[name='careNm']").val(animalInfoList[clickedIndex].careNm);
			detailForm.find("input[name='careTel']").val(animalInfoList[clickedIndex].careTel);
			detailForm.find("input[name='careAddr']").val(animalInfoList[clickedIndex].careAddr);
			detailForm.find("input[name='orgNm']").val(animalInfoList[clickedIndex].orgNm);
			detailForm.find("input[name='chargeNm']").val(animalInfoList[clickedIndex].chargeNm);
			detailForm.find("input[name='officetel']").val(animalInfoList[clickedIndex].officetel);
			detailForm.find("input[name='noticeComment']").val(animalInfoList[clickedIndex].noticeComment);
			detailForm.find("input[name='happenPlace']").val(animalInfoList[clickedIndex].happenPlace);
			detailForm.find("input[name='weight']").val(animalInfoList[clickedIndex].weight);
			
			
			detailForm.submit();
		});
		
	});
	
</script>

<%@ include file="../common/footer.jsp"%>
























