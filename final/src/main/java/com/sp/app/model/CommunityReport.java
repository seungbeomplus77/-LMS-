package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommunityReport {
    private long reportNum; // 신고 번호
    private long communityNum; // 신고 글 번호
    private String reportReason; // 신고 사유
    private String reportDate; // 신고 날짜
    private int reportStatus; // 신고 처리 상태 (예: 0: 미처리, 1: 처리)

    private String studentId; // 신고 아이디
}
