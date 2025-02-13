package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.Exam;

public interface ExamService {
    public void insertExam(Exam dto) throws Exception;
    public void updateExam(Exam dto) throws Exception;
    public void deleteExam(long examId) throws Exception;
    public Exam findExamById(long examId) throws Exception;
    public List<Exam> listExam(Map<String, Object> map) throws Exception;
    public int examDataCount(Map<String, Object> map) throws Exception;
}
