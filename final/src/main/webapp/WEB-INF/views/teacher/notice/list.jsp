<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>알림마당</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/teacherHeader.jsp"/>
</header>
<main>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
    <span>></span>
	<a href="#" class="font-size small fw-bold">알림마당</a> 	
  </div>
  </div>
</div>

<div class="container qa-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-person-raised-hand"></i> 알림마당</h3>
</div>
<!-- 카테고리 버튼 -->
<div class="category-buttons mb-3 mt-4">
  <div class="btn-group d-flex" role="group" aria-label="알림마당 카테고리">
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">전체</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">과제출제/확인</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">시험일정</button>
  </div>
</div>

<div class="body-main">
<table class="table">
  <thead>
    <tr>
      <th width="60">번호</th>
      <th width="100">구분</th>
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
    		시험일정
    	</td>
    	<td>
    		<a class="" href="#">2025년도 중간고사 안내</a>
    	</td>
    	<td>
    		2025-10-10
    	</td>
    	<td>
    		O
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