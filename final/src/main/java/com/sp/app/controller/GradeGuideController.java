package com.sp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/gradeGuide/*")
public class GradeGuideController {
	@GetMapping("main")
	public String main() {
		return "gradeGuide/main";
	}
	@GetMapping("grade")
	public String grade() {
		return "gradeGuide/grade";
	}
	@GetMapping("subjects")
	public String subjects() {
		return "gradeGuide/subjects";
	}
	@GetMapping("finishEnter")
	public String finishEnter() {
		return "gradeGuide/finishEnter";
	}
}
