<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고관리</title>
<style type="text/css">
<style>
  .table {
    width: 100%;
    table-layout: fixed;
    border-collapse: collapse;
  }
  th, td {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    padding: 8px;
    text-align: center;
  }
  td button {
    min-width: 80px;
    display: flex;
    justify-content: center;  /* 가로 정렬 */
    align-items: center;      /* 세로 정렬 */
  }
</style>
<jsp:include page="/WEB-INF/views/layout/headerResources.jsp"/>
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
	<a href="#" class="font-size small fw-bold">신고관리</a> 	
  </div>
  </div>
</div>

<div class="container faq-container">
  <div class="body-container">
  
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-question-circle"></i> 신고관리</h3>

<!-- 검색 창  -->
<div class="row d-flex justify-content-center mt-4">
  <div class="col-6 text-center">
<form class="d-flex" role="enterSearchForm">
<div class="col-auto me-2">
<select id="searchType" class="form-select">
	<option value="all" ${schType=="all"?"selected":""}>제목+내용</option>
	<option value="userName" ${schType=="userName"?"selected":""}>작성자</option>
	<option value="reg_date" ${schType=="reg_date"?"selected":""}>등록일</option>
	<option value="title" ${schType=="title"?"selected":""}>제목</option>
	<option value="content" ${schType=="content"?"selected":""}>내용</option>
</select>
</div>				
  <input class="form-control me-2" type="search" placeholder="검색할 키워드를 입력하세요" aria-label="Search">
  <button type="button" class="btn btn-light" onclick="searchList()"> <i class="bi bi-search"></i> </button>
</form>
  </div>
</div>

<div class="enter-border" style="width: 55%; margin: 0 auto; margin-top:20px; border-top: 3px solid #424951;"></div>

<!-- 카테고리 버튼 -->
<div class="category-buttons mb-3 mt-4">
  <div class="btn-group d-flex" role="group" aria-label="입학안내 카테고리">
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">신고 게시글</button>
<button type="button" class="btn btn-outline-secondary flex-fill rounded-0 fw-bold">신고 댓글</button>
  </div>
</div>

<div class="body-main">

<table class="table">
  <thead>
    <tr>
      <th width="60">번호</th>
      <th width="60">게시글 번호</th>
      <th width="60">아이디</th>
      <th width="80">이름</th>
      <th width="60">상태</th>
      <th width="80">관리</th>
    </tr>
  </thead>
  
  <tbody>
    <tr>
      <td>1</td>
      <td>12</td>
      <td>echdd</td>
      <td>김처처</td>
      <td>활성</td>
		<td>
		    <button type="button" class="btn btn-primary btn-sm"
		      data-bs-toggle="modal" data-bs-target="#exampleModal">
		      상세 보기
		    </button>
		</td>
    </tr>
  </tbody>
</table>

<!-- 모달 창 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">회원 정보</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                여기에 회원 정보를 표시하세요.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>	

	  <div class="page-navigation">
	  ${dataCount == 0 ? "페이칭 처리 테스트." : paging}
	  </div>

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