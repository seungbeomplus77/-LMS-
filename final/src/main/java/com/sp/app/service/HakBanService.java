package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.HakBan;

public interface HakBanService {
    public void insertHakBan(HakBan dto) throws Exception;
    public void updateHakBan(HakBan dto) throws Exception;
    public void deleteHakBan(long hakBanNum) throws Exception;
    public HakBan findHakBanById(long hakBanNum) throws Exception;
    public List<HakBan> listHakBan(Map<String, Object> map) throws Exception;
}
