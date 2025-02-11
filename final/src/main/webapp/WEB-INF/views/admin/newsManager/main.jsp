<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 - 미래소식</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/adminHeader.jsp"/>
</header>
<main>
	<jsp:include page="/WEB-INF/views/layout/adminLeft.jsp"/>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
	<a href="#" class="font-size small">학교소식 > </a>
	<a href="#" class="font-size small fw-bold">미래소식</a> 	
  </div>
  </div>
</div>

<div class="container news-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-newspaper"></i> 미래소식</h3>
</div>

<!-- 검색 창  -->
<div class="row d-flex justify-content-center">
  <div class="col-6 text-center">
<form class="d-flex" role="enterSearchForm">
  <input class="form-control me-2" type="search" placeholder="검색할 키워드를 입력하세요" aria-label="Search">
  <button type="button" class="btn btn-light" onclick="searchList()"> <i class="bi bi-search"></i> </button>
</form>
  </div>
</div>

<div class="enter-border" style="width: 55%; margin: 0 auto; margin-top:20px; border-top: 3px solid #424951;"></div>

<div class="body-main">
<table class="table">
  <thead>
    <tr>
      <th width="60">번호</th>
      <th>제목</th>
      <th width="100">작성일</th>
      <th width="50">파일</th>
    </tr>
  </thead>
  
  <tbody>
    <tr>
    	<td>
    		1
    	</td>
    	<td>
    		<a class="" href="#">미래를 밝힌 50인</a>
    	</td>
    	<td>
    		2025-10-10
    	</td>
    	<td>
    		X
    	</td>
    </tr>
  </tbody>
</table>
	 
	  <div class="page-navigation">
	  ${dataCount == 0 ? "페이칭 처리 테스트." : paging}
	  </div>
</div>
<div class="col text-end">
  <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/student/studentqa/write';">게시글 등록</button>
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