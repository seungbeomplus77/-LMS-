package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.CommunityReply;

public interface CommunityReplyService {
    public void insertCommunityReply(CommunityReply dto) throws Exception;
    public void updateCommunityReply(CommunityReply dto) throws Exception;
    public void deleteCommunityReply(Map<String, Object> map) throws Exception;
    public int communityReplyDataCount(Map<String, Object> map) throws Exception;
    public List<CommunityReply> listCommunityReply(Map<String, Object> map) throws Exception;
    public CommunityReply findCommunityReplyById(long replyNum) throws Exception;
    public void updateCommunityReplyHitCount(long replyNum) throws Exception;
    public CommunityReply findPrevCommunityReply(Map<String, Object> map) throws Exception;
    public CommunityReply findNextCommunityReply(Map<String, Object> map) throws Exception;
}
