<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>나의 학년/반 정보</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/teacherHeader.jsp"/>
</header>
<main>
<jsp:include page="/WEB-INF/views/layout/teacherLeft.jsp"/>

<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
  	<a class="font-size small" href="#">마이페이지 ></a>
  	<a class="font-size small fw-bold" href="#">담당학년/반</a>
	<span class="font-size small" style="margin-left: 800px;">이승범님 반갑습니다!</span>
  </div>
  </div>
</div>

<div class="container faq-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-people"></i> 3학년 2반 구성원</h3>
</div>

<!-- 검색 창 -->
<div class="row d-flex justify-content-center align-items-center">
  <div class="col-auto pe-0">
    <select class="form-select" style="width: 100px;" aria-label="Default select example">
      <option selected>선택</option>
      <option value="1">아이디</option>
      <option value="2">이름</option>
      <option value="3">성별</option>
    </select>
  </div>
  <div class="col-6">
    <form class="d-flex" role="enterSearchForm">
      <input class="form-control me-2" type="search" placeholder="검색할 키워드를 입력하세요" aria-label="Search">
      <button type="button" class="btn btn-light" onclick="searchList()">
        <i class="bi bi-search"></i>
      </button>
    </form>
  </div>
</div>

<div class="enter-border" style="width: 65%; margin: 0 auto; margin-top:20px; border-top: 3px solid #424951;"></div>

<div class="body-main mt-4">
<table class="table">
  <thead>
    <tr>
      <th>번호</th>
      <th>아이디</th>
      <th>이름</th>
      <th>성별</th>
      <th>생년월일</th>
      <th>주소</th>
      <th>전화번호</th>
      <th>이메일</th>
    </tr>
  </thead>
  
  <tbody>
    <tr>
    	<td>
    		1
    	</td>
    	<td>
    		ech0782
    	</td>
    	<td>
    		호날두
    	</td>
    	<td>
    		여자
    	</td>
    	<td>
    		2000-11-11
    	</td>
    	<td>
    		용산구 용산2동 용산빌라 2층
    	</td>
    	<td>
    		010-1111-1111
    	</td>
    	<td>
    		llssbb@naver.com
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