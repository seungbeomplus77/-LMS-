package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.Community;

@Mapper
public interface CommunityMapper {
	public void insertCommunity(Community dto) throws SQLException;
	public void updateCommunity(Community dto) throws SQLException;
	public void deleteCommunity(Community dto) throws SQLException;
	
	public int dataCount(Map<String, Object> map);
	public List<Community> listCommunity(Map<String, Object> map);
	
	public Community findById(long communityNum);
	public void updateHitCount(long communityNum) throws SQLException;
	public Community findByPrev(Map<String, Object> map);
	public Community findByNext(Map<String, Object> map);
	
	// 게시글 좋아요
	public void insertCommunityLike(Map<String, Object> map) throws SQLException;
	public void deleteCommunityLike(Map<String, Object> map) throws SQLException;
	public int CommunityLikeCount(long communityNum);
	public Community userCommunityLiked(Map<String, Object> map);
}
