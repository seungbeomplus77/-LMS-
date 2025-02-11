package com.sp.app.student.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller   // 뷰 반환용 컨트롤러
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/student/mystudy")
public class MyStudyController {

    // 1. 뷰(JSP)를 반환하는 메서드
    @GetMapping("main")
    public String main() {
        return "student/mystudy/main";
    }

    // 2. AJAX 호출용 JSON 데이터를 반환하는 메서드
    @GetMapping("data")
    @ResponseBody
    public Map<String, Object> getChartData() {
        Map<String, Object> data = new HashMap<>();

        data.put("days", Arrays.asList(
            Map.of("ORDERDATE", "2025-02-01", "TOTALMONEY", 1000),
            Map.of("ORDERDATE", "2025-02-02", "TOTALMONEY", 1500)
        ));

        data.put("dayOfWeek", Map.of(
            "SUN", 5,
            "MON", 10,
            "TUE", 15,
            "WED", 20,
            "THU", 25,
            "FRI", 30,
            "SAT", 35,
            "month", "202502"  // 연월 표기
        ));

        data.put("months", Arrays.asList(
            Map.of("ORDERDATE", "202501", "TOTALMONEY", 3000),
            Map.of("ORDERDATE", "202502", "TOTALMONEY", 3500)
        ));

        return data;
    }
}
