package com.sp.app.security;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.sp.app.model.SchoolMember;
import com.sp.app.model.SessionInfo;
import com.sp.app.service.SchoolMemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
	private String defaultUrl;

	@Autowired
	private SchoolMemberService schoolMemberService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			// 로그인 날짜 변경
			schoolMemberService.updateLastLogin(authentication.getName());
		} catch (Exception e) {
		}

		// 로그인 정보 저장
		SchoolMember member = schoolMemberService.findByUserId(authentication.getName());

		SessionInfo info = SessionInfo.builder()
				.schoolId(member.getSchoolId())
				.userId(member.getUserId())
				.userName(member.getUserName())
				.userLevel(NumericRoleGranted.getUserLevel(member.getAuthority()))
				.build();
		
		session.setAttribute("member", info);
		
		// 패스워드 변경이 90일이 지난 경우 패스워드 변경 폼으로 이동
		try {
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			 LocalDateTime currentDateTime = LocalDateTime.now();
			 LocalDateTime targetDateTime = LocalDateTime.parse(member.getModify_date(), formatter);
			 long daysBetween = ChronoUnit.DAYS.between(targetDateTime, currentDateTime);
			 if (daysBetween >= 90) {
				String targetUrl = "/member/updatePwd";
				redirectStratgy.sendRedirect(request, response, targetUrl);
				return;
			}
		} catch (Exception e) {
		}

		// redirect 설정
		resultRedirectStrategy(request, response, authentication);
	}

    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

	// 우선 SavedRequest가 있으면 그대로 리다이렉트 (보통 접근 제한 페이지로 이동했다가 로그인 성공한 경우)
	SavedRequest savedRequest = requestCache.getRequest(request, response);
	if (savedRequest != null) {
	String targetUrl = savedRequest.getRedirectUrl();
	redirectStratgy.sendRedirect(request, response, targetUrl);
	return;
	}

	// 인증된 사용자의 권한 목록을 가져와서 역할에 따라 리다이렉트 URL 결정
	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	String targetUrl = defaultUrl; // 기본 fallback URL
	
	for (GrantedAuthority authority : authorities) {
	String role = authority.getAuthority();
	
	// Spring Security에서는 roles를 설정할 때 자동으로 "ROLE_" 접두사가 붙습니다.
	if ("ROLE_TEACHER".equals(role)) {
	targetUrl = request.getContextPath() + "/teacher";
	break;
	} else if ("ROLE_STUDENT".equals(role)) {
	targetUrl = request.getContextPath() + "/student";
	break;
	} else if ("ROLE_EMPLOYEE".equals(role)) {
	targetUrl = request.getContextPath() + "/admin";
	break;
	}
	}
	redirectStratgy.sendRedirect(request, response, targetUrl);
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
}
