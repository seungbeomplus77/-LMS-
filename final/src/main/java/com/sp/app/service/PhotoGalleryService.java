package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.PhotoGallery;

public interface PhotoGalleryService {
    // 포토갤러리 등록
    public void insertPhotoGallery(PhotoGallery dto, String uploadPath) throws Exception;
    
    // 포토갤러리 수정
    public void updatePhotoGallery(PhotoGallery dto, String uploadPath) throws Exception;
    
    // 포토갤러리 삭제 (삭제 조건이 여러 개라면 Map을 사용)
    public void deletePhotoGallery(long photoGalleryNum, String uploadPath, String filename) throws Exception;
    
    // 포토갤러리 단건 조회 (primary key 기준)
    public PhotoGallery findPhotoGalleryById(long photoGalleryNum) throws Exception;
    
    // 조건에 따른 포토갤러리 목록 조회 (검색, 페이징 등)
    public List<PhotoGallery> listPhotoGallery(Map<String, Object> map) throws Exception;
    
    // 조건에 따른 데이터 건수 조회 (페이징 등에서 활용)
    public int dataCount(Map<String, Object> map) throws Exception;
    
    public boolean deleteUploadFile(String uploadPath, String filename);
}
