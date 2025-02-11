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
    <style>
        /* 화면 중앙에 박스를 위치시키기 위한 스타일 */
        .password-box-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 100px;
            
        }
        /* 네모난 박스 스타일 */
        .password-box {
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
</header>
	<main>
		<div class="container password-box-container">
			<div class="body-container password-box">
				<div class="body-title">
					<h3 class="me-5">
						<i class="bi bi-key-fill"></i> 비밀번호 확인
					</h3>
				</div>
				<!-- 에러 메시지 출력 (인증 실패 시) -->
				<c:if test="${not empty error}">
					<div class="alert alert-danger">${error}</div>
				</c:if>
				<!-- 비밀번호 확인 폼 -->
				<form
					action="${pageContext.request.contextPath}/student/mypage/passwordCheck"
					method="post">
					<div class="mb-3">
						<label for="password" class="form-label">비밀번호</label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="비밀번호를 입력하세요" required>
					</div>
					<div class="d-grid">
						<button type="submit" class="btn btn-primary">확인</button>
					</div>
				</form>
			</div>
		</div>
	</main>
	<footer class="fixed-bottom w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>