package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.CampusLecture;
import com.sp.app.model.CampusLectureFile;

@Mapper
public interface CampusLectureFileMapper {
	public void insertCampusLectureFile(CampusLectureFile dto) throws SQLException;
	public List<CampusLectureFile> listCampusLectureFile(long lectureId);
	public CampusLectureFile findByFileId(long fileId);
	public void deleteCampusLectureFile(Map<String, Object> map) throws SQLException;	
}
