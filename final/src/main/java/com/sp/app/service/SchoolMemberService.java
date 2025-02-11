package com.sp.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sp.app.model.SchoolMember;

public interface SchoolMemberService {
	public void insertMember(SchoolMember dto) throws Exception;
	public void insertMemberStatus(SchoolMember dto) throws Exception;
	public void updateLastLogin(String userId) throws Exception;
	public void updateMemberEnabled(Map<String, Object> map) throws Exception;
	public void updateMember(SchoolMember dto) throws Exception;
	public void updatePassword(SchoolMember dto) throws Exception;
	public void deleteMember(Map<String, Object> map) throws Exception;
	
	public int checkFailureCount(String userId);
	public void updateFailureCountReset(String userId) throws SQLException;
	public void updateFailureCount(String userId) throws SQLException;
	
	public SchoolMember findByUserId(String userId);
	public Long getMemberIdx(String userId);
	public List<SchoolMember> listFindMember(Map<String, Object> map);
	
	public void generatePwd(SchoolMember dto) throws Exception;
	
	public String findByAuthority(String userId);
	
	public void insertRefreshToken(SchoolMember dto) throws Exception;
	public void updateRefreshToken(SchoolMember dto) throws Exception;
	public SchoolMember findByToken(String userId);
	
	public boolean isPasswordCheck(String userId, String userPwd);		
}
