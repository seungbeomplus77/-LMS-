<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<nav class="left-nav flex-column mt-5">
<button class="menu-toggle-btn">> Menu</button>
  <a class="nav-left" href="${pageContext.request.contextPath}/student"><i class="menu--icon bi bi-h-square"></i>HOME</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/student/mypage/schedule/main"><i class="menu--icon bi bi-calendar"></i>일정/시간표</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/student/mypage/attendance/main"><i class="menu--icon bi bi-person-square"></i>출석정보</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/student/mypage/grade/main"><i class="menu--icon bi bi-person-square"></i>성적정보</a>
  <a class="nav-left" href="${pageContext.request.contextPath}/student/mypage/edit/main"><i class="menu--icon bi bi-gear-fill"></i>나의정보수정</a>
  <a class="nav-left" href="#"><i class="menu--icon bi bi-unlock"></i>LogOut</a>
</nav>

<script src="${pageContext.request.contextPath}/dist/js/toggle.js"></script>