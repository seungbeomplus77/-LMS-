package com.sp.app.controller;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.app.common.PaginateUtil;
import com.sp.app.common.StorageService;
import com.sp.app.model.EnterGuide;
import com.sp.app.model.GradeGuide;
import com.sp.app.service.GradeGuideService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/gradeGuide/*")
public class GradeGuideController {
	private final GradeGuideService service;
	private final StorageService storageService;
	private final PaginateUtil paginateUtil;	
	private String uploadPath;	
	
	@PostConstruct // 생성자 호출 후 한번 실행
	public void init() {
	    // 파일을 저장할 실제 절대 경로를 직접 지정
	    uploadPath = "c:/uploads/file";
	}	
	
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
	       
	        int dataCount = service.dataCount(map);
	        int total_page = dataCount == 0 ? 1 : paginateUtil.pageCount(dataCount, size);

	        current_page = Math.max(1, Math.min(current_page, total_page));

	        int offset = (current_page - 1) * size;
	        map.put("offset", offset);
	        map.put("size", size);

	        List<GradeGuide> listGradeGuide = service.listGradeGuide(map);

	        String cp = req.getContextPath();
	        String query = "";
	        String listUrl = cp + "gradeGuide/main";
	        // URL에 categoryId 포함시키기
	        
	        if(categoryId != 0) {
	            query += "categoryId=" + categoryId;
	        }
	       
	        if (!kwd.isBlank()) {
	            query = "schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
	            listUrl += "?" + query;
	        }

	        String paging = paginateUtil.paging(current_page, total_page, listUrl);

	        model.addAttribute("listGradeGuide", listGradeGuide);
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

	    return "gradeGuide/main";
	}
	
	@GetMapping("article/{gradeGuideNum}")
	public String article(
			@PathVariable("gradeGuideNum") long gradeGuideNum,
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
			service.updateHitCount(gradeGuideNum);
			
			// 게시글 가져오기
			GradeGuide dto = Objects.requireNonNull(service.findGradeGuideById(gradeGuideNum));
			
			// 이전글 / 다음글
			Map<String, Object> map = new HashMap<>();
			map.put("schType", schType);
			map.put("kwd", kwd);
			map.put("gradeGuideNum", gradeGuideNum);
			
			GradeGuide prevDto = service.findByPrev(map);
			GradeGuide nextDto = service.findByNext(map);
			
			model.addAttribute("dto", dto);
			model.addAttribute("prevDto", prevDto);
			model.addAttribute("nextDto", nextDto);
			model.addAttribute("query", query);
			model.addAttribute("page", page);
			
			return "gradeGuide/article";
		
		} catch (NullPointerException e) {
		} catch (Exception e) {
			log.info("article : ", e);
		}
		
		return "redirect:/gradeGuide/main?page=" + page;

	}
	
	@GetMapping("download")
	public ResponseEntity<?> download(
	        @RequestParam(name = "gradeGuideNum") long gradeGuideNum,
	        HttpServletRequest req) throws Exception {

	    try {
	        GradeGuide dto = Objects.requireNonNull(service.findGradeGuideById(gradeGuideNum));
	        // uploadPath가 이미 절대 경로이므로 그대로 사용합니다.
	        return storageService.downloadFile(uploadPath, dto.getSaveFilename(), dto.getOriginalFilename());
	    } catch (NullPointerException e) {
	    } catch (Exception e) {
	        log.info("download : ", e);
	    }
	    
	    String url = req.getContextPath() + "admin/downloadFailed";
	    
	    return ResponseEntity
	            .status(HttpStatus.FOUND)
	            .location(URI.create(url))
	            .build();
	}
	
	@GetMapping("downloadFailed")
	public String downloadFailed() {
		return "error/downloadFailure";
	}
	
	@GetMapping("grade")
	public String grade() {
		return "gradeGuide/grade";
	}
	@GetMapping("subjects")
	public String subjects() {
		return "gradeGuide/subjects";
	}
	@GetMapping("finishEnter")
	public String finishEnter() {
		return "gradeGuide/finishEnter";
	}
}
