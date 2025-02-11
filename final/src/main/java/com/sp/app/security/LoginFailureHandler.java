package com.sp.app.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.sp.app.model.SchoolMember;
import com.sp.app.service.SchoolMemberService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
	@Autowired
	private SchoolMemberService schoolMemberService;

	private String defaultFailureUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		String userId = request.getParameter("userId");

		String errorMsg = "아이디 또는 패스워드가 일치하지 않습니다.";
		try {
			if (exception instanceof BadCredentialsException) {
				// 패스워드가 일치하지 않을 때 던지는 예외
				
				int cnt = schoolMemberService.checkFailureCount(userId);
				if(cnt <= 4) {
					schoolMemberService.updateFailureCount(userId);
				}
				
				if(cnt == 4) {
					// 계정 비활성화
					Map<String, Object> map = new HashMap<>();
					map.put("enabled", 0);
					map.put("userId", userId);
					schoolMemberService.updateMemberEnabled(map);
					
					// 비활성화 상태 저장
					SchoolMember dto = new SchoolMember();
					dto.setSchoolId(schoolMemberService.getMemberIdx(userId));
					dto.setRegisterId(userId);
					dto.setStatusCode(1);
					dto.setMemo("패스워드 5회 이상 실패");
					schoolMemberService.insertMemberStatus(dto);
				}

				errorMsg = "아이디 또는 패스워드가 일치하지 않습니다.";
			} else if (exception instanceof InternalAuthenticationServiceException) {
				
				// 존재하지 않는 아이디 일때 던지는 예외
				errorMsg = "아이디 또는 패스워드가 일치하지 않습니다.";
			} else if (exception instanceof DisabledException) {
				
				// 인증 거부 - 계정 비활성화(enabled=0)
				errorMsg = "계정이 비활성화되었습니다. 관리자에게 문의하세요.";
			}
		} catch (Exception e) {
			log.info("LoginFailureHandler : " + errorMsg, e);
		}

		/*
		 - spring security의 예외
           BadCredentialException : 비밀번호가 일치하지 않을 때 던지는 예외
           InternalAuthenticationServiceException : 존재하지 않는 아이디일 때 던지는 예외
           AuthenticationCredentialNotFoundException : 인증 요구가 거부됐을 때 던지는 예외
           LockedException : 인증 거부 - 잠긴 계정
           DisabledException : 인증 거부 - 계정 비활성화(enabled=0)
           AccountExpiredException : 인증 거부 - 계정 유효기간 만료
           CredentialExpiredException : 인증 거부 - 비밀번호 유효기간 만료
		*/

		/*
		// 포워딩하면 아이디 등이 틀린 다음 로그인 성공하면 /error 로 이동
		request.setAttribute("message", errorMsg);
		request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
		*/
		
		response.sendRedirect(defaultFailureUrl);
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
}
