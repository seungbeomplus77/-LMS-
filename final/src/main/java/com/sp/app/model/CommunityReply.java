package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommunityReply {
    private long replyNum;
    private long communityNum;
    private long parentNum;
    
    private String content; // 내용
    private String reg_date; // 등록날짜
    private String modify_date; // 수정날짜
    private int showCommunity  ; // 글 숨김 여부 (1: 안 숨김, 0: 숨김)

    
    private long studentId; // schoolmember
    private int replyLike; // 1: 좋아요 0: 시러요
}
