<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>학생관리</title>
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
	<a href="#" class="font-size small fw-bold">학생관리</a> 	
  </div>
  </div>
</div>

<div class="container qa-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-person-raised-hand"></i> 학생관리</h3>
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

<!-- 카테고리 버튼 -->
<div class="category-buttons mb-3 mt-4">
  <div class="btn-group d-flex" role="group" aria-label="학생관리 카테고리">
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">출석관리</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">성적관리</button>
  </div>
</div>

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
      <th>관리</th>
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
		<td class="align-middle text-center">
		    <button type="button" class="btn btn-primary btn-sm d-flex justify-content-center align-items-center" style="height: 28px; width: 100px;" data-bs-toggle="modal" data-bs-target="#exampleModal">
		        상세 보기
		    </button>
		</td>
    </tr>
  </tbody>
</table>

<!-- 모달 창 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">회원 정보</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                여기에 회원 정보를 표시하세요.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>	  
	  
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