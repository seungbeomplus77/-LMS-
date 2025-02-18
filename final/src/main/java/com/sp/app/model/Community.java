package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Community {
    private long communityNum;
    private String subject; // 제목
    private String content; // 내용
    private Integer hitCount; // 조회수
    private String reg_date; // 등록날짜
    private String modify_date; // 수정날짜
    private int showCommunity; // 글 숨김 여부 (1: 안 숨김, 0: 숨김)
    private int communityLike; // 좋아요
    private String studentId; // schoolmember
    private long categoryNum;
    private String categoryName;
    
	private int replyCount;
	private int boardLikeCount;
}
