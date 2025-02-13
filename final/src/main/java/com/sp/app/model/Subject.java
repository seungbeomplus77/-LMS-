package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Subject {
	private long subjectId;
	private String subjectName;
	
	private long majorId;
	private String majorName;
	
	private long subId;
	private String subName;
}
