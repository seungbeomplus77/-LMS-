package com.sp.app.student.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.app.common.MyUtil;
import com.sp.app.common.PaginateUtil;
import com.sp.app.model.Community;
import com.sp.app.service.CommunityService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student/community/*")
public class CommunityController {
	private final CommunityService service;
	private final PaginateUtil paginateUtil;
	private final MyUtil myUtil;
	
	@GetMapping("list")
	public String list(
			@RequestParam(name = "page", defaultValue = "1") int current_page,
			@RequestParam(name = "categoryNum", defaultValue = "1") int categoryNum,
			@RequestParam(name = "schType", defaultValue = "all") String schType,
			@RequestParam(name = "kwd", defaultValue = "") String kwd,
			Model model,
			HttpServletRequest req) throws Exception {
		
		try {
			int size = 10;
			int total_page = 0;
			int dataCount = 0;
			
			kwd = URLDecoder.decode(kwd, "utf-8");
			
			Map<String, Object> map = new HashMap<>();
			map.put("schType", schType);
			map.put("kwd", kwd);
			map.put("categoryNum", categoryNum);
			
			dataCount = service.communityDataCount(map);
			total_page = paginateUtil.pageCount(dataCount, size);
			
			current_page = Math.min(current_page, total_page);
			
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			map.put("offset", offset);
			map.put("size", size);
			
			List<Community> list = service.listCommunity(map);
			
			String cp = req.getContextPath();
			String query = "page=" + current_page;
			String listUrl = cp + "/student/community/list";
			String articleUrl = cp + "/student/community/article";
			
	        if(categoryNum != 0) {
	            query += "categoryId=" + categoryNum;
	        }
			
			if(! kwd.isBlank()) {
				String qs = "schType=" + schType + "&kwd=" +
						URLEncoder.encode(kwd, "utf-8");
				
				listUrl += "?" + qs;
				query += "&" + qs;
			}
			
			String paging = paginateUtil.paging(current_page, 
					total_page, listUrl);
			
			model.addAttribute("list", list);
			
			model.addAttribute("dataCount", dataCount);
			model.addAttribute("size", size);
			model.addAttribute("page", current_page);
			model.addAttribute("total_page", total_page);
			model.addAttribute("articleUrl", articleUrl);
			model.addAttribute("paging", paging);
			
			model.addAttribute("schType", schType);
			model.addAttribute("kwd", kwd);
			model.addAttribute("query", query);
			model.addAttribute("categoryNum", categoryNum);
			
		} catch (Exception e) {
			log.info("list : ", e);
		}
		
		return "student/community/list";
	}
	
	@GetMapping("write")
	public String writeForm(Model model) throws Exception {
		model.addAttribute("mode", "write");
		return "student/community/write";
	}
	
	@PostMapping("write")
	public String writeSubmit(Community dto) throws Exception {
	    try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디

	        dto.setStudentId(userId); // dto에 아이디 설정

	        service.insertCommunity(dto);
	    } catch (Exception e) {
	        log.info("writeSubmit : ", e);
	    }
	    return "redirect:/student/community/list";
	}
}
