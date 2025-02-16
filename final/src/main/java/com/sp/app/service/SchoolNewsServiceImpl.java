package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.mapper.SchoolNewsMapper;
import com.sp.app.model.SchoolNews;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchoolNewsServiceImpl implements SchoolNewsService {
	private final SchoolNewsMapper mapper;
	private final StorageService storageService;
	
	@Override
	public void insertSchoolNews(SchoolNews dto) throws Exception {
		try {
			mapper.insertSchoolNews(dto);
		} catch (Exception e) {
			log.info("insertSchoolNews : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateSchoolNews(SchoolNews dto) throws Exception {
		try {
			mapper.updateSchoolNews(dto);
		} catch (Exception e) {
			log.info("updateSchoolNews : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteSchoolNews(long schoolNewsNum) throws Exception {
		try {
			mapper.deleteSchoolNews(schoolNewsNum);
		} catch (Exception e) {
			log.info("deleteSchoolNews : ", e);
			throw e;
		}
	}

	@Override
	public SchoolNews findSchoolNewsById(long schoolNewsNum) throws Exception {
		SchoolNews dto = null;
		
		try {
			dto = mapper.findSchoolNewsById(schoolNewsNum);
		} catch (Exception e) {
			log.info("findSchoolNewsById : ", e);
		}
		return dto;
	}

	@Override
	public List<SchoolNews> listSchoolNews(Map<String, Object> map) throws Exception {
		List<SchoolNews> list = null;
		
		try {
			list = mapper.listSchoolNews(map);
		} catch (Exception e) {
			log.info("listSchoolNews : ", e);
			throw e;
		}
		return list;
	}

	@Override
	public int schoolNewsDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.schoolNewsDataCount(map);
		} catch (Exception e) {
			log.info("schoolNewsDataCount : ", e);
		}
		return result;
	}

	@Override
	public void updateSchoolNewsHitCount(long schoolNewsNum) throws Exception {
		try {
			mapper.updateHitCount(schoolNewsNum);
		} catch (Exception e) {
			log.info("updateSchoolNewsHitCount : ", e);
		}
		
	}

	@Override
	public SchoolNews findPrevSchoolNews(Map<String, Object> map) throws Exception {
		SchoolNews dto = null;
		
		try {
			dto = mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findPrevSchoolNews : ", e);
		}
		return dto;
	}

	@Override
	public SchoolNews findNextSchoolNews(Map<String, Object> map) throws Exception {
		SchoolNews dto = null;
		
		try {
			dto = mapper.findByNext(map);
		} catch (Exception e) {
			log.info("findNextSchoolNews : ", e);
		}
		return dto;
	}

	// 파일 업로드
	protected void insertSchoolNewsFile(SchoolNews dto, String uploadPath) throws Exception {
		for (MultipartFile mf : dto.getSelectFile()) {
			try {
				String saveFilename = Objects.requireNonNull(storageService.uploadFileToServer(mf, uploadPath));
				String originalFilename = mf.getOriginalFilename();

				dto.setOriginalFilename(originalFilename);
				dto.setSaveFilename(saveFilename);

				mapper.insertSchoolNews(dto);
			} catch (NullPointerException e) {
				log.info("insertSchoolNewsFile : ", e);
			} catch (StorageException e) {
				log.info("insertSchoolNewsFile : ", e);
			} catch (Exception e) {
				log.info("insertSchoolNewsFile : ", e);
				throw e;
			}
		}
	}		

}

