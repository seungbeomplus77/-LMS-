package com.sp.app.teacher.controller;

import java.io.File;
import java.net.URI;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.model.CampusLecture;
import com.sp.app.model.CampusLectureFile;
import com.sp.app.service.CampusLectureFileService;
import com.sp.app.service.CampusLectureService;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/teacher/campus/*")
public class CampusController {
	private final CampusLectureService campusLectureService;
	private final PaginateUtil paginateUtil;
	private final CampusLectureFileService campusLectureFileService;
	private final StorageService storageService;
	private final MyUtil myUtil;
	
	public String uploadPath;
	
	@PostConstruct
	public void init() {
		uploadPath = "c:/uploads/file";
	}
	
	
	@GetMapping("{subjectId}")
	public String main(
	        @PathVariable("subjectId") String subjectId,
	        Model model) throws Exception {

	    try {
	        CampusLecture lectureCategory = campusLectureService.findBySubjectId(subjectId);
	        
	        
	        List<CampusLecture> list = campusLectureService.listSubject(subjectId);
	        
	        String majorId = null;
	        String subjectName = null;
	        
	        if(list.size() > 0) {
	        	majorId = list.get(0).getMajorId();
	        	subjectName = list.get(0).getSubjectName();
	        }
	        
	        model.addAttribute("subjectId", subjectId);
	        model.addAttribute("lectureCategory", lectureCategory);
	        model.addAttribute("list", list);
	        model.addAttribute("majorId", majorId);
	        model.addAttribute("subjectName", subjectName);
	        
	    } catch (Exception e) {
	        log.info("main : ", e);
	    }
	    
	    return "teacher/campus/main";
	}

	@GetMapping(value = "{subjectId}/list")
	public String list(
			@PathVariable("subjectId") String subjectId,
			@RequestParam(name = "pageNo", defaultValue = "1") int current_page,
			@RequestParam(name = "schType", defaultValue = "all") String schType,
			@RequestParam(name = "kwd", defaultValue = "") String kwd,
			Model model,
			HttpServletResponse resp) throws Exception {
		
		try {
			
			int size = 10; // 한 화면에 보여주는 게시물 수
			int total_page = 0;
			int dataCount = 0;

			kwd = URLDecoder.decode(kwd, "utf-8");

			// 전체 페이지 수
			Map<String, Object> map = new HashMap<String, Object>();	
			map.put("subjectId", subjectId);
			map.put("schType", schType);
			map.put("kwd", kwd);

			dataCount = campusLectureService.campusLectureDataCount(map);
			if (dataCount != 0) {
				total_page = paginateUtil.pageCount(dataCount, size);
			}

			// 다른 사람이 자료를 삭제하여 전체 페이지수가 변화 된 경우
			current_page = Math.min(current_page, total_page);

			// 리스트에 출력할 데이터를 가져오기
			int offset = (current_page - 1) * size;
			if(offset < 0) offset = 0;

			map.put("offset", offset);
			map.put("size", size);

			// 글 리스트
			List<CampusLecture> list = campusLectureService.listCampusLecture(map);
			
	        String paging = paginateUtil.pagingMethod(current_page, total_page, "listPage");
			
			model.addAttribute("list", list);
			model.addAttribute("pageNo", current_page);
			model.addAttribute("dataCount", dataCount);
			model.addAttribute("size", size);
			model.addAttribute("total_page", total_page);
			model.addAttribute("paging", paging);

			model.addAttribute("schType", schType);
			model.addAttribute("kwd", kwd);
			
		} catch (Exception e) {
			log.info("list : ", e);
			
			resp.sendError(406);
			throw e;
		}

		return "teacher/campus/list";
	}
	
	// AJAX - Text
	@GetMapping("{subjectId}/write")
	public String writeForm(
			@PathVariable("subjectId") String subjectId,
			Model model, 
			HttpServletResponse resp) throws Exception {

		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디
			
			model.addAttribute("mode", "write");
			model.addAttribute("pageNo", "1");
			model.addAttribute("loginUser", userId);

			return "teacher/campus/write";
		
		} catch (Exception e) {
			log.info("writeForm : ", e);
			
			resp.sendError(406);
			throw e;
		}
		
	}
	
    // AJAX - JSON : 강의 및 파일 등록 처리
	@ResponseBody
	@PostMapping("{subjectId}/write")
	public Map<String, ?> writeSubmit(
	        @PathVariable("subjectId") String subjectId,
	        CampusLecture dto) throws Exception {

	    String state = "false";
	    
	    try {
	        // 로그인한 사용자 아이디 가져오기
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName();
	        dto.setTeacherId(userId);
	        dto.setSubjectId(subjectId);
	        
	        // 강의 본문 등록 (강의 등록 시 lectureId가 생성되어야 합니다)
	        campusLectureService.insertCampusLecture(dto, uploadPath);
	        
	        // 첨부파일이 있다면 파일 정보를 저장
	        if (dto.getSelectFile() != null && !dto.getSelectFile().isEmpty()) {
	            campusLectureFileService.insertCampusLectureFile(dto, uploadPath);
	        }
	        
	        state = "true";
	    } catch (Exception e) {
	        log.error("writeSubmit : ", e);
	    }

	    Map<String, Object> model = new HashMap<>();
	    model.put("state", state);
	    return model;
	}


	// AJAX - Text
	@GetMapping("{subjectId}/article")
	public String article(
			@PathVariable("subjectId") String subjectId,
			@RequestParam(name = "lectureId") long lectureId,
			@RequestParam(name = "pageNo") String pageNo,
			@RequestParam(name = "schType", defaultValue = "all") String schType,
			@RequestParam(name = "kwd", defaultValue = "") String kwd,
			Model model,
			HttpServletResponse resp) throws Exception { // Model이 마지막 인지인 경우 410 이 발생

		try {
			kwd = URLDecoder.decode(kwd, "utf-8");

			campusLectureService.updateCampusLectureHitCount(lectureId);

			CampusLecture dto = Objects.requireNonNull(campusLectureService.findCampusLectureById(lectureId));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("subjectId", subjectId);
			map.put("schType", schType);
			map.put("kwd", kwd);
			map.put("lectureId", lectureId);

			CampusLecture prevDto = campusLectureService.findByPrev(map);
			CampusLecture nextDto = campusLectureService.findByNext(map);

			// 파일
			List<CampusLectureFile> listFile = campusLectureFileService.listCampusLectureFile(lectureId);

			model.addAttribute("dto", dto);
			model.addAttribute("prevDto", prevDto);
			model.addAttribute("nextDto", nextDto);
			model.addAttribute("listFile", listFile);
			model.addAttribute("pageNo", pageNo);

			return "teacher/campus/article";
			
		} catch (NullPointerException e) {
			log.info("article : ", e);
			
			resp.sendError(410);
			throw e;
		} catch (Exception e) {
			log.info("article : ", e);
			
			resp.sendError(406);
			throw e;
		}
	}
	// AJAX - Text
	@GetMapping("{subjectId}/update")
	public String updateForm(
			@PathVariable("subjectId") String subjectId,
			@RequestParam(name = "lectureId") long lectureId,
			@RequestParam(name = "pageNo") String pageNo,
			Model model,
			HttpServletResponse resp) throws Exception {
		
		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디

			CampusLecture dto = Objects.requireNonNull(campusLectureService.findCampusLectureById(lectureId));
			
			if (!userId.equals(dto.getUserId())) { 
				resp.sendError(403);
				return null;
			}

			List<CampusLectureFile> listFile = campusLectureFileService.listCampusLectureFile(lectureId);

			model.addAttribute("mode", "update");
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("dto", dto);
			model.addAttribute("listFile", listFile);

			return "teacher/campus/write";	
			
		} catch (NullPointerException e) {
			resp.sendError(410);
			throw e;
		} catch (Exception e) {
			resp.sendError(406);
			throw e;
		}
}

	// AJAX - JSON
	@ResponseBody
	@PostMapping("{subjectId}/update")
	public Map<String, ?> updateSubmit(
			@PathVariable("subjectId") String subjectId,
			CampusLecture dto) throws Exception {
		
		Map<String, Object> model = new HashMap<>();

		String state = "false";
		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디
			
	        dto.setUserId(userId);
	        campusLectureService.updateCampusLecture(dto, uploadPath);
	        
	        campusLectureFileService.insertCampusLectureFile(dto, uploadPath);
	        
	        state = "true";
		
		} catch (Exception e) {
			log.info("updateSubmit : ", e);
		}

		model.put("state", state);
		return model;
	}

	// AJAX - JSON
	@ResponseBody
	@PostMapping("{subjectId}/delete")
	public Map<String, ?> delete(
			@PathVariable("subjectId") String subjectId,
			@RequestParam(name = "letureId") long letureId) throws Exception {
		
		Map<String, Object> model = new HashMap<>();

		String state = "false";
		try {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String userId = auth.getName(); // 로그인한 사용자 아이디

			
			campusLectureService.deleteCampusLecture(letureId, uploadPath, userId);
				
			state = "true";
		
		} catch (Exception e) {
			log.info("delete : ", e);
		}

		model.put("state", state);
		return model;
	}
	
	// AJAX - JSON
	@ResponseBody
	@PostMapping("{subjectId}/deleteFile")
	public Map<String, ?> deleteFile(
			@PathVariable("subjectId") String subjectId,
			@RequestParam(name = "fileId") long fileId) throws Exception {
		Map<String, Object> model = new HashMap<>();

		String state = "false";
		try {
			CampusLectureFile dto = Objects.requireNonNull(campusLectureFileService.findCampusLectureFileById(fileId));
			
			campusLectureService.deleteUploadFile(uploadPath, dto.getSaveFilename());

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("field", "fileId");
			map.put("num", fileId);
			
			campusLectureFileService.deleteCampusLectureFile(map);
			
			state = "true";
		} catch (NullPointerException e) {
			log.info("deleteFile : ", e);
		} catch (Exception e) {
			log.info("deleteFile : ", e);
		}
		
		model.put("state", state);
		return model;
	}
	
	@GetMapping("attachment/download")
	public ResponseEntity<?> download(
			@RequestParam(name = "fileId") long fileId,
			HttpServletRequest req) throws Exception {
		
		try {
			CampusLectureFile dto = Objects.requireNonNull(campusLectureFileService.findCampusLectureFileById(fileId));

			return storageService.downloadFile(uploadPath, dto.getSaveFilename(), dto.getOriginalFilename());
			
		} catch (NullPointerException | StorageException e) {
			log.info("download : ", e);
		} catch (Exception e) {
			log.info("download : ", e);
		}
		
		String redirectUrl = req.getContextPath() + "/teacher/campus/downloadFailed";
		return ResponseEntity
                .status(HttpStatus.FOUND)  // 302 상태 코드(리다이렉트)
                .location(URI.create(redirectUrl))  // Location 헤더에 리다이렉트할 URL 설정
                .build();
	}
	
	@GetMapping("attachment/zipdownload")
	public ResponseEntity<?> zipdownload(@RequestParam(name = "fileId") long fileId,
			HttpServletRequest req) throws Exception {
		try {
			List<CampusLectureFile> listFile = campusLectureFileService.listCampusLectureFile(fileId);
			if (listFile.size() > 0) {
				String[] sources = new String[listFile.size()];
				String[] originals = new String[listFile.size()];
				String fileName = listFile.get(0).getOriginalFilename();
				String zipFilename = fileName.substring(0, fileName.lastIndexOf(".")) + "_외.zip";

				for (int idx = 0; idx < listFile.size(); idx++) {
					sources[idx] = uploadPath + File.separator + listFile.get(idx).getSaveFilename();
					originals[idx] = File.separator + listFile.get(idx).getOriginalFilename();
				}

				return storageService.downloadZipFile(sources, originals, zipFilename);
			}
			
		} catch (Exception e) {
			log.info("zipdownload : ", e);
		}
		
		String redirectUrl = req.getContextPath() + "/teacher/campus/downloadFailed";
		return ResponseEntity
                .status(HttpStatus.FOUND)  // 302 상태 코드(리다이렉트)
                .location(URI.create(redirectUrl))  // Location 헤더에 리다이렉트할 URL 설정
                .build();
	}	
	
	
	@GetMapping("downloadFailed")
	public String downloadFailed() {
		return "error/downloadFailure";
	}
}