package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.Community;

public interface CommunityService {
    public void insertCommunity(Community dto) throws Exception;
    public void updateCommunity(Community dto) throws Exception;
    public void deleteCommunity(long communityNum, String studentId) throws Exception;
   
    public Community findCommunityById(long communityNum) throws Exception;
    public List<Community> listCommunity(Map<String, Object> map) throws Exception;
    public int communityDataCount(Map<String, Object> map) throws Exception;
    public void updateCommunityHitCount(long communityNum) throws Exception;
   
    public Community findPrevCommunity(Map<String, Object> map) throws Exception;
    public Community findNextCommunity(Map<String, Object> map) throws Exception;
    
	// 게시글 좋아요
	public void insertCommunityLike(Map<String, Object> map) throws Exception;
	public void deleteCommunityLike(Map<String, Object> map) throws Exception;
	public int CommunityLikeCount(long num);
	public boolean userCommunityLiked(Map<String, Object> map);
}
