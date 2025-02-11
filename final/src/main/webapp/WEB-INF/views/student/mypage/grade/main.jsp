<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>내 정보수정</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
</header>
<main>
<jsp:include page="/WEB-INF/views/layout/studentLeft.jsp"/>

<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
  	<a class="font-size small" href="#">마이페이지 ></a>
  	<a class="font-size small fw-bold" href="#">성적정보</a>
	<span class="font-size small" style="margin-left: 800px;">이승범님 반갑습니다!</span>
  </div>
  </div>
</div>

<div class="container faq-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-people"></i> 성적정보</h3>
</div>

<!-- 검색 창 -->
  <div class="row d-flex justify-content-center align-items-center ms-5">
    <div class="col-auto">
<form class="d-flex justify-content-center align-items-center" id="searchForm" action="#" method="GET">
      <select class="form-select me-2" style="width: 300px;" aria-label="Default select example" name="examType">
        <option selected>선택</option>
        <option value="1">중간</option>
        <option value="2">기말</option>
        <option value="3">계절</option>
      </select>

    <div class="col-6 d-flex">
      <button type="button" class="btn btn-light" onclick="searchList()">
        <i class="bi bi-search"></i>
      </button>
    </div>
</form>
    </div>
  </div>

<div class="enter-border" style="width: 50%; margin: 0 auto; margin-top:20px; border-top: 3px solid #424951;"></div>

<div class="body-main mt-4">
<table class="table">
  <thead>
    <tr>
      <th>번호</th>
      <th>시험유형</th>
      <th>시험일자</th>
      <th>성적</th>
      <th>학점</th>
    </tr>
  </thead>
  
  <tbody>
    <tr>
    	<td>
    		1
    	</td>
    	<td>
    		기말
    	</td>
    	<td>
    		2025-11-11
    	</td>
    	<td>
    		A+
    	</td>
    	<td>
    		5학점
    	</td>
    </tr>
  </tbody>
</table>	  
	  
	  <div class="page-navigation">
	  ${dataCount == 0 ? "페이칭 처리 테스트." : paging}
	  </div>
  </div>

 </div>
</div>
</main>
<footer class="fixed-bottom w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>