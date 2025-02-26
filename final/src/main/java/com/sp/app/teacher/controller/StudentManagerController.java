package com.sp.app.teacher.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sp.app.model.SchoolMember;
import com.sp.app.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/teacher/studentManager/*")
public class StudentManagerController {
	private final StudentService studentService;
	
	@GetMapping("main")
	public String main(
			Model model) {
		try {
			
			Map<String, Object> map = new HashMap<>();
			
			List<SchoolMember> list = studentService.listFindMember(map);
		
			model.addAttribute("list", list);
		
		} catch (Exception e) {
			log.info("main : ", e);
		}
		
		return "teacher/studentManager/main";
	}
}
