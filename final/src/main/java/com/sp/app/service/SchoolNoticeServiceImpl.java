package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.mapper.SchoolNoticeMapper;
import com.sp.app.model.EnterGuide;
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
	public void insertSchoolNotice(SchoolNotice dto, String uploadPath) throws Exception {
	    try {
	        boolean realFile = dto.getSelectFile() != null &&
	            dto.getSelectFile().stream().anyMatch(mf -> !mf.isEmpty());
	        if (realFile) {
	            insertSchoolNoticeFile(dto, uploadPath);
	        } else {
	            mapper.insertSchoolNotice(dto);
	        }
	    } catch (Exception e) {
	        log.info("insertSchoolNotice : ", e);
	        throw e;
	    }
	}

	@Override
	public void updateSchoolNotice(SchoolNotice dto, String uploadPath) throws Exception {
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
	                        log.info("updateSchoolNotice file upload error: ", e);
	                        throw e;
	                    }
	                }
	            }
	        }
	        // DB 업데이트 실행
	        mapper.updateSchoolNotice(dto);
	    } catch (Exception e) {
	        log.info("updateSchoolNotice : ", e);
	        throw e;
	    }
	}

	@Override
	public void deleteSchoolNotice(long schoolNoticeNum, String uploadPath) throws Exception {
		try {
			SchoolNotice dto = findSchoolNoticeById(schoolNoticeNum);
			
			deleteUploadFile(uploadPath, dto.getSaveFilename());
			
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
			dto = mapper.findSchoolNoticeById(schoolNoticeNum);
		} catch (Exception e) {
			log.info("findSchoolNoticeById : ", e);
		}
		return dto;
	}

	@Override
	public List<SchoolNotice> listSchoolNotice(Map<String, Object> map) throws Exception {
		List<SchoolNotice> list = null;
		
		try {
			list = mapper.listSchoolNotice(map);
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
			result = mapper.dataCount(map);
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
			dto = mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findByPrev : ", e);
		}
		return dto;
	}

	@Override
	public SchoolNotice findByNext(Map<String, Object> map) {
		SchoolNotice dto = null;
		
		try {
			dto = mapper.findByNext(map);
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


	@Override
	public boolean deleteUploadFile(String uploadPath, String filename) {
		return storageService.deleteFile(uploadPath, filename);
	}		
	
}

