package com.sp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/news/*")
public class NewsController {
	@GetMapping("main")
	public String main() {
		return "news/main";
	}
	@GetMapping("photo")
	public String photo() {
		return "news/photo";
	}
	@GetMapping("notice")
	public String notice() {
		return "news/notice";
	}
}
