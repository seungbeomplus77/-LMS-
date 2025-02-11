package com.sp.app.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("studentMyPageController")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student/mypage/*")
public class MyPageController {
    @GetMapping
    public String showPasswordCheckForm() {
        return "student/mypage/passwordCheck";
    }
	
    @PostMapping("passwordCheck")
	public String passwordCheck() {
		return "redirect:list";
	}
	
	@GetMapping("list")
	public String list() {
		return "student/mypage/list";
	}
	
    @GetMapping("/edit/main")
	public String editMain() {
		return "student/mypage/edit/main";
	}
    
    @GetMapping("/attendance/main")
    public String attendance() {
    	return "student/mypage/attendance/main";
    }
   
    @GetMapping("/grade/main")
    public String grade() {
    	return "student/mypage/grade/main";
    }
    
    @GetMapping("/schedule/main")
    public String scheduleMain() {
    	return "student/mypage/schedule/main";
    }
}
