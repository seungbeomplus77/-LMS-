package com.sp.app.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("teacherMyPageController")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/teacher/mypage/*")
public class MyPageController {
    @GetMapping
    public String showPasswordCheckForm() {
        return "teacher/mypage/passwordCheck";
    }
	
    @PostMapping("passwordCheck")
	public String passwordCheck() {
		return "redirect:list";
	}
	
    @GetMapping("list")
    public String list() {
    	return "teacher/mypage/list";
    }
    
    @GetMapping("/edit/main")
	public String editMain() {
		return "teacher/mypage/edit/main";
	}
    
    @GetMapping("/myHakBan/main")
    public String myHakBanMain() {
    	return "teacher/mypage/myHakBan/main";
    }
    
    @GetMapping("/schedule/main")
    public String scheduleMain() {
    	return "teacher/mypage/schedule/main";
    }
}
