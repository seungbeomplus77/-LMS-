package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeacherSchedule {
    private long teacherScheduleNum;
    private String subject;
    private String color;
    private String startDate;      // YYYY-MM-DD 형식 (varchar2(10))
    private String endDate;        // YYYY-MM-DD 형식
    private String startTime;      // HH:MM 형식 (varchar2(5))
    private String endTime;        // HH:MM 형식
    private int repeat;
    private int repeat_cycle;   // DB 컬럼명: repeat_cycle
    private String memo;
    private String regDate;
    
    private long teacherId;
    private long categoryId;
    
    private String all_day;
	
    // FullCalendar 에서 사용하는 필드
	private String start;
	private String end;
}
