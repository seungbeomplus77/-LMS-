<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 - 미래소식</title>
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
	<a href="#" class="font-size small">학교소식 > </a>
	<a href="#" class="font-size small fw-bold">미래소식</a> 	
  </div>
  </div>
</div>

<div class="container news-container">
  <div class="body-container">
<div class="body-title">
  <h3 class="me-5"><i class="bi bi-newspaper"></i> 미래소식</h3>
</div>

<div class="col-6 text-center">
    <form class="row" name="searchForm" onsubmit="return false;">
        <div class="col-auto p-1">
            <select name="schType" class="form-select">
                <option value="all" ${schType=="all"?"selected":""}>제목+내용</option>
                <option value="reg_date" ${schType=="reg_date"?"selected":""}>등록일</option>
                <option value="subject" ${schType=="subject"?"selected":""}>제목</option>
                <option value="content" ${schType=="content"?"selected":""}>내용</option>
            </select>
        </div>
        <div class="col-auto p-1">
            <input type="text" name="kwd" value="${kwd}" class="form-control" placeholder="검색어 입력">
        </div>
        <div class="col-auto p-1">
            <button type="button" class="btn btn-light" onclick="searchList()"> 
                <i class="bi bi-search"></i>
            </button>
        </div>
    </form>
</div>

<div class="enter-border" style="width: 55%; margin: 0 auto; margin-top:20px; border-top: 3px solid #424951;"></div>

<div class="body-main">
<table class="table">
  <thead>
    <tr>
      <th width="60">번호</th>
      <th width="100">구분</th>
      <th>제목</th>
      <th width="100">작성일</th>
      <th width="50">파일</th>
    </tr>
  </thead>
  
  <tbody>
    <c:forEach var="dto" items="${listSchoolNews}" varStatus="status">
    <tr>
    	<td>
    		${dto.schoolNewsNum}
    	</td>
    	<td>
			<a href="${pageContext.request.contextPath}/news/article/${dto.schoolNewsNum}?page=${page}${not empty query ? '&' : ''}${query}" class="text-reset">
			    ${dto.subject}
			</a>
    	</td>
    	<td>
    		${dto.content}
    	</td>
    	<td>
    		O
    	</td>
    </tr>
    </c:forEach>
  </tbody>
</table>
	  <div class="page-navigation">
	  ${dataCount == 0 ? "페이칭 처리 테스트." : paging}
	  </div>
    </div>
    
  </div>
</div>
</main>
<script type="text/javascript">
  // 페이지 로드 후, 엔터키 입력 시 searchList() 실행
  window.addEventListener('load', () => {
    const inputEL = document.querySelector('form[name="searchForm"] input[name="kwd"]');
    if(inputEL) {
      inputEL.addEventListener('keydown', function(evt) {
        if(evt.key === 'Enter') {
          evt.preventDefault();
          searchList();
        }
      });
    }
  });

  function searchList() {
    const f = document.searchForm; // 폼의 name="searchForm"
    
    // 검색어가 없으면 실행하지 않음
    if (!f.kwd.value.trim()) {
      alert("검색어를 입력하세요.");
      f.kwd.focus();
      return;
    }
    
    // 폼 데이터를 FormData로 가져와 URLSearchParams로 변환
    const formData = new FormData(f);
    const requestParams = new URLSearchParams(formData).toString();
    
    // 관리 영역의 메인 페이지 URL (컨텍스트 경로 포함)
    const url = '${pageContext.request.contextPath}/news/main';
    location.href = url + '?' + requestParams;
  }
</script>
<footer class="fixed-bottom w-100 bg-light.bg-gradient fw-lighter text-dark font-size small">
    <jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
</footer>

<jsp:include page="/WEB-INF/views/layout/footerResources.jsp"/>
</body>
</html>