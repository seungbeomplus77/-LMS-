package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student {
    private long schoolId;
    private String enterDate; // 입학일
    private String graduateDate; // 졸업일
}
