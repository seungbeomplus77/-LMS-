package com.sp.app.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student/studentqa/*")
public class StudentQaController {
	@GetMapping("list")
	public String list() {
		return "student/studentqa/list";
	}
	
	@GetMapping("write")
	public String write() {
		return "student/studentqa/write";
	}
}
