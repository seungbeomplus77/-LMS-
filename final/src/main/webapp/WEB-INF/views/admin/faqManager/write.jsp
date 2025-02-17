<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>관리자 - FAQ 작성/수정</title>
  <jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
  <jsp:include page="/WEB-INF/views/layout/adminHeader.jsp"/>
</header>
<main>
  <jsp:include page="/WEB-INF/views/layout/adminLeft.jsp"/>
  <div class="container mt-4">
    <h3>
      <c:choose>
        <c:when test="${mode == 'write'}">새 FAQ 등록</c:when>
        <c:otherwise>FAQ 수정</c:otherwise>
      </c:choose>
    </h3>
    
    <!-- mode 값에 따라 write 또는 update 액션 호출 -->
<form action="${pageContext.request.contextPath}/admin/faqManager/<c:choose><c:when test='${mode == "write"}'>write</c:when><c:otherwise>update</c:otherwise></c:choose>" method="post">
      <!-- 수정 모드라면 faqNum을 히든필드로 포함 -->
      <c:if test="${mode != 'write'}">
        <input type="hidden" name="faqNum" value="${dto.faqNum}" />
      </c:if>
      
      <div class="mb-3">
        <label for="question" class="form-label">질문</label>
        <input type="text" class="form-control" id="question" name="question"
               value="${dto.question}" required />
      </div>
      
      <div class="mb-3">
        <label for="answer" class="form-label">답변</label>
        <textarea class="form-control" id="answer" name="answer" rows="5" required>${dto.answer}</textarea>
      </div>
      
      <div class="mb-3">
        <label for="categoryId" class="form-label">카테고리</label>
        <select class="form-select" id="categoryId" name="categoryId">
          <!-- 예시: 카테고리 값 (실제 값에 맞게 수정) -->
          <option value="1" <c:if test="${dto.categoryId == 1}">selected</c:if>>전체</option>
          <option value="2" <c:if test="${dto.categoryId == 2}">selected</c:if>>고교학점제</option>
          <option value="3" <c:if test="${dto.categoryId == 3}">selected</c:if>>학교생활</option>
          <option value="4" <c:if test="${dto.categoryId == 4}">selected</c:if>>기숙사</option>
          <option value="5" <c:if test="${dto.categoryId == 5}">selected</c:if>>동아리/대외활동</option>
        </select>
      </div>
      
      <div class="mb-3">
        <label class="form-label">노출 여부</label>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="showFaq" id="showFaqYes" value="1"
                 <c:if test="${dto.showFaq == 1 || dto.showFaq == null}">checked</c:if>>
          <label class="form-check-label" for="showFaqYes">노출</label>
        </div>
        <div class="form-check">
          <input class="form-check-input" type="radio" name="showFaq" id="showFaqNo" value="0"
                 <c:if test="${dto.showFaq != 1}">checked</c:if>>
          <label class="form-check-label" for="showFaqNo">숨김</label>
        </div>
      </div>
      
      <button type="submit" class="btn btn-primary">저장</button>
      <button type="button" class="btn btn-secondary" 
              onclick="location.href='${pageContext.request.contextPath}/admin/faqManager/main?page=${page}'">
        취소
      </button>
    </form>
  </div>
</main>
<footer>
  <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>
<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>
