package com.sp.app.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/teacher/notice/*")
public class NoticeController {
	@GetMapping("list")
	public String list() {
		return "teacher/notice/list";
	}
}
