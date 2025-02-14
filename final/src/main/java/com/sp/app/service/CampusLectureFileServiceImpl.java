package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.mapper.CampusLectureFileMapper;
import com.sp.app.model.CampusLectureFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampusLectureFileServiceImpl implements CampusLectureFileService {
	private final CampusLectureFileMapper mapper;
	private final StorageService storageService;
		
	@Override
	public void insertCampusLectureFile(CampusLectureFile dto) throws Exception {
		try {
			mapper.insertCampusLectureFile(dto);
		} catch (Exception e) {
			log.info("insertCampusLectureFile : ", e);
		}
		
	}

	@Override
	public List<CampusLectureFile> listCampusLectureFile(long lectureId) throws Exception {
		List<CampusLectureFile> list = null;
		
		try {
			list = mapper.listCampusLectureFile(lectureId);
		} catch (Exception e) {
			log.info("listCampusLectureFile : ", e);
		}
		
		return list;
	}

	@Override
	public CampusLectureFile findCampusLectureFileById(long fileId) throws Exception {
		CampusLectureFile dto = null;
		
		try {
			dto = mapper.findByFileId(fileId);
		} catch (Exception e) {
			log.info("CampusLectureFile : ", e);
		}
		return dto;
	}

	@Override
	public void deleteCampusLectureFile(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteCampusLectureFile(map);
		} catch (Exception e) {
			log.info("deleteCampusLectureFile : ", e);
		}
		
	}
	
	// 파일 업로드
	protected void insertLectureFile(CampusLectureFile dto, String uploadPath) throws Exception {
		for (MultipartFile mf : dto.getSelectFile()) {
			try {
				String saveFilename = Objects.requireNonNull(storageService.uploadFileToServer(mf, uploadPath));
				String originalFilename = mf.getOriginalFilename();

				dto.setOriginalFilename(originalFilename);
				dto.setSaveFilename(saveFilename);

				mapper.insertCampusLectureFile(dto);
			} catch (NullPointerException e) {
				log.info("insertLectureFile : ", e);
			} catch (StorageException e) {
				log.info("insertLectureFile : ", e);
			} catch (Exception e) {
				log.info("insertLectureFile : ", e);
				throw e;
			}
		}
	}	

}
