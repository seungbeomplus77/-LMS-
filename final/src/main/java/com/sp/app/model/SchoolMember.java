package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SchoolMember {
	private long schoolId;
	private String userId;
	private String userPwd;
	private String userName;
	private String gender;
	private int enabled;
	private String register_date;
	private String modify_date;
	private String last_login;
	private String email;
	private int receiveEmail;
	private String tel;
	private String tel1;
	private String birth;
	private String zip;
	private String addr1;
	private String addr2;
	private String ipAddr;
	
	private long roleId;
	private String authority; // 권한
    
	private String registerId;  // 추가
    private int statusCode;  // 추가
    private String memo;  // 추가
}
