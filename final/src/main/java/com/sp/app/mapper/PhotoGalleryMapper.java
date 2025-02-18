package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.PhotoGallery;

@Mapper
public interface PhotoGalleryMapper {
    // 포토갤러리 등록
    public void insertPhotoGallery(PhotoGallery dto) throws SQLException;
    
    // 포토갤러리 수정
    public void updatePhotoGallery(PhotoGallery dto) throws SQLException;
    
    // 포토갤러리 삭제
    public void deletePhotoGallery(long photoGalleryNum) throws SQLException;
    
    // 포토갤러리 단건 조회 (primary key 기준)
    public PhotoGallery findPhotoGalleryById(long photoGalleryNum) throws SQLException;
	public PhotoGallery findByPrev(Map<String, Object> map);
	public PhotoGallery findByNext(Map<String, Object> map);
    
    // 조건에 따른 포토갤러리 목록 조회 (검색, 페이징 등)
    public List<PhotoGallery> listPhotoGallery(Map<String, Object> map) throws SQLException;
    
    // 조건에 따른 데이터 건수 조회 (페이징 등에서 활용)
    public int dataCount(Map<String, Object> map) throws SQLException;
}
