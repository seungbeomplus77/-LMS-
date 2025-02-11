package com.sp.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/introduce/*")
public class IntroduceController {
	
	@GetMapping("main")
	public String main() {
		return "introduce/main";
	}
	
	@GetMapping("map")
	public String map() {
		return "introduce/map";
	}
	
	@GetMapping("status")
	public String status() {
		return "introduce/status";
	}
	
	@GetMapping("future")
	public String future() {
		return "introduce/future";
	}
}
