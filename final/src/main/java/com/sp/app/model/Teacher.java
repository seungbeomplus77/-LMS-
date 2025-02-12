package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Teacher {
	private long schoolId;
	private String hireDate; // 입직일
	private String retireDate; // 퇴직일
	private String position; // 직급/직책
}
