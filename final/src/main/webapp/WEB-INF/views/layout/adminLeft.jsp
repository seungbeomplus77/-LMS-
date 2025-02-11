<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<nav class="left-nav flex-column">
<button class="menu-toggle-btn">> Menu</button>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin"><i class="menu--icon bi bi-h-square"></i>HOME</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/schedule/main"><i class="menu--icon bi bi-calendar"></i>학습계획</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/accountManager/main"><i class="menu--icon bi bi-person-square"></i>계정관리</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/dataManager/main"><i class="menu--icon bi bi-geo"></i>통계분석</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/reportManager/main"><i class="menu--icon bi bi-gear-fill"></i>신고관리</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/faqManager/main"><i class="menu--icon bi bi-question-square"></i>자주하는질문(FAQ)</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/enterManager/main"><i class="menu--icon bi bi-question-square"></i>입학안내</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/newsManager/main"><i class="menu--icon bi bi-question-square"></i>미래소식</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/photoManager/main"><i class="menu--icon bi bi-question-square"></i>포토갤러리</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/noticeManager/main"><i class="menu--icon bi bi-question-square"></i>공지사항</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/admin/gradeManager/main"><i class="menu--icon bi bi-question-square"></i>학점안내</a>
  <a class="nav-left" href="#"><i class="menu--icon bi bi-unlock"></i>LogOut</a>
</nav>

<script src="${pageContext.request.contextPath}/dist/js/toggle.js"></script>