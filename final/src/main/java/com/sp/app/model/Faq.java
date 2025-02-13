package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Faq {
    private long faqNum;
    private String question; // 질문
    private String answer; // 답변
    private String reg_date;
    private int showFaq; // 글 숨김 여부 (1: 안 숨김, 0: 숨김)
    
    private long schoolId;

    // FAQ 카테고리 관련 필드
    private long categoryId;
    private String categoryName;
    private int showFaqCategory; // 카테고리 숨김 여부 (1: 안 숨김, 0: 숨김)
}
