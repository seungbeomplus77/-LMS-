package com.sp.app.model;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommunityReply {
    private long replyNum;
    private long parent; // 기존 parentNum을 parent로 변경 (부모 댓글의 ID)
    
    private long studentId; // schoolmember의 기본키
    private String content;  // 댓글 내용
    private String reg_date; // 등록 날짜
    private String modify_date; // 수정 날짜
    private int showReply;   // 댓글 숨김 여부
    private int showCommunityReply; // 게시글 숨김 여부 (1: 안 숨김, 0: 숨김)

    private int replyLike;   // 좋아요 (1: 좋아요, 0: 싫어요)
    private int answerCount; // 답글 수
    private int likeCount;   // 좋아요 카운트
    private int disLikeCount;// 싫어요 카운트

    @Value("-1")
    private int userLiked;   // 현재 사용자가 좋아요/싫어요를 눌렀는지 (-1: 미체크, 1: 좋아요, 0: 싫어요)

    // 추가된 컬럼들 (계층 구조 및 정렬을 위한 필드)
    private int depth;     // 댓글의 깊이 (최상위 댓글: 0, 답글: 1, ...)
    private int orderNo;   // 같은 그룹 내에서 댓글의 정렬 순서
    private long groupNum;  // 댓글 그룹 번호 (연관된 댓글들을 묶는 번호)
    private long gap;       // 댓글 간 간격 혹은 정렬을 위한 추가 정보
}