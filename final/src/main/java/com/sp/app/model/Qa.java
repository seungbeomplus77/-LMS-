package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Qa {
	private long qaNum;
	private long teacherId;
	private long studentId;
	
    private String question; // 질문
    private String answer; // 답변
    private String reg_date; // 질문 날짜
    private String answer_date; // 답변 날짜
    private int showQa; // 글 숨김 여부 (1: 안 숨김, 0: 숨김)
}
