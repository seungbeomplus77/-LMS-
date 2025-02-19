package com.sp.app.student.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.app.common.MyUtil;
import com.sp.app.common.PaginateUtil;
import com.sp.app.model.Community;
import com.sp.app.model.SessionInfo;
import com.sp.app.service.CommunityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
			
			if (categoryNum != 0) {
			    query += "&categoryNum=" + categoryNum;
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
	
	@GetMapping("article/{communityNum}")
	public String article(
			@PathVariable("communityNum") long communityNum,
			@RequestParam(name = "page") String page,
			@RequestParam(name = "schType", defaultValue = "all") String schType,
			@RequestParam(name = "kwd", defaultValue = "") String kwd,
			Model model,
			HttpSession session) throws Exception {
		
		String query = "page=" + page;
		
		try {
			kwd = URLDecoder.decode(kwd, "utf-8");
			if(! kwd.isBlank()) {
				query += "&schType=" + schType + "&kwd="
						+ URLEncoder.encode(kwd, "utf-8");
			}		
		
			service.updateCommunityHitCount(communityNum);
			
			Community dto = Objects.requireNonNull(service.findCommunityById(communityNum));
			
			Map<String, Object> map = new HashMap<>();
			map.put("schType", schType);
			map.put("kwd", kwd);
			map.put("communityNum", communityNum);
			
			Community prevDto = service.findPrevCommunity(map);
			Community nextDto = service.findNextCommunity(map);
			
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디

	        boolean isAuthor = userId.equals(dto.getStudentId());
	        boolean userLiked = service.userCommunityLiked(map);
	        
			model.addAttribute("dto", dto);
			model.addAttribute("prevDto", prevDto);
			model.addAttribute("nextDto", nextDto);

			model.addAttribute("query", query);
			model.addAttribute("page", page);
			
			model.addAttribute("userLiked", userLiked);
			model.addAttribute("isAuthor", isAuthor);
			model.addAttribute("studentId", userId);
			
			return "student/community/article";
			
		} catch (NullPointerException e) {
		} catch (Exception e) {
			log.info("article : ", e);
		}

		return "redirect:/student/community/list?" + query;
	}
	
	@GetMapping("update")
	public String updateForm(
			@RequestParam(name = "communityNum") long communityNum,
			@RequestParam(name = "page") String page,
			Model model)  throws Exception {
		
		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디

	        Community dto = Objects.requireNonNull(service.findCommunityById(communityNum));
	        
	        if (!userId.equals(dto.getStudentId())) {
	            return "redirect:/student/community/list?page=" + page;
	        }
	        
	        model.addAttribute("dto", dto);
	        model.addAttribute("page", page);
	        model.addAttribute("mode", "update");
	        
	        return "student/community/write";
		
		} catch (NullPointerException e) {
		} catch (Exception e) {
			log.info("updateForm : ", e);
		}
		
		return "redirect:/student/community/list?page=" + page;
	}
	
	@PostMapping("update")
	public String updateSubmit(Community dto,
			@RequestParam(name = "page") String page) {
		
		try {
			service.updateCommunity(dto);
		} catch (Exception e) {
			log.info("updateSubmit : ", e);
		}
		
		return "redirect:/student/community/list?page=" + page;
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam(name = "communityNum") long communityNum,
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
				
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디			
			
	        service.deleteCommunity(communityNum, userId);
	        
		} catch (Exception e) {
			log.info("deleteBoard : ", e);
		}
		
		return "redirect:/student/community/list?" + query;
	}
	
	// 게시글 좋아요 추가/삭제 : AJAX-JSON
	@ResponseBody
	@PostMapping("insertBoardLike")
	public Map<String, ?> insertBoardLike(
			@RequestParam(name = "communityNum") long communityNum,
			@RequestParam(name = "userLiked") boolean userLiked) {
		
		Map<String, Object> model = new HashMap<>();
		
		String state = "true";
		int boardLikeCount = 0;
		
		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디			
			
			Map<String, Object> map = new HashMap<>();
			map.put("communityNum", communityNum);
			map.put("studentId", userId);
			
			if(userLiked) {
				service.deleteCommunityLike(map); // 좋아요 해제
			} else {
				service.insertCommunityLike(map); // 좋아요 추가
			}
			
			boardLikeCount = service.CommunityLikeCount(communityNum);
			
		} catch (DuplicateKeyException e) {
			state = "liked";
		} catch (Exception e) {
			state = "false";
		}
		
		model.put("state", state);
		model.put("boardLikeCount", boardLikeCount);
		
		return model;
	}	
}