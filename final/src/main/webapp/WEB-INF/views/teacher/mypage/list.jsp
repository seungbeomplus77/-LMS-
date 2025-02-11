<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>마이페이지</title>
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
    <span>></span>
  	<a class="font-size small fw-bold" href="#">마이페이지</a>
	<span class="font-size small" style="margin-left: 1000px;">이승범님 반갑습니다!</span>
  </div>
  </div>
</div>
    <!-- 프로필 카드 -->
    <div class="d-flex justify-content-center align-items-center mt-5">
    <div class="container mt-4">
        <div class="card shadow-sm" style="max-width: 600px; margin: 0 auto; border-radius: 10px;">
            <div class="card-body text-center">
                <!-- 프로필 이미지 -->
                <img src="${pageContext.request.contextPath}/uploads/image/기본프로필.png" alt="프로필 이미지"
                    class="rounded-circle mb-3" style="width: 120px; height: 120px; object-fit: cover;">
                
                <!-- 이름과 역할 -->
                <h5 class="card-title fw-bold">이승범님</h5>
                <p class="card-text text-muted mt-3">담당학년/반: <strong>3-2</strong></p>
                <p class="card-text text-muted">담당과목: <strong>수학</strong></p>
                <p class="card-text text-muted">입직년도: <strong>2025-02-04</strong></p>
                <p class="card-text text-muted">이메일: <strong>iii@naver.com</strong></p>
                <p class="card-text text-muted">전화번호: <strong>010-2222-2222</strong></p>
                <p class="card-text text-muted">집주소: <strong>용산구 용산2동 용산아파트 201호</strong></p>
                <p class="card-text text-muted">이메일수신여부: <strong>X</strong></p>
                </div>
                </div>
                </div>
</div>
</main>
<footer class="ft w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>