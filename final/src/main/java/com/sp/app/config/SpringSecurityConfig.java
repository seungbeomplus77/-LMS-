package com.sp.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import com.sp.app.security.AjaxSessionTimeoutFilter;
import com.sp.app.security.LoginFailureHandler;
import com.sp.app.security.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 요청 캐시 설정 (예: URL 파라미터 ?continue 제거)
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName(null);

        // 정적 자원 및 공개 URI
        String[] excludeUri = { "/login", "/index.jsp", "/dist/**", "/uploads/photo/**",
                "/favicon.ico", "/WEB-INF/views/**", "/uploads/image/**", "/**" };

        http.cors(Customizer.withDefaults())
            .csrf(AbstractHttpConfigurer::disable)
            .requestCache(request -> request.requestCache(requestCache))
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        // 권한에 따른 URL 접근 제어
        http.authorizeHttpRequests(authorize -> authorize
            // 정적 자원 및 로그인 페이지는 모두 permitAll()
            .requestMatchers(excludeUri).permitAll()
            // TEACHER 전용 페이지: http://localhost:9090/TEACHER/...
            .requestMatchers("/teacher/**").hasRole("TEACHER")
            // STUDENT 전용 페이지: http://localhost:9090/STUDENT/...
            .requestMatchers("/student/**").hasRole("STUDENT")
            // ADMIN 전용 페이지: http://localhost:9090/ADMIN/...
            .requestMatchers("/admin/**").hasRole("EMPLOYEE")
            // 그 외의 요청은 인증된 사용자만 접근
            .anyRequest().authenticated()
        );

        // 로그인 설정: 로그인 페이지와 로그인 처리 모두 루트("/")에서 처리
        http.formLogin(login -> login
            .loginPage("/login")                // GET "/" 에서 로그인 폼 제공
            .loginProcessingUrl("/login")       // POST "/" 로 로그인 인증 요청 처리
            .usernameParameter("userId")
            .passwordParameter("userPwd")
            .successHandler(loginSuccessHandler())  // 성공 시 권한별 리다이렉션
            .failureHandler(loginFailureHandler())
            .permitAll()
        );

        // 로그아웃 설정
        http.logout(logout -> logout
            .logoutUrl("/member/logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/login	")
        );

        // Ajax 세션 타임아웃 필터 및 세션 관리 설정
        http.addFilterAfter(ajaxSessionTimeoutFilter(), ExceptionTranslationFilter.class)
            .sessionManagement(management -> management
                .maximumSessions(1)
                .expiredUrl("/member/expired")
            );

        // 권한 거부(403) 페이지 처리
        http.exceptionHandling(exceptionConfig ->
            exceptionConfig.accessDeniedPage("/member/noAuthorized")
        );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

    /**
     * 커스텀 로그인 성공 핸들러
     * - 로그인 성공 후, Authentication 객체에서 사용자의 권한을 확인하여
     *   TEACHER → /TEACHER, STUDENT → /STUDENT, ADMIN → /ADMIN 으로 리다이렉트하도록 구현합니다.
     *   (내부 로직은 LoginSuccessHandler 클래스에서 별도로 작성)
     */
    @Bean
    LoginSuccessHandler loginSuccessHandler() {
        LoginSuccessHandler handler = new LoginSuccessHandler();
        // 기본 URL은 역할에 따른 분기 로직이 없을 때 fallback 용도로 사용됩니다.
        handler.setDefaultUrl("/");
        return handler;
    }

    @Bean
    LoginFailureHandler loginFailureHandler() {
        LoginFailureHandler handler = new LoginFailureHandler();
        handler.setDefaultFailureUrl("/?login_error");
        return handler;
    }

    @Bean
    AjaxSessionTimeoutFilter ajaxSessionTimeoutFilter() {
        AjaxSessionTimeoutFilter filter = new AjaxSessionTimeoutFilter();
        filter.setAjaxHeader("AJAX");
        return filter;
    }
}
