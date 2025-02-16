<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/adminHeader.jsp"/>
</header>
<main>
	<jsp:include page="/WEB-INF/views/layout/adminLeft.jsp"/>
	<div class="container">
		<div class="body-container">	
			<div class="body-title">
				<h3><i class="bi bi-app"></i> 게시판 </h3>
			</div>
			
			<div class="body-main">

				<table class="table board-article">
					<thead>
						<tr>
							<td colspan="2" align="center">
								${dto.subject}
							</td>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td align="right">
								${dto.reg_date} | 조회 ${dto.hitCount}
							</td>
						</tr>
						
						<tr>
							<td colspan="2" valign="top" height="200" style="border-bottom: none;">
								${dto.content}
							</td>
						</tr>

						<tr>
							<td colspan="2">
								<c:if test="${not empty dto.saveFilename}">
									<p class="border text-secondary my-1 p-2">
										<i class="bi bi-folder2-open"></i>
										<a href="${pageContext.request.contextPath}/news/download?schoolNoticeNum=${dto.schoolNoticeNum}">${dto.originalFilename}</a>
									</p>
								</c:if>
							</td>
						</tr>

						<tr>
							<td colspan="2">
								이전글 :
								<c:if test="${not empty prevDto}">
									<a href="${pageContext.request.contextPath}/news/noticeArticle/${prevDto.schoolNoticeNum}?${query}">${prevDto.subject}</a>
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								다음글 :
								<c:if test="${not empty nextDto}">
									<a href="${pageContext.request.contextPath}/news/noticeArticle/${nextDto.schoolNoticeNum}?${query}">${nextDto.subject}</a>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
				
				<table class="table table-borderless mb-2">
					<tr>
						<td class="text-end">
							<button type="button" class="btn btn-light" onclick="location.href='${pageContext.request.contextPath}/news/notice?${query}';">리스트</button>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>	
	
	
</main>
</body>
</html>
