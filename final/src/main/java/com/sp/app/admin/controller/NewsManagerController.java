package com.sp.app.admin.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.app.common.PaginateUtil;
import com.sp.app.model.SchoolNews;
import com.sp.app.service.SchoolNewsService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/newsManager/*")
public class NewsManagerController {
	private final SchoolNewsService service;
	private final PaginateUtil paginateUtil;
	
	@GetMapping("main")
	public String main(
	        @RequestParam(name = "page", defaultValue = "1") int current_page,
	        @RequestParam(name = "schType", defaultValue = "all") String schType,
	        @RequestParam(name = "kwd", defaultValue = "") String kwd,
	        Model model,
	        HttpServletRequest req) throws Exception {
		
	    try {
	        int size = 10;
	        kwd = URLDecoder.decode(kwd, "utf-8");

	        Map<String, Object> map = new HashMap<>();
	        map.put("schType", schType);
	        map.put("kwd", kwd);
	       
	        int dataCount = service.schoolNewsDataCount(map);
	        int total_page = dataCount == 0 ? 1 : paginateUtil.pageCount(dataCount, size);

	        current_page = Math.max(1, Math.min(current_page, total_page));

	        int offset = (current_page - 1) * size;
	        map.put("offset", offset);
	        map.put("size", size);

	        List<SchoolNews> listSchoolNews = service.listSchoolNews(map);

	        String cp = req.getContextPath();
	        String query = "";
	        String listUrl = cp + "/admin/newsManager/main";
	       
	        if (!kwd.isBlank()) {
	            query = "schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
	            listUrl += "?" + query;
	        }

	        String paging = paginateUtil.paging(current_page, total_page, listUrl);

	        model.addAttribute("listSchoolNews", listSchoolNews);
	        model.addAttribute("dataCount", dataCount);
	        model.addAttribute("size", size);
	        model.addAttribute("page", current_page);
	        model.addAttribute("total_page", total_page);
	        model.addAttribute("paging", paging);
	        model.addAttribute("query", query);
	        model.addAttribute("schType", schType);
	        model.addAttribute("kwd", kwd);

	    } catch (Exception e) {
	        log.info("main : ", e);
	    }

	    return "admin/newsManager/main";
	}
	
	@GetMapping("write")
	public String writeForm(Model model) throws Exception {
		model.addAttribute("mode", "write");
		return "admin/newsManager/write";
	}
	
	@PostMapping("write")
	public String writeSubmit(SchoolNews dto) throws Exception {
		try {
			service.insertSchoolNews(dto);
			
		} catch (Exception e) {
			log.info("writeSubmit : ", e);
		}
		
		return "redirect:/admin/newsManager/main";
	}
	
	@GetMapping("article/{schoolNewsNum}")
	public String article(
			@PathVariable("schoolNewsNum") long schoolNewsNum,
			@RequestParam(name = "page") String page,
			@RequestParam(name = "schType", defaultValue = "all") String schType,
			@RequestParam(name = "kwd", defaultValue = "") String kwd,
			Model model) {
		
		String query = "page=" + page;
		
		try {
			kwd = URLDecoder.decode(kwd, "utf-8");
			if(! kwd.isBlank()) {
				query += "&schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
			}
			// 조회수
			service.updateSchoolNewsHitCount(schoolNewsNum);
			
			// 게시글 가져오기
			SchoolNews dto = Objects.requireNonNull(service.findSchoolNewsById(schoolNewsNum));
			
			// 이전글 / 다음글
			Map<String, Object> map = new HashMap<>();
			map.put("schType", schType);
			map.put("kwd", kwd);
			map.put("schoolNewsNum", schoolNewsNum);
			
			SchoolNews prevDto = service.findPrevSchoolNews(map);
			SchoolNews nextDto = service.findNextSchoolNews(map);
			
			model.addAttribute("dto", dto);
			model.addAttribute("prevDto", prevDto);
			model.addAttribute("nextDto", nextDto);
			model.addAttribute("query", query);
			model.addAttribute("page", page);
			
			return "admin/newsManager/article";
		
		} catch (NullPointerException e) {
		} catch (Exception e) {
			log.info("article : ", e);
		}
		
		return "redirect:/admin/newsManager/main?page=" + page;

	}	

	@GetMapping("update")
	public String updateForm(
			@RequestParam(name = "schoolNewsNum") long schoolNewsNum,
			@RequestParam(name = "page") String page,
			Model model) {

		try {
			SchoolNews dto = Objects.requireNonNull(service.findSchoolNewsById(schoolNewsNum));
			
			model.addAttribute("dto", dto);
			model.addAttribute("page", page);
			model.addAttribute("mode", "update");

			return "admin/newsManager/write";
		
		} catch (NullPointerException e) {
		} catch (Exception e) {
			log.info("updateForm : ", e);
		}

		return "redirect:/admin/newsManager/main?page=" + page;
	}
	
	@PostMapping("update")
	public String updateSubmit(SchoolNews dto,
			@RequestParam(name = "page") String page) {
		
		try {
			service.updateSchoolNews(dto);
		} catch (Exception e) {
			log.info("updateSubmit : ", e);
		}
		
		return "redirect:/admin/newsManager/main?page=" + page;
	}	

	@GetMapping("delete")
	public String delete(@RequestParam(name = "schoolNewsNum") long schoolNewsNum,
			@RequestParam(name = "schType", defaultValue = "all") String schType, 
			@RequestParam(name = "kwd", defaultValue = "") String kwd,
			@RequestParam(name = "page") String page) {
		
		String query = "page=" + page;
		
		try {
			kwd = URLDecoder.decode(kwd, "utf-8");
			
			if(! kwd.isBlank()) {
				query += "&schType=" + schType + "&kwd="
						+ URLEncoder.encode(kwd, "utf-8");				
			}
			
			service.deleteSchoolNews(schoolNewsNum);
			
		} catch (Exception e) {
			log.info("delete : ", e);
		}
		
		return "redirect:/admin/newsManager/main?" + query;
	}	
	
}

