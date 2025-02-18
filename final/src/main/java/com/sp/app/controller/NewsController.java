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

import com.sp.app.common.MyUtil;
import com.sp.app.common.PaginateUtil;
import com.sp.app.common.StorageService;
import com.sp.app.model.PhotoGallery;
import com.sp.app.model.SchoolNews;
import com.sp.app.model.SchoolNotice;
import com.sp.app.service.PhotoGalleryService;
import com.sp.app.service.SchoolNewsService;
import com.sp.app.service.SchoolNoticeService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/news/*")
public class NewsController {
	private final SchoolNewsService service;
	private final StorageService storageService;
	private final PaginateUtil paginateUtil;
	private final MyUtil myUtil;	
	
	private String uploadPath;
	
	@PostConstruct // 생성자 호출 후 한번 실행
	public void init() {
	    // 파일을 저장할 실제 절대 경로를 직접 지정
	    uploadPath = "c:/uploads/file";
	}
	
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
	        String listUrl = cp + "news/main";
	        
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

	    return "news/main";
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
			
			return "news/article";
		
		} catch (NullPointerException e) {
		} catch (Exception e) {
			log.info("article : ", e);
		}
		
		return "redirect:/news/main?page=" + page;

	}	
	
	private final PhotoGalleryService service3;
	
	@GetMapping("photo")
	public String photo(
			@RequestParam(name = "page", defaultValue = "1") int current_page,
			Model model,
			HttpServletRequest req) throws Exception {
		
		try {
			int size = 8;
			int total_page;
			int dataCount;
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			dataCount = service3.dataCount(map);
			total_page = paginateUtil.pageCount(dataCount, size);
			
			current_page = Math.min(current_page, total_page);
			
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;
			
			map.put("offset", offset);
			map.put("size", size);

			List<PhotoGallery> list = service3.listPhotoGallery(map);
			
			String cp = req.getContextPath();
			String query = "";
			String listUrl = cp + "/news/photo";
			String articleUrl = cp + "/photo/article?page=" + current_page;
			String paging = paginateUtil.paging(current_page, total_page, listUrl);
			
			model.addAttribute("list", list);
			model.addAttribute("dataCount", dataCount);
			model.addAttribute("size", size);
			model.addAttribute("total_page", total_page);
			model.addAttribute("articleUrl", articleUrl);
			model.addAttribute("page", current_page);
			model.addAttribute("paging", paging);

		} catch (Exception e) {
			log.info("photo : ", e);
		}
		
		return "news/photo";
	}
    
	private final SchoolNoticeService service2;
	@PostConstruct // 생성자 호출 후 한번 실행
	public void init1() {
	    // 파일을 저장할 실제 절대 경로를 직접 지정
	    uploadPath = "c:/uploads/file";
	}
	
	@GetMapping("notice")
    public String notice(
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

            int dataCount = service2.dataCount(map);
            int total_page = dataCount == 0 ? 1 : paginateUtil.pageCount(dataCount, size);

            current_page = Math.max(1, Math.min(current_page, total_page));

            int offset = (current_page - 1) * size;
            map.put("offset", offset);
            map.put("size", size);

            List<SchoolNotice> listSchoolNotice = service2.listSchoolNotice(map);

            String cp = req.getContextPath();
            String query = "";
            String listUrl = cp + "/news/notice";

            if (categoryId != 0) {
                query = "categoryId=" + categoryId;
            }
            if (!kwd.isBlank()) {
                if (!query.isEmpty()) {
                    query += "&";
                }
                query += "schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
                listUrl += "?" + query;
            }

            String paging = paginateUtil.paging(current_page, total_page, listUrl);

            model.addAttribute("listSchoolNotice", listSchoolNotice);
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
            log.info("notice list : ", e);
        }

        return "news/notice"; // JSP: /WEB-INF/views/news/notice.jsp (리스트 출력)
    }

    @GetMapping("noticeArticle/{schoolNoticeNum}")
    public String noticeArticle(
            @PathVariable("schoolNoticeNum") long schoolNoticeNum,
            @RequestParam(name = "page") String page,
            @RequestParam(name = "schType", defaultValue = "all") String schType,
            @RequestParam(name = "kwd", defaultValue = "") String kwd,
            Model model) {

        String query = "page=" + page;

        try {
            kwd = URLDecoder.decode(kwd, "utf-8");
            if (!kwd.isBlank()) {
                query += "&schType=" + schType + "&kwd=" + URLEncoder.encode(kwd, "utf-8");
            }
            // 조회수 증가
            service2.updateHitCount(schoolNoticeNum);

            // 게시글 가져오기
            SchoolNotice dto = Objects.requireNonNull(service2.findSchoolNoticeById(schoolNoticeNum));

            // 이전글 / 다음글
            Map<String, Object> map = new HashMap<>();
            map.put("schType", schType);
            map.put("kwd", kwd);
            map.put("schoolNoticeNum", schoolNoticeNum);

            SchoolNotice prevDto = service2.findByPrev(map);
            SchoolNotice nextDto = service2.findByNext(map);

            model.addAttribute("dto", dto);
            model.addAttribute("prevDto", prevDto);
            model.addAttribute("nextDto", nextDto);
            model.addAttribute("query", query);
            model.addAttribute("page", page);

            return "news/noticeArticle";

        } catch (Exception e) {
            log.info("notice article : ", e);
        }

        return "redirect:/news/notice?" + query;
    }

    @GetMapping("download")
    public ResponseEntity<?> Download(
            @RequestParam(name = "schoolNoticeNum") long schoolNoticeNum,
            HttpServletRequest req) throws Exception {

        try {
            SchoolNotice dto = Objects.requireNonNull(service2.findSchoolNoticeById(schoolNoticeNum));
            
            return storageService.downloadFile(uploadPath, dto.getSaveFilename(), dto.getOriginalFilename());
        } catch (Exception e) {
            log.info("notice download : ", e);
        }

        String url = req.getContextPath() + "/news/notice/downloadFailed";
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build();
    }

    @GetMapping("notice/downloadFailed")
    public String noticeDownloadFailed() {
        return "error/downloadFailure";
    }
}

