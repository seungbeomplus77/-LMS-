package com.sp.app.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.app.common.PaginateUtil;
import com.sp.app.common.StorageService;
import com.sp.app.model.PhotoGallery;
import com.sp.app.service.PhotoGalleryService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/photoManager/*")
public class PhotoManagerController {
	private final PhotoGalleryService service;
	private final PaginateUtil paginateUtil;
	private final StorageService storageService;
	
	private String uploadPath;
	
	@PostConstruct
	public void init() {
		uploadPath = this.storageService.getRealPath("/uploads/photo");		
	}
	
	@GetMapping("main")
	public String main(
			@RequestParam(name = "page", defaultValue = "1") int current_page,
			Model model,
			HttpServletRequest req) throws Exception {
		
		try {
			int size = 8;
			int total_page;
			int dataCount;
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			dataCount = service.dataCount(map);
			total_page = paginateUtil.pageCount(dataCount, size);
			
			current_page = Math.min(current_page, total_page);
			
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			map.put("offset", offset);
			map.put("size", size);

			List<PhotoGallery> list = service.listPhotoGallery(map);
			
			String cp = req.getContextPath();
			String query = "";
			String listUrl = cp + "/admin/photoManager/main";
			String articleUrl = cp + "/admin/photoManager/article?page=" + current_page;
			String paging = paginateUtil.paging(current_page, total_page, listUrl);
			
			model.addAttribute("list", list);
			model.addAttribute("dataCount", dataCount);
			model.addAttribute("size", size);
			model.addAttribute("total_page", total_page);
			model.addAttribute("articleUrl", articleUrl);
			model.addAttribute("page", current_page);
			model.addAttribute("paging", paging);

		} catch (Exception e) {
			log.info("main : ", e);
		}
		
		return "admin/photoManager/main";
	}
	@GetMapping("write")
	public String writeForm(Model model) throws Exception {

		model.addAttribute("mode", "write");

		return "admin/photoManager/write";
	}

	@PostMapping("write")
	public String writeSubmit(PhotoGallery dto) throws Exception {
		try {
			
			service.insertPhotoGallery(dto, uploadPath);
			
		} catch (Exception e) {
			log.info("writeSubmit : ", e);
		}

		return "redirect:/admin/photoManager/main";
	}
	
	
}

