package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.common.StorageService;
import com.sp.app.mapper.PhotoGalleryMapper;
import com.sp.app.model.PhotoGallery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoGalleryServiceImpl implements PhotoGalleryService {
	private final PhotoGalleryMapper mapper;
	private final StorageService storageService;
	
	@Override	
	public void insertPhotoGallery(PhotoGallery dto, String uploadPath) throws Exception {
		try {
			String saveFilename = storageService.uploadFileToServer(dto.getSelectFile(), uploadPath);
			if (saveFilename != null) {
				dto.setImageFilename(saveFilename);

				mapper.insertPhotoGallery(dto);
			}		
		} catch (Exception e) {
			log.info("insertPhotoGallery : ", e);
			
			throw e;
		}
		
	}

	@Override
	public void updatePhotoGallery(PhotoGallery dto, String uploadPath) throws Exception {
		try {
			// 업로드한 파일이 존재한 경우
			if(dto.getSelectFile() != null && ! dto.getSelectFile().isEmpty()) {
				if(! dto.getImageFilename().isBlank()) {
					deleteUploadFile(uploadPath, dto.getImageFilename());
				}
				
				String saveFilename = storageService.uploadFileToServer(dto.getSelectFile(), uploadPath);
				dto.setImageFilename(saveFilename);
			}

			 mapper.updatePhotoGallery(dto);
		} catch (Exception e) {
			log.info("updatePhotoGallery : ", e);
			
			throw e;
		}
	}
	@Override
	public void deletePhotoGallery(long num, String uploadPath, String filename) throws Exception {
		try {
			if (filename != null) {
				deleteUploadFile(uploadPath, filename);
			}

			 mapper.deletePhotoGallery(num);
		} catch (Exception e) {
			log.info("deletePhotoGallery : ", e);
			
			throw e;
		}
	}

	@Override
	public PhotoGallery findPhotoGalleryById(long photoGalleryNum) throws Exception {
		PhotoGallery dto = null;

		try {
			dto = mapper.findPhotoGalleryById(photoGalleryNum);
		} catch (Exception e) {
			log.info("findPhotoGalleryById : ", e);
		}

		return dto;
	}

	@Override
	public List<PhotoGallery> listPhotoGallery(Map<String, Object> map) throws Exception {
		List<PhotoGallery> list = null;

		try {
			list = mapper.listPhotoGallery(map);
		} catch (Exception e) {
			log.info("listPhotoGallery : ", e);
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
	public boolean deleteUploadFile(String uploadPath, String filename) {
		return storageService.deleteFile(uploadPath, filename);
	}

}
