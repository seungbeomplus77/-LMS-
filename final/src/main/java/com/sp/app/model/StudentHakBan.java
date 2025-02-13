package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentHakBan {
    private long hakBanNum; // HakBan 테이블의 학년-반 번호
    private long studentId; // 학생의 schoolId (student 테이블의 PK)
}
