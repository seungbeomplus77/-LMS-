package com.sp.app.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sp.app.common.StorageService;
import com.sp.app.exception.StorageException;
import com.sp.app.mapper.CampusLectureFileMapper;
import com.sp.app.model.CampusLecture;
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
	
	@Override
	public void insertCampusLectureFile(CampusLecture dto, String uploadPath) throws Exception {
	    // 첨부파일이 있다면 CampusLectureFile 객체를 만들어 저장합니다.
	    if (dto.getSelectFile() != null && !dto.getSelectFile().isEmpty()) {
	        for (MultipartFile mf : dto.getSelectFile()) {
	            if (mf.isEmpty()) continue;
	            try {
	                // 파일 서버에 업로드 후 저장된 파일명과 원본파일명 가져오기
	                String saveFilename = Objects.requireNonNull(storageService.uploadFileToServer(mf, uploadPath));
	                String originalFilename = mf.getOriginalFilename();

	                // CampusLectureFile 객체 생성 후 값 설정
	                CampusLectureFile fileDto = new CampusLectureFile();
	                // 강의 등록 후 dto의 lectureId가 채워져 있다고 가정합니다.
	                fileDto.setLectureId(dto.getLectureId());
	                fileDto.setSaveFilename(saveFilename);
	                fileDto.setOriginalFilename(originalFilename);

	                // DB에 파일 정보 저장 (fileDto 변수 사용)
	                mapper.insertCampusLectureFile(fileDto);
	            } catch (Exception e) {
	                log.error("Error inserting campus lecture file", e);
	                throw e;
	            }
	        }
	    }
	}



	@Override
	public boolean deleteUploadFile(String uploadPath, String filename) {
		return storageService.deleteFile(uploadPath, filename);
	}	

}
