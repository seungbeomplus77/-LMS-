package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.CampusLecture;

@Mapper
public interface CampusLectureMapper {
	public void insertCampusLecture(CampusLecture dto) throws SQLException;
	public void updateCampusLecture(CampusLecture dto) throws SQLException;
	public void deleteCampusLecture(Map<String, Object> map) throws SQLException;
	public List<CampusLecture> listCampusLecture(Map<String, Object> map);

	public CampusLecture findById(long lectureId);
	public void updateHitCount(long lectureId) throws SQLException;
	public int dataCount(Map<String, Object> map);
}
