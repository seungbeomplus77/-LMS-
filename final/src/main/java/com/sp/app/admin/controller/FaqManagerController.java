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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.app.common.PaginateUtil;
import com.sp.app.common.StorageService;
import com.sp.app.model.Faq;
import com.sp.app.service.FaqService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/faqManager/*")
public class FaqManagerController {
	private final FaqService service;
	private final PaginateUtil paginateUtil;	
	
	@GetMapping("main")
	public String main(
	        @RequestParam(name = "page", defaultValue = "1") int current_page,
	        @RequestParam(name = "categoryId", defaultValue = "1") int categoryId,
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
	        map.put("categoryId", categoryId);
	        
	       int dataCount = service.faqDataCount(map);
	       int total_page = dataCount == 0 ? 1 : paginateUtil.pageCount(dataCount, size);

	       current_page = Math.max(1, Math.min(current_page, total_page));

	       
	       int offset = (current_page - 1) * size;
	       map.put("offset", offset);
	       map.put("size", size);
	        
	       List<Faq> listFaq = service.listFaq(map);
		
	       String cp = req.getContextPath();
	       String query = "";
	       String listUrl = cp + "/admin/faqManager/main";
	        // URL에 categoryId 포함시키기
	        
	        if(categoryId != 0) {
	            query += "categoryId=" + categoryId;
	        }		
		
	        if (!kwd.isBlank()) {
	            query = "schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
	            listUrl += "?" + query;
	        }

	        String paging = paginateUtil.paging(current_page, total_page, listUrl);

	        model.addAttribute("listFaq", listFaq);
	        model.addAttribute("dataCount", dataCount);
	        model.addAttribute("size", size);
	        model.addAttribute("page", current_page);
	        model.addAttribute("total_page", total_page);
	        model.addAttribute("paging", paging);
	        model.addAttribute("query", query);
	        model.addAttribute("schType", schType);
	        model.addAttribute("kwd", kwd);
	        model.addAttribute("categoryId", categoryId);

	    } catch (Exception e) {
	        log.info("main : ", e);
	    }

	    return "admin/faqManager/main";
	}
	
	
	@GetMapping("write")
	public String writeForm(Model model) throws Exception {
		model.addAttribute("mode", "write");
		return "admin/faqManager/write";
	}
	
	@PostMapping("write")
	public String writeSubmit(Faq dto) throws Exception {
		try {
			service.insertFaq(dto);
			
		} catch (Exception e) {
			log.info("writeSubmit : ", e);
		}
		
		return "redirect:/admin/faqManager/main";
	}
	
	@GetMapping("update")
	public String updateForm(
			@RequestParam(name = "faqNum") long faqNum,
			Model model) {

		try {
			Faq dto = Objects.requireNonNull(service.findFaqById(faqNum));
			
			model.addAttribute("dto", dto);
			model.addAttribute("mode", "update");

			return "admin/faqManager/write";
		
		} catch (NullPointerException e) {
		} catch (Exception e) {
			log.info("updateForm : ", e);
		}

		return "redirect:/admin/faqManager/main";
	}
	
	@PostMapping("update")
	public String updateSubmit(Faq dto) {
		
		try {
			service.updateFaq(dto);
		} catch (Exception e) {
			log.info("updateSubmit : ", e);
		}
		
		return "redirect:/admin/faqManager/main";
	}	
	
	@GetMapping("delete")
	public String delete(@RequestParam(name = "faqNum") long faqNum,
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
			
			service.deleteFaq(faqNum);
			
		} catch (Exception e) {
			log.info("delete : ", e);
		}
		
		return "redirect:/admin/faqManager/main";
	}
	
	
}

