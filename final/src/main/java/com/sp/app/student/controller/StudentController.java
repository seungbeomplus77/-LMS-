package com.sp.app.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {
	
	@GetMapping("/student")
	public String handleHome(Model model) {
		
		return "student/main";
	}
}
