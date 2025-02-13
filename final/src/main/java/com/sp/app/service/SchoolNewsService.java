package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.SchoolNews;

public interface SchoolNewsService {
    public void insertSchoolNews(SchoolNews dto) throws Exception;
    public void updateSchoolNews(SchoolNews dto) throws Exception;
    public void deleteSchoolNews(long schoolNewsNum) throws Exception;
    public SchoolNews findSchoolNewsById(long schoolNewsNum) throws Exception;
    public List<SchoolNews> listSchoolNews(Map<String, Object> map) throws Exception;
    public int schoolNewsDataCount(Map<String, Object> map) throws Exception;
    public void updateSchoolNewsHitCount(long schoolNewsNum) throws Exception;
    public SchoolNews findPrevSchoolNews(Map<String, Object> map) throws Exception;
    public SchoolNews findNextSchoolNews(Map<String, Object> map) throws Exception;
}
