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
        f.action = '${pageContext.request.contextPath}/admin/enterManager/${mode}';
        f.submit();
    }
    
    <c:if test="${mode == 'update'}">
    function deleteFile(enterGuideNum) {
        if( ! confirm('파일을 삭제하시겠습니까?') ) {
            return;
        }
        let url = '${pageContext.request.contextPath}/admin/enterManager/deleteFile?enterGuideNum=' + enterGuideNum + '&page=${page}';
        location.href = url;
    }
    </c:if>
  </script>
</head>
<body>
  <header>
    <jsp:include page="/WEB-INF/views/layout/adminHeader.jsp"/>
  </header>
  <main>
    <jsp:include page="/WEB-INF/views/layout/adminLeft.jsp"/>
    <!-- 글 등록/수정 폼 시작 -->
    <div class="container mt-4">
      <h2><c:choose>
            <c:when test="${mode == 'update'}">글 수정</c:when>
            <c:otherwise>글 등록</c:otherwise>
          </c:choose>
      </h2>
      <form name="boardForm" action="" method="post" enctype="multipart/form-data">
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
        <!-- 첨부파일 선택 -->
        <div class="form-group mb-3">
          <label for="selectFile">첨부파일</label>
          <input type="file" name="selectFile" id="selectFile" class="form-control">
        </div>
        <!-- 카테고리 선택 (필요한 경우) -->
        <div class="form-group mb-3">
          <label for="categoryId">카테고리</label>
          <select name="categoryId" id="categoryId" class="form-control">
            <option value="2" <c:if test="${dto.categoryId == 2}">selected</c:if>>신입학</option>
            <option value="3" <c:if test="${dto.categoryId == 3}">selected</c:if>>전편입</option>
            <option value="4" <c:if test="${dto.categoryId == 4}">selected</c:if>>특별전형</option>
            <!-- 추가 옵션 -->
          </select>
          <input type="hidden" name="schoolId" value="10">
        </div>
        <!-- update 모드일 경우 기존 첨부파일 표시 및 삭제 버튼 -->
        <c:if test="${mode == 'update'}">
          <div class="form-group mb-3">
            <label>첨부된 파일</label>
            <c:if test="${not empty dto.saveFilename}">
              <p class="form-control-plaintext">
                <a href="javascript:deleteFile('${dto.enterGuideNum}')"><i class="bi bi-trash"></i></a>
                <c:out value="${dto.originalFilename}"/>
              </p>
            </c:if>
          </div>
        </c:if>
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
          <a href="${pageContext.request.contextPath}/admin/enterManager/main" class="btn btn-link">
            <c:choose>
              <c:when test="${mode == 'update'}">수정취소</c:when>
              <c:otherwise>등록취소</c:otherwise>
            </c:choose>
            &nbsp;<i class="bi bi-x"></i>
          </a>
          <!-- update 모드일 때 필요한 숨은 필드들 -->
          <c:if test="${mode == 'update'}">
            <input type="hidden" name="enterGuideNum" value="${dto.enterGuideNum}">
            <input type="hidden" name="saveFilename" value="${dto.saveFilename}">
            <input type="hidden" name="originalFilename" value="${dto.originalFilename}">
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
