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
	public void insertGradeGuide(GradeGuide dto, String uploadPath) throws Exception {
	    try {
	        boolean realFile = dto.getSelectFile() != null &&
	            dto.getSelectFile().stream().anyMatch(mf -> !mf.isEmpty());
	        if (realFile) {
	        	insertGradeGuideFile(dto, uploadPath);
	        } else {
	            mapper.insertGradeGuide(dto);
	        }
	    } catch (Exception e) {
	        log.info("insertGradeGuide : ", e);
	        throw e;
	    }
	}

	@Override
	public void updateGradeGuide(GradeGuide dto, String uploadPath) throws Exception {
	    try {
	        // dto.getSelectFile()가 null이 아니고, 실제 새 파일이 존재하는지 확인
	        if (dto.getSelectFile() != null && !dto.getSelectFile().isEmpty()) {
	            boolean newFile = false;
	            for (MultipartFile mf : dto.getSelectFile()) {
	                if (!mf.isEmpty()) {
	                	newFile = true;
	                    break;
	                }
	            }
	            if (newFile) {
	                // 기존 파일이 있다면 삭제
	                if (dto.getSaveFilename() != null && !dto.getSaveFilename().isBlank()) {
	                    deleteUploadFile(uploadPath, dto.getSaveFilename());
	                }
	                // 새 파일 업로드 (업데이트에서는 보통 하나의 파일만 업로드)
	                for (MultipartFile mf : dto.getSelectFile()) {
	                    if (mf.isEmpty()) continue;
	                    try {
	                        String saveFilename = Objects.requireNonNull(storageService.uploadFileToServer(mf, uploadPath));
	                        String originalFilename = mf.getOriginalFilename();
	                        dto.setSaveFilename(saveFilename);
	                        dto.setOriginalFilename(originalFilename);
	                        break;  // 하나의 파일만 처리
	                    } catch (Exception e) {
	                        log.info("updateGradeGuide file upload error: ", e);
	                        throw e;
	                    }
	                }
	            }
	        }
	        // DB 업데이트 실행
	        mapper.updateGradeGuide(dto);
	    } catch (Exception e) {
	        log.info("updateGradeGuide : ", e);
	        throw e;
	    }
	}

	@Override
	public void deleteGradeGuide(long gradeGuideNum, String uploadPath) throws Exception {
		try {
			GradeGuide dto = findGradeGuideById(gradeGuideNum);
			
			deleteUploadFile(uploadPath, dto.getSaveFilename());
			
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
			dto = mapper.findGradeGuideById(gradeGuideNum);
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
			result = mapper.dataCount(map);
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
			dto = mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findByPrev : ", e);
		}
		return dto;
	}

	@Override
	public GradeGuide findByNext(Map<String, Object> map) {
		GradeGuide dto = null;
		
		try {
			dto = mapper.findByNext(map);
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

	@Override
	public boolean deleteUploadFile(String uploadPath, String filename) {
		return storageService.deleteFile(uploadPath, filename);
	}		

}
