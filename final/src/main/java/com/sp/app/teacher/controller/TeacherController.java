package com.sp.app.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TeacherController {
	
	@GetMapping("/teacher")
	public String handleHome(Model model) {
		
		return "teacher/main";
	}
}
