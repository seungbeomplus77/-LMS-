package com.sp.app.level.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserLevelController {
    @ModelAttribute
    public void addLevel(Model model) {
    	// 교직원
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean employee = auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"));
        model.addAttribute("employee", employee);
        
        // 학생
        boolean student = auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));
        model.addAttribute("student", student);        
       
        // 교사
        boolean teacher = auth.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"));
        model.addAttribute("teacher", teacher);        
    }
}