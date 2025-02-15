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
	public void insertEnterGuide(EnterGuide dto, String uploadPath) throws Exception {
	    try {
	        boolean realFile = dto.getSelectFile() != null &&
	            dto.getSelectFile().stream().anyMatch(mf -> !mf.isEmpty());
	        if (realFile) {
	            insertEnterGuideFile(dto, uploadPath);
	        } else {
	            mapper.insertEnterGuide(dto);
	        }
	    } catch (Exception e) {
	        log.info("insertEnterGuide : ", e);
	        throw e;
	    }
	}


	@Override
	public void updateEnterGuide(EnterGuide dto, String uploadPath) throws Exception {
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
	                        log.info("updateEnterGuide file upload error: ", e);
	                        throw e;
	                    }
	                }
	            }
	        }
	        // DB 업데이트 실행
	        mapper.updateEnterGuide(dto);
	    } catch (Exception e) {
	        log.info("updateEnterGuide : ", e);
	        throw e;
	    }
	}


	@Override
	public void deleteEnterGuide(long enterGuideNum, String uploadPath) throws Exception {
		try {
			EnterGuide dto = findEnterGuideById(enterGuideNum);
			
			
			deleteUploadFile(uploadPath, dto.getSaveFilename());
			
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
			result = mapper.enterGuideDataCount(map);
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
	
	@Override
	public boolean deleteUploadFile(String uploadPath, String filename) {
		return storageService.deleteFile(uploadPath, filename);
	}
	
	// 파일 업로드
	protected void insertEnterGuideFile(EnterGuide dto, String uploadPath) throws Exception {
	    // dto.getSelectFile()이 null이 아니고, 실제 업로드할 파일이 있는지 체크
	    if (dto.getSelectFile() != null) {
	        for (MultipartFile mf : dto.getSelectFile()) {
	            // 파일이 비어있다면 건너뜀
	            if (mf.isEmpty()) {
	                continue;
	            }
	            try {
	                String saveFilename = Objects.requireNonNull(storageService.uploadFileToServer(mf, uploadPath));
	                String originalFilename = mf.getOriginalFilename();

	                dto.setOriginalFilename(originalFilename);
	                dto.setSaveFilename(saveFilename);
	                
	                mapper.insertEnterGuide(dto);
	            } catch (NullPointerException e) {
	                log.info("insertEnterGuideFile : ", e);
	            } catch (StorageException e) {
	                log.info("insertEnterGuideFile : ", e);
	            } catch (Exception e) {
	                log.info("insertEnterGuideFile : ", e);
	                throw e;
	            }
	        }
	    }
	}

}
