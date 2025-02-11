<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>교직원 페이지</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/adminHeader.jsp"/>
</header>
    <main>
	<jsp:include page="/WEB-INF/views/layout/adminLeft.jsp"/>
        <div class="d-flex justify-content-center">
        <h2>회원 등록</h2>
        <form action="${pageContext.request.contextPath}/admin" method="post">	
            <div>
                <label for="userId">아이디:</label>
                <input type="text" id="userId" name="userId" required>
            </div>
            <div>
                <label for="userPwd">비밀번호:</label>
                <input type="password" id="userPwd" name="userPwd" required>
            </div>
            <div>
                <label for="userName">이름:</label>
                <input type="text" id="userName" name="userName" required>
            </div>
            <div>
                <label for="gender">성별:</label>
                <select id="gender" name="gender" required>
                    <option value="남자">남자</option>
                    <option value="여자">여자</option>
                </select>
            </div>
            <!-- 기타 필요한 필드들 추가 -->
            <div>
                <label for="authority">역할:</label>
                <select id="authority" name="authority" required>
                    <option value="STUDENT">학생</option>
                    <option value="TEACHER">교사</option>
                    <option value="EMPLOYEE">교직원</option>
                </select>
            </div>
            <div>
                <button type="submit">등록</button>
            </div>
        </form>
    </div>
    </main>


<footer class="fixed-bottom w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>