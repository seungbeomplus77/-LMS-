package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.common.MyUtil;
import com.sp.app.mapper.CommunityMapper;
import com.sp.app.model.Community;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityServiceImpl implements CommunityService {
	private final CommunityMapper mapper;
	private final MyUtil myUtil;
	
	@Override
	public void insertCommunity(Community dto) throws Exception {
		try {
			mapper.insertCommunity(dto);
		} catch (Exception e) {
			log.info("insertCommunity : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateCommunity(Community dto) throws Exception {
		try {
			mapper.updateCommunity(dto);
		} catch (Exception e) {
			log.info("updateCommunity : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteCommunity(long communityNum, String studentId) throws Exception {
		try {
			mapper.deleteCommunity(communityNum);
		} catch (Exception e) {
			log.info("deleteCommunity : ", e);
			throw e;
		}
		
	}

	@Override
	public Community findCommunityById(long communityNum) throws Exception {
		Community dto = null;
		
		try {
			dto = mapper.findCommunityById(communityNum);
		} catch (Exception e) {
			log.info("findCommunityById : ", e);
		}
		
		return dto;
	}

	@Override
	public List<Community> listCommunity(Map<String, Object> map) throws Exception {
		List<Community> list = null;
		
		try {
			list = mapper.listCommunity(map);
			
		} catch (Exception e) {
			log.info("listCommunity : ", e);
			throw e;
		}
		
		return list;
	}

	@Override
	public int communityDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("communityDataCount : ", e);
		}
		
		return result;
	}

	@Override
	public void updateCommunityHitCount(long communityNum) throws Exception {
		try {
			mapper.updateHitCount(communityNum);
		} catch (Exception e) {
			log.info("updateCommunityHitCount : ", e);
			throw e;
		}
		
	}

	@Override
	public Community findPrevCommunity(Map<String, Object> map) throws Exception {
		Community dto = null;
		
		try {
			dto = mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findPrevCommunity : ", e);
		}
		
		return dto;
	}

	@Override
	public Community findNextCommunity(Map<String, Object> map) throws Exception {
		Community dto = null;
		
		try {
			dto = mapper.findByNext(map);
		} catch (Exception e) {
			log.info("findNextCommunity : ", e);
		}
		
		return dto;
	}

	@Override
	public void insertCommunityLike(Map<String, Object> map) throws Exception {
		try {
			mapper.insertCommunityLike(map);
		} catch (Exception e) {
			log.info("insertCommunityLike : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteCommunityLike(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteCommunityLike(map);
		} catch (Exception e) {
			log.info("deleteCommunityLike : ", e);
			throw e;
		}
		
	}

	@Override
	public int CommunityLikeCount(long communityNum) {
		int result = 0;
		
		try {
			result = mapper.CommunityLikeCount(communityNum);
		} catch (Exception e) {
			log.info("CommunityLikeCount : ", e);
		}
		return result;
	}

	@Override
	public boolean userCommunityLiked(Map<String, Object> map) {
		boolean result = false;
		
		try {
			Community dto = mapper.userCommunityLiked(map);
			if(dto != null) {
				result = true;
			}
		} catch (Exception e) {
			log.info("userCommunityLiked : ", e);
		}
		
		return result;
	}
}
