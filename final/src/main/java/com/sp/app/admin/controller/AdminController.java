package com.sp.app.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sp.app.model.SchoolMember;
import com.sp.app.service.SchoolMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {
	private final SchoolMemberService schoolMemberService;
	
	@GetMapping("/admin")
	public String handleHome(Model model) {
		model.addAttribute("schoolMember", new SchoolMember());
		return "admin/main";
	}
	
	@PostMapping("/admin")
	public String insertForm(SchoolMember dto, Model model) {
		
		try {

			schoolMemberService.insertMember(dto);
			return "redirect:/admin/accountManager/main";
		} catch (Exception e) {
			log.info("insertForm : ", e);
		}
		
		return "admin/main";
	}
}
