package com.sp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.app.service.SchoolMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private final SchoolMemberService service;
    
    // 홈 화면이 로그인 화면 역할을 겸함: GET과 POST 모두 처리
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(
            @RequestParam(name = "login_error", required = false) String login_error,
            Model model) {
        
        // 로그인 에러가 발생했을 경우 메시지 추가
        if (login_error != null) {
            model.addAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
        }
        
        // 홈 화면 뷰 (로그인 페이지) 반환
        return "main/home";
    }
}
