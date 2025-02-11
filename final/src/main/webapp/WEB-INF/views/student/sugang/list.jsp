<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>수업신청</title>
<style type="text/css">

</style>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
</header>
<main>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
    <span>></span>
	<a href="#" class="font-size small fw-bold">수강신청</a> 	
  </div>
  </div>
</div>

<div class="container grade-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-book"></i> 수강신청</h3>
</div>

<div class="category-buttons mb-3 mt-4">
  <div class="btn-group d-flex" role="group" aria-label="입학안내 카테고리">
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">필수</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">진로</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">선택</button>
  </div>
</div>

      <div class="body-main">
        <div class="row g-1 mt-1 p-1">
          <!-- 국어 -->
          <div class="col-4 p-2">
            <div class="border rounded p-5 text-center">
              <div class="fs-5 mb-2">
                <span class="product-totalAmount fw-semibold text-primary">국어</span>
              </div>
					<span class="badge bg-primary rounded-pill">필수</span>	
					<span class="badge bg-primary rounded-pill">3학점</span>
					<h6 class="mt-3">이승범 교사  <span class="badge bg-secondary mt-1">월,목 11:30~12:30</span></h6>
					<button type="button" class="btn btn-sm btn-outline-secondary mt-2">내역 담기</button>		
            </div>
          </div>
          <!-- 수학 -->
          <div class="col-4 p-2">
            <div class="border rounded p-5 text-center">
              <div class="fs-5 mb-2">
                <span class="product-totalAmount fw-semibold text-primary">수학</span>
              </div>
					<span class="badge bg-success rounded-pill">선택</span>	
					<span class="badge bg-success rounded-pill">3학점</span>
					<h6 class="mt-3">이승범 교사  <span class="badge bg-secondary mt-1">월,목 11:30~12:30</span></h6>
					<button type="button" class="btn btn-sm btn-outline-secondary mt-2">내역 담기</button>						
            </div>
          </div>
          <!-- 영어 -->
          <div class="col-4 p-2">
            <div class="border rounded p-5 text-center">
              <div class="fs-5 mb-2">
                <span class="product-totalAmount fw-semibold text-primary">영어</span>
              </div>
					<span class="badge bg-danger rounded-pill">진로</span>	
					<span class="badge bg-danger rounded-pill">3학점</span>
					<h6 class="mt-3">이승범 교사  <span class="badge bg-secondary mt-1">월,목 11:30~12:30</span></h6>
					<button type="button" class="btn btn-sm btn-outline-secondary mt-2">내역 담기</button>					
            </div>
          </div>
        </div>
      </div>
    </div>

		<!-- 수강신청 내역 박스 (오른쪽 상단 고정) -->
		<div class="registration-box border rounded p-2" style="position: fixed; top: 150px; right: 10px; width: 230px;">
		  <h5 class="text-center mb-3">수강신청 내역</h5>
		  <ul class="list-group list-group-flush">
		    <li class="list-group-item d-flex justify-content-between align-items-center py-1">
		      세계정치와 경제
		      <span class="badge bg-primary rounded-pill">3학점</span>
		    </li>
		    <li class="list-group-item d-flex justify-content-between align-items-center py-1">
		      수학
		      <span class="badge bg-success rounded-pill">3학점</span>
		    </li>
		    <li class="list-group-item d-flex justify-content-between align-items-center py-1">
		      영어
		      <span class="badge bg-info rounded-pill">3학점</span>
		    </li>
		  </ul>
		  
		  <!-- 지금까지 담은 총 학점 -->
		  <div class="mt-3" style="font-size: 12px;">
		    <span class="fw-bold">총 학점: </span>
		    <span class="">필수: 9학점 /</span>
		    <span class="">선택: 10학점 /</span>
		    <span class="">진로: 5학점</span>
		  </div>
		  <!-- 수강신청하기 버튼 -->
		  <div class="mt-3">
		    <button type="button" class="btn btn-primary w-100">수강신청하기</button>
		  </div>

</div>
    </div>
</main>
<footer class="w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>