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
          <a class="nav-link" href="${pageContext.request.contextPath}/teacher/studentManager/main">학생관리</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/teacher/campus/sj101">캠퍼스</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/teacher/notice/list">알림마당</a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="${pageContext.request.contextPath}/qa/list">Q&A</a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="${pageContext.request.contextPath}/teacher/mypage/passwordCheck">마이페이지</a>
        </li>
      </ul>
    </div>
    <a class="nav-left" href="${pageContext.request.contextPath}/teacher"><i class="menu--icon bi bi-h-square"></i></a>
    <a class="nav-left" href="${pageContext.request.contextPath}/teacher/mypage/passwordCheck"><i class="menu--icon bi bi-person-square"></i></a>
    <a class="nav-left" href="${pageContext.request.contextPath}/member/logout">><i class="menu--icon bi bi-unlock"></i></a>
  </div>	    
</nav>