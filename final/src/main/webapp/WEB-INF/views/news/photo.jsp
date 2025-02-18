<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 - 포토갤러리</title>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/views/layout/header2.jsp"/>
</header>
<main>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
	<a href="#" class="font-size small">입학안내 > </a>
	<a href="#" class="font-size small fw-bold">포토갤러리</a> 	
  </div>
  </div>
</div>

<div class="container enter-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-image"></i> 포토갤러리</h3>
</div>

<div class="body-main">
				<div class="row row-cols-4 px-1 py-1 g-2">
					<c:forEach var="dto" items="${list}" varStatus="status">
						<div>
							<div class="col border rounded p-1 item">
								<img src="${pageContext.request.contextPath}/uploads/photo/${dto.imageFilename}">
								<p class="item-title">${dto.subject}</p>
							</div>
						</div>
					</c:forEach>
				</div>			 
	  <div class="page-navigation">
	  ${dataCount == 0 ? "페이칭 처리 테스트." : paging}
	  </div>
  </div>
</div>
</div>

</main>

<footer class="w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>