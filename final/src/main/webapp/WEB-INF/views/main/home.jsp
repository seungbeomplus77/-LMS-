<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>미래 고등학교</title>

<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
<style type="text/css">
body, .main-container {
    background-image: url('/uploads/image/school.jpg'); 
    background-size: cover; 
    background-position: center; 
    background-repeat: no-repeat;
    image-rendering: crisp-edges;
    image-rendering: -webkit-optimize-contrast;
}

p { font-size: 14px; }

</style>
</head>
<body>

<header>
    <jsp:include page="/WEB-INF/views/layout/header.jsp"/>
</header>

<main>
    <!-- 로그인 창 -->
    <div class="main-container d-flex justify-content-center align-items-center vh-100">
        <div class="body-container">
            <div class="row">
                <div class="">
                    
			                    <c:if test="${not empty message}">
			    <div class="alert alert-danger" role="alert">
			        <c:out value="${message}" />
			    </div>
			</c:if>
                    
                    
                    <div class="bg-white bg-opacity-25 p-5 rounded mb-2 mt-5" style="max-width: 350px;">
					<form name="loginForm" action="${pageContext.request.contextPath}/login" method="post" class="">
					    <h3 class="text-center">
					        <img src="${pageContext.request.contextPath}/uploads/image/logo.png"
					             alt="Logo" width="110"
					             height="auto" class="">
					    </h3>
					    <!-- 아이디 입력 -->
					    <div class="col-12 mb-2">
					        <label class="mb-2">
					            <i class="bi bi-person-fill me-1"></i>아이디
					        </label>
					        <input type="text" name="userId" class="form-control" placeholder="아이디">
					    </div>
					    <!-- 비밀번호 입력 -->
					    <div class="col-12 mt-1 mb-3">
					        <label class="mb-2">
					            <i class="bi bi-lock-fill me-1"></i>비밀번호
					        </label>
					        <input type="password" name="userPwd" class="form-control" placeholder="비밀번호">
					    </div>
					    <!-- 안내 문구 -->
					    <div class="col-12 mb-4">
					        <p class="fw-bold text-dark">
					            ＊이 홈페이지는 학교 구성원을 위한 사이트로 회원가입 기능을 제공하지 않습니다.<br/>
					            ＊아이디/비밀번호를 분실하신 경우 학교 행정실로 문의 부탁드립니다.
					        </p>
					    </div>
					    <!-- 로그인 버튼 -->
					    <div class="row mt-4">
					        <button type="submit" class="btn btn-primary float-end">
					            &nbsp;로그인&nbsp;<i class="bi bi-check2"></i>
					        </button>
					    </div>
					</form>

                    </div>	
                </div>
            </div>
        </div>
    </div>
</main>

<footer class="fixed-bottom w-100 bg-light.bg-gradient fw-lighter text-light font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>
