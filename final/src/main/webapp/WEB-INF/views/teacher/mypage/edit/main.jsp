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
    <jsp:include page="/WEB-INF/views/layout/teacherHeader.jsp"/>
</header>
<main>
<jsp:include page="/WEB-INF/views/layout/teacherLeft.jsp"/>

<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
  	<a class="font-size small" href="#">마이페이지 ></a>
  	<a class="font-size small fw-bold" href="#">나의정보수정</a>
	<span class="font-size small" style="margin-left: 800px;">이승범님 반갑습니다!</span>
  </div>
  </div>
</div>

<div class="container news-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-gear-fill"></i> 나의정보수정</h3>
</div>

<!-- 프로필 카드 -->
<div class="d-flex justify-content-center align-items-center">
  <div class="container mt-4">
    <div class="card shadow-sm" style="max-width: 600px; margin: 0 auto; border-radius: 10px;">
      <div class="card-body text-center">
<!-- 프로필 이미지 -->
<img src="${pageContext.request.contextPath}/uploads/image/기본프로필.png" alt="프로필 이미지"
		class="rounded-circle mb-3" style="width: 120px; height: 120px; object-fit: cover;">
                
        <!-- 이름과 역할 -->
        <h5 class="card-title fw-bold">이승범님</h5>

        <!-- 입력 필드 -->
        <div class="d-flex flex-column gap-3 text-start">
          <div>
            <label class="fw-semibold">이메일</label>
            <input type="email" class="form-control mt-1" name="email" value="iii@naver.com">
          </div>
          <div>
            <label class="fw-semibold">휴대전화</label>
            <input type="tel" class="form-control mt-1" name="phone" value="010-2222-2222">
          </div>
          <div>
            <label class="fw-semibold">집주소</label>
            <input type="text" class="form-control mt-1" name="address" value="용산구 용산2동 용산아파트 201호">
          </div>
          <div class="d-flex justify-content-center align-items-center gap-2 mt-3">
            <label class="fw-semibold mb-0">이메일 수신 여부</label>
            <i class="bi bi-check-circle-fill text-success"></i>
            <input type="checkbox" class="form-check-input" name="email_receive" value="true">
            
            <i class="bi bi-x-circle-fill text-danger"></i>
            <input type="checkbox" class="form-check-input" name="email_receive" value="false">
      </div>
    </div>
    	<button class="btn btn-primary mt-4 w-100" onclick="mypageEdit()">수정하기</button>
   </div>
  </div>
 </div>
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