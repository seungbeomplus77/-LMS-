package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentAttendance {
	private long hakBanNum; // 학년반 테이블 기본키
	private long studentId;
	private long teacherId;
	
	private String attendanceDate; // 출석 일자
	private String status; // 출석 상태 (예: '출석', '지각', '결석')
}
