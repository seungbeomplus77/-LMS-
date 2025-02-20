<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>spring</title>
    <jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
    <style type="text/css">
        .body-container { max-width: 870px; }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/boot-board.css" type="text/css">
</head>
<body>

<c:choose>
    <c:when test="${student}">
        <header>
            <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
        </header>
    </c:when>
    <c:otherwise>
        <header>
            <jsp:include page="/WEB-INF/views/layout/teacherHeader.jsp"/>
        </header>
    </c:otherwise>
</c:choose>

<main>
    <div class="container">
        <div class="body-container">    
            <div class="body-title">
                <h3><i class="bi bi-exclamation-square"></i> 강좌 질문과 답변 </h3>
            </div>
            
            <div class="body-main">
                <div class="row board-list-header">
                    <div class="col-auto me-auto">
                        ${dataCount}개(${page}/${total_page} 페이지)
                    </div>
                    <div class="col-auto">&nbsp;</div>
                </div>                
                
                <table class="table table-hover board-list">
                    <thead class="table-light">
                        <tr>
                            <th width="60">번호</th>
                            <th>제목</th>
                            <th width="100">작성자</th>
                            <th width="90">질문일자</th>
                            <th width="80">처리결과</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                        
                        <c:forEach var="dto" items="${list}" varStatus="status">
                            <tr>
                                <td>${dataCount - (page-1) * size - status.index}</td>
                                <td class="left">
									<c:url var="articleUrl" value="/qa/article/${dto.qaNum}">
									    <c:param name="page" value="${page}" />
									</c:url>
									
									<c:choose>
									    <c:when test="${dto.showQa == 1}">
									        <c:choose>
									            
									            <c:when test="${loginUser eq dto.userId or teacher or employee}">
									                <a href="${pageContext.request.contextPath}${articleUrl}">${dto.title}</a>
									            </c:when>
									            <c:otherwise>
									                비밀글 입니다.
									                <i class="bi bi-file-lock2"></i>
									            </c:otherwise>
									        </c:choose>
									    </c:when>
									    <c:otherwise>
									        <a href="${pageContext.request.contextPath}${articleUrl}">${dto.title}</a>
									    </c:otherwise>
									</c:choose>
                                </td>
                                <td>${dto.userId}</td>
                                <td>${dto.reg_date}</td>
                                <td>${not empty dto.answer ? "답변완료" : "답변대기"}</td>
                            </tr>	
                        </c:forEach>
                    </tbody>
                </table>
                
                <div class="page-navigation">
                    ${dataCount == 0 ? "등록된 게시물이 없습니다." : paging}
                </div>
    
                <div class="row board-list-footer">
                    <div class="col">
                        <button type="button" class="btn btn-light" onclick="location.href='${pageContext.request.contextPath}/qa/list';" title="새로고침">
                            <i class="bi bi-arrow-counterclockwise"></i>
                        </button>
                    </div>
                    <div class="col text-end">
                        <button type="button" class="btn btn-light" onclick="location.href='${pageContext.request.contextPath}/qa/write';">질문등록</button>
                    </div>
                </div>
    
            </div>
        </div>
    </div>
</main>

<footer>
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>
