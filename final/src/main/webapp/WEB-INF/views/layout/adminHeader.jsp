<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<nav class="navbar navbar-expand-lg navbar-border">
  <div class="container">
    <a class="navbar-brand d-flex align-items-center text-decoration-none" href="${pageContext.request.contextPath}/">
      <img src="/dist/images/logo.png" alt="Logo" width="100" height="auto" class="me-1">
    </a>
    <!-- 화면 작아졌을 때 헤더메뉴 네비게이션-->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>	
    
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav fw-bold">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/schedule/main">학습계획</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/accountManager/main">계정관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/admin/dataManager/main">통계분석</a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="${pageContext.request.contextPath}/admin/reportManager/main">신고관리</a>
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}" id="introduceDropdown" role="button" 
          data-bs-toggle="dropdown" aria-expanded="false" onclick="location.href=this.href">게시글관리</a>
            <ul class="dropdown-menu" aria-labelledby="aritcleDropdown">
        <li class="">
          <a class="dropdown-item " href="${pageContext.request.contextPath}/admin/faqManager/main">자주묻는질문(FAQ)</a>
        </li>
        <li class="">
          <a class="dropdown-item " href="${pageContext.request.contextPath}/admin/enterManager/main">입학안내</a>
        </li>
        <li class="">
          <a class="dropdown-item " href="${pageContext.request.contextPath}/admin/newsManager/main">미래소식</a>
        </li>
        <li class="">
          <a class="dropdown-item " href="${pageContext.request.contextPath}/admin/photoManager/main">포토갤러리</a>
        </li>
        <li class="">
          <a class="dropdown-item " href="${pageContext.request.contextPath}/admin/noticeManager/main">공지사항</a>
        </li>
        <li class="">
          <a class="dropdown-item " href="${pageContext.request.contextPath}/admin/gradeManager/main">학점안내</a>
        </li>
          </ul>        
        </li>		
     
      </ul>
    </div>
    <a class="nav-left" href="${pageContext.request.contextPath}/admin"><i class="menu--icon bi bi-h-square"></i></a>
    <a class="nav-left" href="${pageContext.request.contextPath}/member/logout"><i class="menu--icon bi bi-unlock"></i></a>
  </div>	    
</nav>