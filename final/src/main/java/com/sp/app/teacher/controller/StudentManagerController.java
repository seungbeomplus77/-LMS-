package com.sp.app.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/teacher/studentManager/*")
public class StudentManagerController {
	@GetMapping("main")
	public String main() {
		return "teacher/studentManager/main";
	}
}
