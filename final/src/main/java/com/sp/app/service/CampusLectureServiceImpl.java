package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.mapper.CampusLectureMapper;
import com.sp.app.model.CampusLecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampusLectureServiceImpl implements CampusLectureService {
	private final CampusLectureMapper mapper;
	private final StorageService storageService;
	
	@Override
	public void insertCampusLecture(CampusLecture dto, String uploadPath) throws Exception {
	    try {
	        boolean realFile = dto.getSelectFile() != null &&
	            dto.getSelectFile().stream().anyMatch(mf -> !mf.isEmpty());
	        if (realFile) {
	            insertCampusFile(dto, uploadPath);
	        } else {
	            mapper.insertCampusLecture(dto);
	        }
	    } catch (Exception e) {
	        log.info("insertCampusLecture : ", e);
	        throw e;
	    }
	}

	@Override
	public void updateCampusLecture(CampusLecture dto, String uploadPath) throws Exception {
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
	                        log.info("updateCampus file upload error: ", e);
	                        throw e;
	                    }
	                }
	            }
	        }
	        // DB 업데이트 실행
	        mapper.updateCampusLecture(dto);
	    } catch (Exception e) {
	        log.info("updateCampusLecture : ", e);
	        throw e;
	    }
	}

	@Override
	public void deleteCampusLecture(long lectureId, String uploadPath, String userId) throws Exception {
		try {
			CampusLecture dto = findCampusLectureById(lectureId);
			
			deleteUploadFile(uploadPath, dto.getSaveFilename());
			
			mapper.deleteCampusLecture(lectureId);
			
		} catch (Exception e) {
			log.info("deleteCampusLecture : ", e);
			throw e;
		}
		
	}

	@Override
	public List<CampusLecture> listCampusLecture(Map<String, Object> map) throws Exception {
		List<CampusLecture> list = null;
		
		try {
			list = mapper.listCampusLecture(map);
		} catch (Exception e) {
			log.info("listCampusLecture : ", e);
			throw e;
		}
		
		return list;
	}

	@Override
	public CampusLecture findCampusLectureById(long lectureId) throws Exception {
		CampusLecture dto = null;
		
		try {
			dto = mapper.findById(lectureId);
		} catch (Exception e) {
			log.info("findCampusLectureById : ", e);
		}
		
		return dto;
	}

	@Override
	public void insertMajor(CampusLecture dto) throws Exception {
		try {
			mapper.insertMajor(dto);
		} catch (Exception e) {
			log.info("insertMajor : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateMajor(CampusLecture dto) throws Exception {
		try {
			mapper.updateMajor(dto);
		} catch (Exception e) {
			log.info("updateMajor : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteMajor(String majorId) throws Exception {
		try {
			mapper.deleteMajor(majorId);
		} catch (Exception e) {
			log.info("deleteMajor : ", e);
		}
		
	}

	@Override
	public List<CampusLecture> listMajor() throws Exception {
		List<CampusLecture> list = null;
		
		try {
			list = mapper.listMajor();
		} catch (Exception e) {
			log.info("listMajor : ", e);
		}
		
		return list;
	}

	@Override
	public CampusLecture findByMajorId(String majorId) throws Exception {
		CampusLecture dto = null;
		
		try {
			dto = mapper.findByMajorId(majorId);
		} catch (Exception e) {
			log.info("findByMajorId : ", e);
		}
		
		return dto;
	}

	@Override
	public void insertSub(CampusLecture dto) throws Exception {
		try {
			mapper.insertSub(dto);
		} catch (Exception e) {
			log.info("insertSub : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateSub(CampusLecture dto) throws Exception {
		try {
			mapper.updateSub(dto);
		} catch (Exception e) {
			log.info("updateSub : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteSub(String subId) throws Exception {
		try {
			mapper.deleteSub(subId);
		} catch (Exception e) {
			log.info("deleteSub : ", e);
		}
		
	}

	@Override
	public List<CampusLecture> listSub() throws Exception {
		List<CampusLecture> list = null;
		
		try {
			list = mapper.listSub();
		} catch (Exception e) {
			log.info("listSub : ", e);
		}
		
		return list;
	}

	@Override
	public CampusLecture findBySubId(String subId) throws Exception {
		CampusLecture dto = null;
		
		try {
			dto = mapper.findBySubId(subId);
		} catch (Exception e) {
			log.info("findByMajorId : ", e);
		}
		
		return dto;
	}

	@Override
	public void insertSubject(CampusLecture dto) throws Exception {
		try {
			mapper.insertSubject(dto);
		} catch (Exception e) {
			log.info("insertSubject : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateSubject(CampusLecture dto) throws Exception {
		try {
			mapper.updateSubject(dto);
		} catch (Exception e) {
			log.info("updateSubject : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteSubject(String subjectId) throws Exception {
		try {
			mapper.deleteSubject(subjectId);
		} catch (Exception e) {
			log.info("deleteSubject : ", e);
		}
		
	}

	@Override
	public List<CampusLecture> listSubject(String majorId) throws Exception {
		List<CampusLecture> list = null;
		
		try {
			list = mapper.listSubject(majorId);
		} catch (Exception e) {
			log.info("listSubject : ", e);
		}
		
		return list;
	}

	@Override
	public CampusLecture findBySubjectId(String subjectId) throws Exception {
		CampusLecture dto = null;
		
		try {
			dto = mapper.findBySubjectId(subjectId);
		} catch (Exception e) {
			log.info("findBySubjectId : ", e);
		}
		
		return dto;
	}

	@Override
	public boolean deleteUploadFile(String uploadPath, String filename) {
		return storageService.deleteFile(uploadPath, filename);
	}

	@Override
	public CampusLecture findByPrev(Map<String, Object> map) {
		CampusLecture dto = null;
		
		try {
			dto = mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findByPrev : ", e);
		}
		return dto;
	}

	@Override
	public CampusLecture findByNext(Map<String, Object> map) {
		CampusLecture dto = null;
		
		try {
			dto = mapper.findByNext(map);
		} catch (Exception e) {
			log.info("findByNext : ", e);
		}
		return dto;
	}

	@Override
	public void updateCampusLectureHitCount(long lectureId) throws Exception {
		try {
			mapper.updateCampusLectureHitCount(lectureId);
		} catch (Exception e) {
			log.info("updateCampusLectureHitCount : ", e);
		}
		
	}

	@Override
	public int campusLectureDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.campusLectureDataCount(map);
		} catch (Exception e) {
			log.info("campusLectureDataCount : ", e);
		}
		return result;
	}
	
	// 파일 업로드
	protected void insertCampusFile(CampusLecture dto, String uploadPath) throws Exception {
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
	                
	                dto.setLectureId(dto.getLectureId());
	                dto.setOriginalFilename(originalFilename);
	                dto.setSaveFilename(saveFilename);
	                
	                mapper.insertCampusLecture(dto);
	            } catch (NullPointerException e) {
	                log.info("insertCampusFile : ", e);
	            } catch (StorageException e) {
	                log.info("insertCampusFile : ", e);
	            } catch (Exception e) {
	                log.info("insertCampusFile : ", e);
	                throw e;
	            }
	        }
	    }
	}
}
