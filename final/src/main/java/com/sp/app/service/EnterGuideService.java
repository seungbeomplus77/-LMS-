package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.EnterGuide;

public interface EnterGuideService {
    public void insertEnterGuide(EnterGuide dto) throws Exception;
    public void updateEnterGuide(EnterGuide dto) throws Exception;
    public void deleteEnterGuide(long enterGuideNum) throws Exception;
    public EnterGuide findEnterGuideById(long enterGuideNum) throws Exception;
    public List<EnterGuide> listEnterGuide(Map<String, Object> map) throws Exception;
    public int enterGuideDataCount(Map<String, Object> map) throws Exception;
    public void updateEnterGuideHitCount(long enterGuideNum) throws Exception;
    public EnterGuide findPrevEnterGuide(Map<String, Object> map) throws Exception;
    public EnterGuide findNextEnterGuide(Map<String, Object> map) throws Exception;
}
