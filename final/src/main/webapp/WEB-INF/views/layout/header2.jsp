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
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/introduce/main" id="introduceDropdown" role="button" 
          data-bs-toggle="dropdown" aria-expanded="false" onclick="location.href=this.href">학교소개</a>
            <ul class="dropdown-menu" aria-labelledby="introduceDropdown">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/introduce/main">미래고 소개</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/introduce/map">오시는길</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/introduce/status">학교현황</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/introduce/future">교육목표와 비전</a></li>
          </ul>
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/enter/main" id="introduceDropdown" role="button" 
          data-bs-toggle="dropdown" aria-expanded="false" onclick="location.href=this.href">입학안내</a>
            <ul class="dropdown-menu" aria-labelledby="introduceDropdown">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/enter/main">한눈에 보는 입학안내</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/enter/new">신입학 안내</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/enter/old">전편입 안내</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/enter/special">특별전형 안내</a></li>
          </ul>        
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/news/main" id="introduceDropdown" role="button" 
          data-bs-toggle="dropdown" aria-expanded="false" onclick="location.href=this.href">학교소식</a>
            <ul class="dropdown-menu" aria-labelledby="introduceDropdown">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/news/main">미래소식</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/news/photo">포토갤러리</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/news/notice">공지사항</a></li>
           </ul>        	
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/gradeGuide/main" id="introduceDropdown" role="button" 
          data-bs-toggle="dropdown" aria-expanded="false" onclick="location.href=this.href">학점안내</a>
            <ul class="dropdown-menu" aria-labelledby="introduceDropdown">
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/gradeGuide/main">한눈에 보는 학점안내</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/gradeGuide/finishEnter">이수기준/졸업요건</a></li>
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/gradeGuide/subjects">교과목구성/학점배정</a></li>       
            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/gradeGuide/grade">성적산출방법</a></li>       
           </ul>
        </li>
        
        <li class="nav-item">
		  <a class="nav-link" href="${pageContext.request.contextPath}/faq/main">자주묻는질문(FAQ)</a>
        </li>
      </ul>	
    </div>
    <a class="nav-left" href="${pageContext.request.contextPath}/"><i class="menu--icon bi bi-h-square"></i></a>
    <a class="nav-left" href="${pageContext.request.contextPath}/member/logout"><i class="menu--icon bi bi-unlock"></i></a>
  </div>	    
</nav>