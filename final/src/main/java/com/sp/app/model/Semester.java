package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Semester {
	private long semesterNum;
	private String semesterName; // 학기 유형 2025-1학기
}
