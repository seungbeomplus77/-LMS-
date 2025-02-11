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
    <jsp:include page="/WEB-INF/views/layout/studentHeader.jsp"/>
</header>
<main>
<div class="breadcrumb">
  <div class="sub-breadcrumb">
  <div class="sub-sub-breadcrumb">
    <span>></span>
	<a href="#" class="font-size small fw-bold">묻고 답해요</a> 	
  </div>
  </div>
</div>

<div class="container qa-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-person-raised-hand"></i> 묻고 답해요</h3>
</div>

<div class="body-main">
<table class="table">
  <thead>
    <tr>
      <th width="120">분류</th>
      <th>제목</th>
      <th width="80">작성자</th>
      <th width="100">문의일자</th>
      <th width="100">처리결과</th>
    </tr>
  </thead>
  
  <tbody>
    <tr>
    	<td>
    		진로/입시
    	</td>
    	<td>
    		<a class="" href="#">대학교 입시 상담을 받고싶습니다.</a>
    	</td>
    	<td>
    		이승범
    	</td>
    	<td>
    		2025-10-10
    	</td>
    	<td>
    		처리대기
    	</td>
    </tr>
  </tbody>
</table>
	 
	  <div class="page-navigation">
	  ${dataCount == 0 ? "페이칭 처리 테스트." : paging}
	  </div>
	  
<div class="col text-end">
  <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/student/studentqa/write';">질문등록</button>
</div>
    </div>
 </div>
</div>
</main>
<footer class="fixed-bottom w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>