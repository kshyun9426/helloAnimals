<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../common/header.jsp"%>

  <!-- Page Content -->
  <div class="container mb-5">

    <!-- Page Heading/Breadcrumbs -->
    <h1 class="mt-4 mb-3">품종 : <c:out value="${animalInfo.kindCd}"/></h1>

    <ol class="breadcrumb">
      <li class="breadcrumb-item">
      	<!-- 페이지 보관해야함 -->
        <a href="/helloanimal/main/page" class="backToListBtn"><b>목록으로</b></a>
      </li>
      <li class="breadcrumb-item active">접수일: <c:out value="${animalInfo.happenDt}"/></li>
    </ol>

    <!-- Portfolio Item Row -->
    <div class="row">
      <div class="col-md-8">
        <img class="img-fluid" src="<c:out value='${animalInfo.popfile}'/>" style="width: 100%; height: 600px;"  alt="">
      </div>

      <div class="col-md-4">
        <h3 class="my-3">특징</h3>
        <p>
        	<c:out value="${animalInfo.specialMark}"/>
        </p>
        <h3 class="my-3">상세 정보</h3>
        <ul>
          <li>품종: ${animalInfo.kindCd}</li>
          <li>색상: ${animalInfo.colorCd}</li>
          <li>나이: ${animalInfo.age}</li>
          <li>무게: ${animalInfo.weight}</li>
          <li>성별: ${animalInfo.sexCd}</li>
          <li>상태: ${animalInfo.processState}</li>
          <li>중성화 여부: ${animalInfo.neuterYn}</li>
          <li>보호소 이름: ${animalInfo.careNm}</li>
          <li>보호소 번호: ${animalInfo.careTel}</li>
          <li>보호소 장소: ${animalInfo.careAddr}</li>
          <li>관할 기관: ${animalInfo.orgNm}</li>
          <li>담당자: ${animalInfo.chargeNm}</li>
          <li>담당자연락처: ${animalInfo.officetel}</li>
          <li>발견장소: ${animalInfo.happenPlace}</li>
        </ul>
      </div>
    </div>
    <!-- /.row -->
  </div>
  <!-- /.container -->

  <script>
  	$(document).ready(function(){
  		var pageNum = "<c:out value='${cri.pageNum}'/>";
  		var amount = "<c:out value='${cri.amount}'/>";
  		$(".backToListBtn").on("click", function(e){
  			e.preventDefault();
  			window.location.href = "http://localhost:8080/helloanimal/main/page?pageNum="+pageNum+"&amount="+amount;
  			
  		});
  	});
  </script>


<%@ include file="../common/footer.jsp"%>


