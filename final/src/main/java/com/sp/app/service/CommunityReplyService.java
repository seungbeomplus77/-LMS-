package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.CommunityReply;

public interface CommunityReplyService {
    public void insertCommunityReply(CommunityReply dto) throws Exception;
    public void updateCommunityReply(CommunityReply dto) throws Exception;
    public void deleteCommunityReply(Map<String, Object> paramMap) throws Exception;
    public int communityReplyDataCount(Map<String, Object> map) throws Exception;
    public List<CommunityReply> listCommunityReply(Map<String, Object> map) throws Exception;
    public CommunityReply findCommunityReplyById(long replyNum) throws Exception;
    public void updateCommunityReplyHitCount(long replyNum) throws Exception;
    public CommunityReply findPrevCommunityReply(Map<String, Object> map) throws Exception;
    public CommunityReply findNextCommunityReply(Map<String, Object> map) throws Exception;
    
    // 댓글 좋아요, 싫어요, 삭제, 수정 등등
    public void insertReplyLike(Map<String, Object> map) throws Exception;
    public void deleteReply(Map<String, Object> map) throws Exception;
    public Map<String, Object> replyLikeCount(Map<String, Object> map) throws Exception;
    
	// 댓글 숨김/표시
	public void updateReplyShowHide(Map<String, Object> map) throws Exception;
	
	// 댓글의 답글
	public List<CommunityReply> listReplyAnswer(Map<String, Object> map);
	public int replyAnswerCount(Map<String, Object> map);
	
	public void insertReply(CommunityReply dto) throws Exception;
}
