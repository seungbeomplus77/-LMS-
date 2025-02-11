package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.SchoolMember;

@Mapper
public interface SchoolMemberMapper {
	public SchoolMember loginSchoolMember(String userId);
	
	public void updateLastLogin(String userId) throws SQLException;
	
	public void insertSchoolMember(SchoolMember dto) throws SQLException;
	public void updateSchoolMember(SchoolMember dto) throws SQLException;
	public void deleteSchoolMember(Map<String, Object> map) throws SQLException;
	
	public SchoolMember findById(String userId);
	public void insertMemberStatus(SchoolMember dto) throws SQLException;
	
	public void updateSchoolMemberEnabled(Map<String, Object> map) throws SQLException;
	
	public int checkFailureCount(String userId);
	public void updateFailureCountReset(String userId) throws SQLException;
	public void updateFailureCount(String userId) throws SQLException;
	
	public Long getSchoolId(String userId);
	
	public List<SchoolMember> listFindMember(Map<String, Object> map);
	
	public void insertAuthority(SchoolMember dto) throws SQLException;
	public void deleteAuthority(Map<String, Object> map) throws SQLException;
	public String findByAuthority(String userId);
	
	public void insertRefreshToken(SchoolMember dto) throws SQLException;
	public void updateRefreshToken(SchoolMember dto) throws SQLException;
	public void deleteRefreshToken(String userId) throws SQLException;
	public SchoolMember findByToken(String userId);	
}
