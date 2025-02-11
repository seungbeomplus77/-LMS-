package com.sp.app.service;

import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sp.app.mail.Mail;
import com.sp.app.mail.MailSender;
import com.sp.app.mapper.SchoolMemberMapper;
import com.sp.app.model.SchoolMember;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolMemberServiceImpl implements SchoolMemberService {
	private final SchoolMemberMapper mapper;
	private final MailSender mailSender;
	private final PasswordEncoder bcryptEncoder;
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public void insertMember(SchoolMember dto) throws Exception {
		try {
			// 패스워드 암호화
			String plainPwd = dto.getUserPwd();
			String encPassword = bcryptEncoder.encode(plainPwd);
			dto.setUserPwd(encPassword);
			
			// 권한 null일때 기본값
			if (dto.getAuthority() == null || dto.getAuthority().isBlank()) {
				dto.setAuthority("USER"); 
			}
			
			// 회원 정보 저장
			mapper.insertSchoolMember(dto);
			
			// 권한 정보 저장
	        mapper.insertAuthority(dto);
		
		} catch (Exception e) {
			log.info("insertMember : ", e);
			throw e;
		}
		
	}

	@Override
	public void insertMemberStatus(SchoolMember dto) throws Exception {
		try {
			mapper.insertMemberStatus(dto);
		} catch (Exception e) {
			log.info("insertMemberStatus : ", e);
		}
	}

	@Override
	public void updateLastLogin(String userId) throws Exception {
		try {
			mapper.updateLastLogin(userId);
		} catch (Exception e) {
			log.info("updateLastLogin : ", e);
			
			throw e;
		}
	}

	@Override
	public void updateMemberEnabled(Map<String, Object> map) throws Exception {
		try {
			mapper.updateSchoolMemberEnabled(map);
		} catch (Exception e) {
			log.info("updateSchoolMemberEnabled : ", e);
			
			throw e;
		}
	}
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public void updateMember(SchoolMember dto) throws Exception {
		try {
			
			boolean bPwdUpdate = ! isPasswordCheck(dto.getUserId(), dto.getUserPwd());
			if( bPwdUpdate ) {
				// 패스워드가 변경된 경우만 member1 테이블의 패스워드 변경
				String encPassword = bcryptEncoder.encode(dto.getUserPwd());
				dto.setUserPwd(encPassword);
				
				mapper.updateSchoolMember(dto);
			}			

		} catch (Exception e) {
			log.info("updateMember : ", e);
			
			throw e;
		}		
	}

	@Override
	public void updatePassword(SchoolMember dto) throws Exception {
		if( isPasswordCheck(dto.getUserId(), dto.getUserPwd()) ) {
			throw new RuntimeException("패스워드가 기존 패스워드와 일치합니다.");
		}

		try {
			String encPassword = bcryptEncoder.encode(dto.getUserPwd());
			dto.setUserPwd(encPassword);
			
			mapper.updateSchoolMember(dto);
		} catch (Exception e) {
			log.info("updatePassword : ", e);
			
			throw e;
		}		
	}

	@Transactional(rollbackFor = {Exception.class})
	@Override
	public void deleteMember(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteAuthority(map);
			
			map.put("enabled", 0);
			mapper.updateSchoolMemberEnabled(map);

			mapper.deleteSchoolMember(map);
			// mapper.deleteMember1(map);
		} catch (Exception e) {
			log.info("deleteMember : ", e);
			
			throw e;
		}
	}

	@Override
	public int checkFailureCount(String userId) {
		int result = 0;
		
		try {
			result = mapper.checkFailureCount(userId);
		} catch (Exception e) {
			log.info("checkFailureCount : ", e);
		}
		
		return result;
	}

	@Override
	public void updateFailureCountReset(String userId) throws SQLException {
		try {
			mapper.updateFailureCountReset(userId);
		} catch (Exception e) {
			log.info("updateFailureCountReset : ", e);
			
			throw e;
		}
	}

	@Override
	public void updateFailureCount(String userId) throws SQLException {
		try {
			mapper.updateFailureCount(userId);
		} catch (Exception e) {
			log.info("updateFailureCount : ", e);
			
			throw e;
		}
	}
	
	@Transactional
	@Override
	public SchoolMember findByUserId(String userId) {
		SchoolMember dto = null;

		try {
			// 객체 = Objects.requireNonNull(객체)
			//  : 파라미터로 입력된 값이 null 이면 NullPointerException을 발생하고,
			//    그렇지 않다면 입력값을 그대로 반환
			dto = Objects.requireNonNull(mapper.findById(userId));
			
		} catch (NullPointerException e) {
		} catch (ArrayIndexOutOfBoundsException e) {
		} catch (Exception e) {
			log.info("findByUserId : ", e);
		}

		return dto;
	}

	@Override
	public Long getMemberIdx(String userId) {
		try {
			Long result = Objects.requireNonNull(mapper.getSchoolId(userId));
			return result;
		} catch (Exception e) {
			log.info("getMemberIdx : ", e);
		}

		return 0L;
	}

	@Override
	public List<SchoolMember> listFindMember(Map<String, Object> map) {
		List<SchoolMember> list = null;
		
		try {
			list = mapper.listFindMember(map);
		} catch (Exception e) {
			log.info("listFindMember : ", e);
		}
		
		return list;
	}

	@Override
	public void generatePwd(SchoolMember dto) throws Exception {
		// 10 자리 임시 패스워드 생성
		
		String lowercase = "abcdefghijklmnopqrstuvwxyz";
		String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String digits = "0123456789";
		String special_characters = "!#@$%^&*()-_=+[]{}?";
		String all_characters = lowercase + digits + uppercase + special_characters;
		
		try {
			// 암호화적으로 안전한 난수 생성(예측 불가 난수 생성)
			SecureRandom random = new SecureRandom();
			
			StringBuilder sb = new StringBuilder();
			
			// 각 문자는 최소 하나 이상 포함
			sb.append(lowercase.charAt(random.nextInt(lowercase.length())));
			sb.append(uppercase.charAt(random.nextInt(uppercase.length())));
			sb.append(digits.charAt(random.nextInt(digits.length())));
			sb.append(special_characters.charAt(random.nextInt(special_characters.length())));
			
			for(int i = sb.length(); i < 10; i++) {
				int index = random.nextInt(all_characters.length());
				
				sb.append(all_characters.charAt(index));
			}
			
			// 문자 섞기
			StringBuilder password = new StringBuilder();
			while (sb.length() > 0) {
				int index = random.nextInt(sb.length());
				password.append(sb.charAt(index));
				sb.deleteCharAt(index);
			}
	        
			String result;
			result = dto.getUserName() +"님의 새로 발급된 임시 패스워드는 <b> "
					+ password.toString() + " </b> 입니다.<br>"
					+ "로그인 후 반드시 패스워드를 변경하시기 바랍니다.";
			
			Mail mail = new Mail();
			mail.setReceiverEmail(dto.getEmail());
			
			mail.setSenderEmail("메일설정이메일@도메인");
			mail.setSenderName("관리자");
			mail.setSubject("임시 패스워드 발급");
			mail.setContent(result);
			
			// 테이블의 패스워드 변경
			String encPassword = bcryptEncoder.encode(password.toString());
			dto.setUserPwd(encPassword);
			mapper.updateSchoolMember(dto);
			
			mapper.updateFailureCountReset(dto.getUserId());
			
			// 메일 전송
			boolean b = mailSender.mailSend(mail);
			
			if( ! b ) {
				throw new Exception("이메일 전송중 오류가 발생했습니다.");
			}

		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public String findByAuthority(String userId) {
		String authority = null;
		
		try {
			authority = mapper.findByAuthority(userId);
		} catch (Exception e) {
			log.info("findByAuthority", e);
		}
		
		return authority;
	}

	@Override
	public void insertRefreshToken(SchoolMember dto) throws Exception {
		try {
			mapper.insertRefreshToken(dto);
		} catch (Exception e) {
			log.info("insertRefreshToken", e);
			throw e;
		}
	}

	@Override
	public void updateRefreshToken(SchoolMember dto) throws Exception {
		try {
			mapper.updateRefreshToken(dto);
		} catch (Exception e) {
			log.info("updateRefreshToken", e);
			throw e;
		}
	}

	@Override
	public SchoolMember findByToken(String userId) {
		SchoolMember dto = null;

		try {
			dto = mapper.findByToken(userId);
		} catch (Exception e) {
			log.info("findByToken", e);
		}

		return dto;
	}
	@Override
	public boolean isPasswordCheck(String userId, String userPwd) {
		try {
			// 패스워드 비교(userPwd를 암호화 해서 dto.getUserPwd()와 비교하면 안된다.)
			//                 userPwd를 암호화하면 가입할때의 암호화 값과 다름. 암호화할때 마다 다른 값
			
			SchoolMember dto = Objects.requireNonNull(findByUserId(userId));
			
			return bcryptEncoder.matches(userPwd, dto.getUserPwd());
		} catch (NullPointerException e) {
		} catch (Exception e) {
		}
		
		return false;
	}
	
	
}
