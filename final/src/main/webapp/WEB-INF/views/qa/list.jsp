<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>묻고 답해요</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
  <!-- userlevel == 50 (학생) -->
  <c:if test="${sessionScope.loginUser.userlevel == 50}">
    <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
  </c:if>

  <!-- userlevel == 60 (교사) -->
  <c:if test="${sessionScope.loginUser.userlevel == 60}">
    <jsp:include page="/WEB-INF/views/layout/teacherHeader.jsp"/>
  </c:if>
</header>
<main>

</main>
<footer class="fixed-bottom w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>