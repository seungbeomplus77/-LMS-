<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<nav class="left-nav flex-column campus-left">
<button class="menu-toggle-btn">> Menu</button>
  <ul class="text-center">
  <li class="nav-left mt-3"><i class="menu--icon bi bi-calendar"></i>국어(필수)</li>
  <li class="nav-left"><i class="menu--icon bi bi-calendar"></i>영어(필수)</li>
  <li class="nav-left"><i class="menu--icon bi bi-calendar"></i>수학(필수)</li>
  <li class="nav-left"><i class="menu--icon bi bi-calendar"></i>과학(필수)</li>
  <li class="nav-left"><i class="menu--icon bi bi-calendar"></i>사회(필수)</li>
  </ul>
</nav>
<script src="${pageContext.request.contextPath}/dist/js/toggle.js"></script>