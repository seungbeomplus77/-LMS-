package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.mapper.GradeGuideMapper;
import com.sp.app.model.GradeGuide;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class GradeGuideServiceImpl implements GradeGuideService {
	private final GradeGuideMapper mapper;
	private final StorageService storageService;
	
	@Override
	public void insertGradeGuide(GradeGuide dto) throws Exception {
		try {
			mapper.insertGradeGuide(dto);
		} catch (Exception e) {
			log.info("insertGradeGuide : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateGradeGuide(GradeGuide dto) throws Exception {
		try {
			mapper.updateGradeGuide(dto);
		} catch (Exception e) {
			log.info("updateGradeGuide : ", e);
			throw e;
		}
	}

	@Override
	public void deleteGradeGuide(long gradeGuideNum) throws Exception {
		try {
			mapper.deleteGradeGuide(gradeGuideNum);
		} catch (Exception e) {
			log.info("deleteGradeGuide : ", e);
			throw e;
		}
	}

	@Override
	public GradeGuide findGradeGuideById(long gradeGuideNum) throws Exception {
		GradeGuide dto = null;
		
		try {
			mapper.findGradeGuideById(gradeGuideNum);
		} catch (Exception e) {
			log.info("findGradeGuideById : ", e);
		}
		return dto;
	}

	@Override
	public List<GradeGuide> listGradeGuide(Map<String, Object> map) throws Exception {
		List<GradeGuide> list = null;
		
		try {
			list = mapper.listGradeGuide(map);
		} catch (Exception e) {
			log.info("listGradeGuide : ", e);
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
	public void updateHitCount(long gradeGuideNum) throws Exception {
		try {
			mapper.updateHitCount(gradeGuideNum);
		} catch (Exception e) {
			log.info("updateHitCount : ", e);
		}
	}

	@Override
	public GradeGuide findByPrev(Map<String, Object> map) {
		GradeGuide dto = null;
		
		try {
			mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findByPrev : ", e);
		}
		return dto;
	}

	@Override
	public GradeGuide findByNext(Map<String, Object> map) {
		GradeGuide dto = null;
		
		try {
			mapper.findByNext(map);
		} catch (Exception e) {
			log.info("findByNext : ", e);
		}
		return dto;
	}

	// 파일 업로드
	protected void insertGradeGuideFile(GradeGuide dto, String uploadPath) throws Exception {
		for (MultipartFile mf : dto.getSelectFile()) {
			try {
				String saveFilename = Objects.requireNonNull(storageService.uploadFileToServer(mf, uploadPath));
				String originalFilename = mf.getOriginalFilename();

				dto.setOriginalFilename(originalFilename);
				dto.setSaveFilename(saveFilename);

				mapper.insertGradeGuide(dto);
			} catch (NullPointerException e) {
				log.info("insertGradeGuideFile : ", e);
			} catch (StorageException e) {
				log.info("insertGradeGuideFile : ", e);
			} catch (Exception e) {
				log.info("insertGradeGuideFile : ", e);
				throw e;
			}
		}
	}		

}
