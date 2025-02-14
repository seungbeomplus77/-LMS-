package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.mapper.EnterGuideMapper;
import com.sp.app.model.EnterGuide;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class EnterGuideServiceImpl implements EnterGuideService {
	private final EnterGuideMapper mapper;
	private final StorageService storageService;
	
	@Override
	public void insertEnterGuide(EnterGuide dto) throws Exception {
		try {
			mapper.insertEnterGuide(dto);
		} catch (Exception e) {
			log.info("insertEnterGuide : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateEnterGuide(EnterGuide dto) throws Exception {
		try {
			mapper.updateEnterGuide(dto);
		} catch (Exception e) {
			log.info("updateEnterGuide : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteEnterGuide(long enterGuideNum) throws Exception {
		try {
			mapper.deleteEnterGuide(enterGuideNum);
		} catch (Exception e) {
			log.info("deleteEnterGuide : ", e);
			throw e;
		}
		
	}

	@Override
	public EnterGuide findEnterGuideById(long enterGuideNum) throws Exception {
		EnterGuide dto = null;
		
		try {
			dto = mapper.findEnterGuideById(enterGuideNum);
		} catch (Exception e) {
			log.info("findEnterGuideById : ", e);
		}
		return dto;
	}

	@Override
	public List<EnterGuide> listEnterGuide(Map<String, Object> map) throws Exception {
		List<EnterGuide> list = null;
		
		try {
			list = mapper.listEnterGuide(map);
		} catch (Exception e) {
			log.info("listEnterGuide : ", e);
			throw e;
		}
		return list;
	}

	@Override
	public int enterGuideDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("enterGuideDataCount : ", e);
		}
		return result;
	}

	@Override
	public void updateEnterGuideHitCount(long enterGuideNum) throws Exception {
		try {
			mapper.updateHitCount(enterGuideNum);
		} catch (Exception e) {
			log.info("updateEnterGuideHitCount : ", e);
		}
		
	}

	@Override
	public EnterGuide findPrevEnterGuide(Map<String, Object> map) throws Exception {
		EnterGuide dto = null;
		
		try {
			dto = mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findPrevEnterGuide : ", e);
		}
		return dto;
	}

	@Override
	public EnterGuide findNextEnterGuide(Map<String, Object> map) throws Exception {
		EnterGuide dto = null;
		
		try {
			dto = mapper.findByNext(map);
		} catch (Exception e) {
			log.info("findNextEnterGuide : ", e);
		}
		return dto;
	}
	
	// 파일 업로드
	protected void insertEnterGuideFile(EnterGuide dto, String uploadPath) throws Exception {
		for (MultipartFile mf : dto.getSelectFile()) {
			try {
				String saveFilename = Objects.requireNonNull(storageService.uploadFileToServer(mf, uploadPath));
				String originalFilename = mf.getOriginalFilename();

				dto.setOriginalFilename(originalFilename);
				dto.setSaveFilename(saveFilename);

				mapper.insertEnterGuide(dto);
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
