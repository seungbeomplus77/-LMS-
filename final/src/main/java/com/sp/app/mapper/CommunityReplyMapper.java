package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.CommunityReply;

@Mapper
public interface CommunityReplyMapper {
	public long CommunitySeq();
	public void insertCommunityReply(CommunityReply dto) throws SQLException;
	public void updateCommunityReply(CommunityReply dto) throws SQLException;
	public void deleteCommunityReply(CommunityReply dto) throws SQLException;
	
	public void updateOrderNo(Map<String, Object> map) throws SQLException;
	
	public int dataCount(Map<String, Object> map);
	public List<CommunityReply> listCommunityReply(Map<String, Object> map);
	
	public CommunityReply findById(long replyNum);
	public void updateHitCount(long boardNum) throws SQLException;
	public CommunityReply findByPrev(Map<String, Object> map);
	public CommunityReply findByNext(Map<String, Object> map);
}
