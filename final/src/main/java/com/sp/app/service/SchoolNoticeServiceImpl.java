package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.mapper.SchoolNoticeMapper;
import com.sp.app.model.SchoolNotice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolNoticeServiceImpl implements SchoolNoticeService {
	private final SchoolNoticeMapper mapper;
	private final StorageService storageService;
	
	@Override
	public void insertSchoolNotice(SchoolNotice dto) throws Exception {
		try {
			mapper.insertSchoolNotice(dto);
		} catch (Exception e) {
			log.info("insertSchoolNotice : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateSchoolNotice(SchoolNotice dto) throws Exception {
		try {
			mapper.updateSchoolNotice(dto);
		} catch (Exception e) {
			log.info("updateSchoolNotice : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteSchoolNotice(long schoolNoticeNum) throws Exception {
		try {
			mapper.deleteSchoolNotice(schoolNoticeNum);
		} catch (Exception e) {
			log.info("deleteSchoolNotice : ", e);
			throw e;
		}
		
	}

	@Override
	public SchoolNotice findSchoolNoticeById(long schoolNoticeNum) throws Exception {
		SchoolNotice dto = null;
		
		try {
			mapper.findSchoolNoticeById(schoolNoticeNum);
		} catch (Exception e) {
			log.info("findSchoolNoticeById : ", e);
		}
		return dto;
	}

	@Override
	public List<SchoolNotice> listSchoolNotice(Map<String, Object> map) throws Exception {
		List<SchoolNotice> list = null;
		
		try {
			mapper.listSchoolNotice(map);
		} catch (Exception e) {
			log.info("listSchoolNotice : ", e);
			throw e;
		}
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			mapper.dataCount(map);
		} catch (Exception e) {
			log.info("dataCount : ", e);
		}
		return result;
	}

	@Override
	public void updateHitCount(long schoolNoticeNum) throws Exception {
		try {
			mapper.updateHitCount(schoolNoticeNum);
		} catch (Exception e) {
			log.info("schoolNoticeNum : ", e);
		}
		
	}

	@Override
	public SchoolNotice findByPrev(Map<String, Object> map) {
		SchoolNotice dto = null;
		
		try {
			mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findByPrev : ", e);
		}
		return dto;
	}

	@Override
	public SchoolNotice findByNext(Map<String, Object> map) {
		SchoolNotice dto = null;
		
		try {
			mapper.findByNext(map);
		} catch (Exception e) {
			log.info("findByNext : ", e);
		}
		return dto;
	}
	
	// 파일 업로드
	protected void insertSchoolNoticeFile(SchoolNotice dto, String uploadPath) throws Exception {
		for (MultipartFile mf : dto.getSelectFile()) {
			try {
				String saveFilename = Objects.requireNonNull(storageService.uploadFileToServer(mf, uploadPath));
				String originalFilename = mf.getOriginalFilename();

				dto.setOriginalFilename(originalFilename);
				dto.setSaveFilename(saveFilename);

				mapper.insertSchoolNotice(dto);
			} catch (NullPointerException e) {
				log.info("insertSchoolNoticeFile : ", e);
			} catch (StorageException e) {
				log.info("insertSchoolNoticeFile : ", e);
			} catch (Exception e) {
				log.info("insertSchoolNoticeFile : ", e);
				throw e;
			}
		}
	}		

}

