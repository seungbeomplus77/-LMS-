<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>관리자 - 자주묻는질문</title>
  <jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
  <style>
    /* FAQ 질문 행은 클릭 가능하게 */
    .faq-question { cursor: pointer; }
    /* 답변 행 스타일 */
    .faq-answer { background-color: #f9f9f9; }
  </style>
  <script type="text/javascript">
    function toggleAnswer(id) {
      var row = document.getElementById(id);
      if(row.style.display === "none") {
        row.style.display = "table-row";
      } else {
        row.style.display = "none";
      }
    }
  </script>
</head>
<body>
<header>
  <jsp:include page="/WEB-INF/views/layout/adminHeader.jsp"/>
</header>
<main>
  <jsp:include page="/WEB-INF/views/layout/adminLeft.jsp"/>
  
  <div class="breadcrumb">
    <div class="sub-breadcrumb">
      <div class="sub-sub-breadcrumb">
        <span>></span>
        <a href="#" class="font-size small fw-bold">자주묻는질문(FAQ)</a> 	
      </div>
    </div>
  </div>
  
  <div class="container faq-container">
    <div class="body-container">
      <div class="body-title">
        <h3 class="me-5"><i class="bi bi-question-circle"></i> 자주묻는질문</h3>
      </div>
      
      <!-- 카테고리 버튼 -->
      <div class="category-buttons mb-3 mt-4">
        <div class="btn-group d-flex" role="group" aria-label="FAQ 카테고리">
          <c:url var="urlAll" value="/admin/faqManager/main">
            <c:param name="categoryId" value="1" />
          </c:url>
          <c:url var="urlNew" value="/admin/faqManager/main">
            <c:param name="categoryId" value="2" />
          </c:url>
          <c:url var="urlTransfer" value="/admin/faqManager/main">
            <c:param name="categoryId" value="3" />
          </c:url>
          <c:url var="urlSpecial" value="/admin/faqManager/main">
            <c:param name="categoryId" value="4" />
          </c:url>
          <c:url var="urlBest" value="/admin/faqManager/main">
            <c:param name="categoryId" value="5" />
          </c:url>
          
          <a href="${pageContext.request.contextPath}${urlAll}" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">전체</a>
          <a href="${pageContext.request.contextPath}${urlNew}" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">고교학점제</a>
          <a href="${pageContext.request.contextPath}${urlTransfer}" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">학교생활</a>
          <a href="${pageContext.request.contextPath}/admin/faqManager/main?categoryId=4" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">기숙사</a>
          <a href="${pageContext.request.contextPath}/admin/faqManager/main?categoryId=5" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">동아리/대외활동</a>
        </div>
      </div>
      
      <!-- FAQ 목록 테이블 -->
      <table class="table table-bordered">
        <thead>
          <tr>
            <th width="60">번호</th>
            <th width="100">카테고리</th>
            <th>질문</th>
            <th width="120">등록일</th>
            <th width="120">관리</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="faq" items="${listFaq}" varStatus="status">
            <!-- 질문 행: 클릭 시 답변 토글 -->
            <tr class="faq-question" onclick="toggleAnswer('answer_${faq.faqNum}')">
              <td>${faq.faqNum}</td>
              <td>${faq.categoryName}</td>
              <td>${faq.question}</td>
              <td>${faq.reg_date}</td>
              <td>
                <a href="${pageContext.request.contextPath}/admin/faqManager/update?faqNum=${faq.faqNum}&page=${page}"
                   onclick="event.stopPropagation();">수정</a>
                |
                <a href="${pageContext.request.contextPath}/admin/faqManager/delete?faqNum=${faq.faqNum}&schType=${schType}&kwd=${kwd}&page=${page}"
                   onclick="event.stopPropagation(); return confirm('삭제하시겠습니까?');">삭제</a>
              </td>
            </tr>
            <!-- 답변 행 (초기에는 숨김) -->
            <tr id="answer_${faq.faqNum}" class="faq-answer" style="display:none;">
              <td colspan="5">
                <strong>답변:</strong><br/>
                ${faq.answer}
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      
      <!-- 페이징 -->
	  <div class="page-navigation">
	  ${dataCount == 0 ? "페이칭 처리 테스트." : paging}
	  </div>
      
      <!-- FAQ 등록 버튼 -->
      <div class="col text-end">
        <button type="button" class="btn btn-primary" 
                onclick="location.href='${pageContext.request.contextPath}/admin/faqManager/write'">
          게시글 등록
        </button>
      </div>  
      
    </div>
  </div>
</main>
<footer class="w-100 bg-light bg-gradient fw-lighter text-dark font-size small">
  <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>
<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>
