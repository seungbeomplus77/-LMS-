package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HakBan {
    private Long hakBanNum;
    private String hak;
    private String ban;
    
    private long teacherId;	// teacher 테이블 기본키
}
