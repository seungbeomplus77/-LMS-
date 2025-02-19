package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.CommunityReply;

@Mapper
public interface CommunityReplyMapper {
	public long CommunitySeq();
	public void insertCommunityReply(CommunityReply dto) throws SQLException;
	public void updateCommunityReply(CommunityReply dto) throws SQLException;
	public void deleteCommunityReply(long replyNum) throws SQLException;
	
	public void updateOrderNo(Map<String, Object> map) throws SQLException;
	
	public int dataCount(Map<String, Object> map);
	public List<CommunityReply> listCommunityReply(Map<String, Object> map);
	
	public CommunityReply findById(long replyNum);
	public void updateHitCount(long boardNum) throws SQLException;
	public CommunityReply findByPrev(Map<String, Object> map);
	public CommunityReply findByNext(Map<String, Object> map);
	
	// 댓글 좋아요 싫어요
    public void insertReplyLike(Map<String, Object> map) throws SQLException;
    public void deleteReply(Map<String, Object> map) throws SQLException;
    public Map<String, Object> replyLikeCount(Map<String, Object> map) throws SQLException;
    public Optional<Integer> userReplyLiked(Map<String, Object> map) throws SQLException;
    
	// 댓글 숨김/표시
	public void updateReplyShowHide(Map<String, Object> map) throws SQLException;
	public List<CommunityReply> listReplyAnswer(Map<String, Object> map);
	public void insertReply(CommunityReply dto) throws SQLException;
	
	public int replyAnswerCount(Map<String, Object> map);
}
