<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>글 등록/수정</title>
  <jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
  <link rel="icon" href="data:;base64,iVBORw0KGgo=">
  <style type="text/css">
    .body-container {
      max-width: 800px;
    }
  </style>
  <!-- 필요에 따라 추가 스타일시트 로드 -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/boot-board.css" type="text/css">
  
  <script type="text/javascript">
    function sendOk() {
        const f = document.boardForm;
        let str;
      
        str = f.subject.value.trim();
        if( !str ) {
            alert('제목을 입력하세요.');
            f.subject.focus();
            return;
        }
      
        str = f.content.value.trim();
        if( !str ) {
            alert('내용을 입력하세요.');
            f.content.focus();
            return;
        }
      
        // 등록 또는 수정 액션 선택 (mode에 따라)
        f.action = '${pageContext.request.contextPath}/student/community/${mode}';
        f.submit();
    }
    
  </script>
</head>
<body>
  <header>
    <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
  </header>
  <main>
    <!-- 글 등록/수정 폼 시작 -->
    <div class="container mt-4">
      <h2><c:choose>
            <c:when test="${mode == 'update'}">글 수정</c:when>
            <c:otherwise>글 등록</c:otherwise>
          </c:choose>
      </h2>
      <form name="boardForm" action="" method="post">
        <!-- 제목 -->
        <div class="form-group mb-3">
          <label for="subject">제목</label>
          <input type="text" name="subject" id="subject" class="form-control" maxlength="100" required
                 value="<c:out value='${dto.subject}'/>">
        </div>
        <!-- 내용 -->
        <div class="form-group mb-3">
          <label for="content">내용</label>
          <textarea name="content" id="content" class="form-control" rows="10" required><c:out value="${dto.content}"/></textarea>
        </div>
        <!-- 카테고리 선택 (필요한 경우) -->
        <div class="form-group mb-3">
          <label for="categoryNum">카테고리</label>
          <select name="categoryNum" id="categoryNum" class="form-control">
            <option value="2" <c:if test="${dto.categoryNum == 1}">selected</c:if>>자유</option>
            <option value="2" <c:if test="${dto.categoryNum == 2}">selected</c:if>>학업/진로</option>
            <option value="3" <c:if test="${dto.categoryNum == 3}">selected</c:if>>정보/꿀팁</option>
            <option value="4" <c:if test="${dto.categoryNum == 4}">selected</c:if>>동아리</option>
            <option value="5" <c:if test="${dto.categoryNum == 5}">selected</c:if>>고민/익명</option>
          </select>
        </div>

        <!-- 버튼들 -->
        <div class="form-group">
          <button type="button" class="btn btn-primary" onclick="sendOk();">
            <c:choose>
              <c:when test="${mode == 'update'}">수정완료</c:when>
              <c:otherwise>등록완료</c:otherwise>
            </c:choose>
            &nbsp;<i class="bi bi-check2"></i>
          </button>
          <button type="reset" class="btn btn-secondary">다시작성</button>
          <a href="${pageContext.request.contextPath}/student/community/list" class="btn btn-link">
            <c:choose>
              <c:when test="${mode == 'update'}">수정취소</c:when>
              <c:otherwise>등록취소</c:otherwise>
            </c:choose>
            &nbsp;<i class="bi bi-x"></i>
          </a>
          <!-- update 모드일 때 필요한 숨은 필드들 -->
          <c:if test="${mode == 'update'}">
            <input type="hidden" name="communityNum" value="${dto.communityNum}">
            <input type="hidden" name="page" value="${page}">
          </c:if>
        </div>
      </form>
    </div>
    <!-- 글 등록/수정 폼 끝 -->
  </main>
  <footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
  </footer>
  <jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>
