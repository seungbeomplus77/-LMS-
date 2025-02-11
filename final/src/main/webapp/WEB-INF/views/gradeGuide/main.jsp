<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학점안내</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>

</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/header2.jsp"/>
</header>
<main>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
	<a href="#" class="font-size small">학점안내 > </a>
	<a href="#" class="font-size small fw-bold">한눈에 보는 학점안내</a> 	
  </div>
  </div>
</div>

<div class="container grade-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-book"></i> 한눈에 보는 학점안내</h3>
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

<!-- 카테고리 버튼 -->
<div class="category-buttons mb-3 mt-4">
  <div class="btn-group d-flex" role="group" aria-label="입학안내 카테고리">
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">전체</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">이수기준/졸업요건</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">교과목구성/학점배정</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">성적산출방법</button>
  </div>
</div>

<div class="body-main">
<table class="table">
  <thead>
    <tr>
      <th width="60">번호</th>
      <th width="120">구분</th>
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
    		성적산출방법
    	</td>
    	<td>
    		<a class="" href="#">2025년도 미래고등학교 성적산출방법 안내</a>
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
<footer class="fixed-bottom w-100 bg-light bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>