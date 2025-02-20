package com.sp.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.app.common.PaginateUtil;
import com.sp.app.model.Qa;
import com.sp.app.service.QaService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/qa/*")
public class QaController {
	private final QaService service;
	private final PaginateUtil paginateUtil;
	
	@GetMapping("list")
	public String list(
	        @RequestParam(name = "page", defaultValue = "1") int current_page,
	        Model model,
	        Qa dto,
	        HttpServletRequest req) throws Exception {
	    
	    try {
	        int size = 10;
	        int total_page;
	        int dataCount;

	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디
	        
	        // 로그인 사용자의 아이디를 dto에 설정
	        dto.setUserId(userId);
	        
	        Map<String, Object> map = new HashMap<>();
	        // 조회 조건으로 로그인 사용자 아이디를 전달
	        map.put("studentId", userId);
	        
	        dataCount = service.dataCount(map);
	        total_page = paginateUtil.pageCount(dataCount, size);

	        current_page = Math.min(current_page, total_page);
	        int offset = (current_page - 1) * size;
	        if(offset < 0) offset = 0;

	        map.put("offset", offset);
	        map.put("size", size);

	        List<Qa> list = service.listQa(map);

	        String cp = req.getContextPath();
	        String listUrl = cp + "/qa/list";
	        String articleUrl = cp + "/qa/article?page=" + current_page;
	        String paging = paginateUtil.paging(current_page, total_page, listUrl);

	        // 게시글 목록과 추가로 로그인 사용자 정보를 전달
	        model.addAttribute("list", list);
	        model.addAttribute("articleUrl", articleUrl);
	        model.addAttribute("page", current_page);
	        model.addAttribute("dataCount", dataCount);
	        model.addAttribute("size", size);
	        model.addAttribute("total_page", total_page);
	        model.addAttribute("paging", paging);
	        
	        model.addAttribute("loginUser", dto.getUserId());
	        
	    } catch (Exception e) {
	        log.info("list : ", e);
	    }

	    return "qa/list";
	}
	
	@GetMapping(value =  "write")
	public String writeForm(Model model) throws Exception {
		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName();
			
			model.addAttribute("mode", "write");
			model.addAttribute("loginUser", userId);
			
			return "qa/write";
			
		} catch (Exception e) {
			log.info("writeForm : ", e);
		}
		
		return "redirect:/qa/list";
	}

	@PostMapping("write")
	public String writeSubmit(Qa dto) throws Exception {

		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디
	        
	        // 로그인 사용자의 아이디를 dto에 설정
	        dto.setUserId(userId);
	        service.insertQa(dto);
	        
		} catch (Exception e) {
			log.info("writeSubmit : ", e);
		}

		return "redirect:/qa/list";
	}
	
	@GetMapping("update")
	public String updateForm(@RequestParam(name = "qaNum") long qaNum,
			@RequestParam(name = "page") String page,
			Model model) throws Exception {

		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디
	        
			Qa dto = Objects.requireNonNull(service.findQaById(qaNum));

	        if (!userId.equals(dto.getUserId())) {
				return "redirect:/qa/list?page=" + page;
			}

			model.addAttribute("mode", "update");
			model.addAttribute("page", page);
			model.addAttribute("dto", dto);
			model.addAttribute("loginUser", userId);
			
			return "qa/write";
			
		} catch (NullPointerException e) {
			log.info("updateForm : ", e);
		} catch (Exception e) {
			log.info("updateForm : ", e);
		}
		
		return "redirect:/qa/list?page=" + page;
	}

	@PostMapping("update")
	public String updateSubmit(Qa dto,
			@RequestParam(name = "page") String page) throws Exception {
		
		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디

	        if (!userId.equals(dto.getUserId())) {
	            return "redirect:/qa/list?page=" + page;
	        }	        
			
	        service.updateQa(dto);
		
		} catch (Exception e) {
			log.info("updateSubmit : ", e);
		}

		return "redirect:/qa/list?page=" + page;
	}

	@PostMapping("answer")
	public String answerSubmit(Qa dto,
	        @RequestParam(name = "page") String page) throws Exception {
	    try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디
	        
	        // 교사와 교직원 여부 체크
	        boolean isTeacher = auth.getAuthorities().stream()
	                              .anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"));
	        boolean isEmployee = auth.getAuthorities().stream()
	                              .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"));

	        // 질문 작성자와 로그인한 사용자가 일치하거나, 
	        // 교사/교직원인 경우에는 업데이트 허용
	        if (!userId.equals(dto.getUserId()) && !(isTeacher || isEmployee)) {
	            return "redirect:/qa/list?page=" + page;
	        }
	        
	        
	        dto.setTeacherUserId(userId);
	        
	        service.updateAnswer(dto);
	    } catch (Exception e) {
	        log.info("answerSubmit : ", e);
	    }
	    return "redirect:/qa/list?page=" + page;
	}


	@GetMapping("delete")
	public String delete(@RequestParam(name = "qaNum") long qaNum,
			@RequestParam(name = "page") String page,
			@RequestParam(name = "mode") String mode) throws Exception {
		
		String query = "page=" + page;
	
		try {	
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디
				
	        Qa dto = Objects.requireNonNull(service.findQaById(qaNum));
				
	        if (userId.equals(dto.getUserId())) {
	            // 글 작성자 본인이 삭제할 수 있도록 처리
	            switch (mode) {
	                case "question":
	                    service.deleteQa(qaNum);
	                    break;
	                case "answer":
	                    dto.setAnswer("");
	                    service.updateAnswer(dto);
	                    break;
	            }
	        }
			
		} catch (NullPointerException e) {
			log.info("delete : ", e);
		} catch (Exception e) {
			log.info("delete : ", e);
		}

		return "redirect:/qa/list?" + query;
	}

	@GetMapping("article/{qaNum}")
	public String article(
			@PathVariable(name = "qaNum") long qaNum,
			@RequestParam(name = "page") String page,
			Model model) throws Exception {

		String query = "page=" + page;
		try {

			Qa dto = Objects.requireNonNull(service.findQaById(qaNum));
			Map<String, Object> map = new HashMap<>();
			
			map.put("qaNum", qaNum);
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디
			
	        // 교사와 교직원 역할 여부
	        boolean isTeacher = auth.getAuthorities().stream()
	                              .anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"));
	        boolean isEmployee = auth.getAuthorities().stream()
	                              .anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"));
	        
	        // 접근 권한 검증: 글 작성자이거나 교사 또는 교직원인 경우 접근 허용
	        if (!(userId.equals(dto.getUserId()) || isTeacher || isEmployee)) {
	            return "redirect:/qa/list?page=" + page;
	        }

			dto.setQuestion(dto.getQuestion().replaceAll("\n", "<br>"));

			model.addAttribute("dto", dto);
			model.addAttribute("page", page);
			model.addAttribute("query", query);
			model.addAttribute("loginUser", dto.getUserId());

			return "qa/article";
			
		} catch (NullPointerException e) {
			log.info("article : ", e);
		} catch (Exception e) {
			log.info("article : ", e);
		}
		
		return "redirect:/qa/list?" + query;
	}
}