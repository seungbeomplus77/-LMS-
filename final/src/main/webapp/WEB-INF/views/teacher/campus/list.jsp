<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>캠퍼스</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/teacherHeader.jsp"/>
</header>
<main>
<jsp:include page="/WEB-INF/views/layout/campusLeft.jsp"/>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
    <span>></span>
	<a href="#" class="font-size small fw-bold">미래 캠퍼스</a> 	
  </div>
  </div>
</div>

<div class="container faq-container">
  <div class="body-container">
  
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-question-circle"></i> 미래캠퍼스</h3>

<!-- 검색 창  -->
<div class="row d-flex justify-content-center mt-4">
  <div class="col-6 text-center">
<form class="d-flex" role="enterSearchForm">
<div class="col-auto me-2">
<select id="searchType" class="form-select">
	<option value="all" ${schType=="all"?"selected":""}>제목+내용</option>
	<option value="userName" ${schType=="userName"?"selected":""}>작성자</option>
	<option value="reg_date" ${schType=="reg_date"?"selected":""}>등록일</option>
	<option value="title" ${schType=="title"?"selected":""}>제목</option>
	<option value="content" ${schType=="content"?"selected":""}>내용</option>
</select>
</div>				
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
      <th width="100">제목</th>
      <th>작성자</th>
      <th width="100">작성일</th>
      <th width="50">파일</th>
      <th width="100">조회수</th>
    </tr>
  </thead>
  
  <tbody>
    <tr>
    	<td>
    		1
    	</td>
    	<td>
    		문학
    	</td>
    	<td>
    		김교수
    	</td>
    	<td>
    		2025-10-10
    	</td>
    	<td>
    		O
    	</td>
    	<td>
    		5
    	</td>
    </tr>
  </tbody>
</table>
	  <div class="page-navigation">
	  ${dataCount == 0 ? "페이칭 처리 테스트." : paging}
	  </div>

<div class="col text-end">
  <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/student/studentqa/write';">수업등록</button>
</div>

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