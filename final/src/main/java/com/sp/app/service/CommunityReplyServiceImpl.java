package com.sp.app.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.common.MyUtil;
import com.sp.app.mapper.CommunityReplyMapper;
import com.sp.app.model.CommunityReply;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityReplyServiceImpl implements CommunityReplyService {
	private final CommunityReplyMapper mapper;
	private final MyUtil myUtil;
	
	@Override
	public void insertCommunityReply(CommunityReply dto, String mode) throws Exception {
		try {
			long seq = mapper.CommunitySeq();
			
			if (mode.equals("write")) { // 새글 등록시
				dto.setReplyNum(seq);
				dto.setGroupNum(seq);
				dto.setDepth(0);
				dto.setOrderNo(0);
				dto.setParent(0);
			} else { // 답글 등록시
				// orderNo 변경
				Map<String, Object> map = new HashMap<>();
				map.put("groupNum", dto.getGroupNum());
				map.put("orderNo", dto.getOrderNo());
				mapper.updateOrderNo(map);
				
				dto.setReplyNum(seq);
				dto.setDepth(dto.getDepth() + 1);
				dto.setOrderNo(dto.getOrderNo() + 1);
			}
			
			mapper.insertCommunityReply(dto);
		
		} catch (Exception e) {
			log.info("insertCommunityReply : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateCommunityReply(CommunityReply dto) throws Exception {
		try {
			mapper.updateCommunityReply(dto);
		} catch (Exception e) {
			log.info("updateCommunityReply : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteCommunityReply(long replyNum) throws Exception {
		try {
			mapper.deleteCommunityReply(replyNum);
		} catch (Exception e) {
			log.info("deleteCommunityReply : ", e);
		}
		
	}

	@Override
	public int communityReplyDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("communityReplyDataCount : ", e);
		}
		
		return result;
	}

	@Override
	public List<CommunityReply> listCommunityReply(Map<String, Object> map) throws Exception {
		List<CommunityReply> list = null;
		long gap;
		
		try {
			list = mapper.listCommunityReply(map);
			
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	        LocalDateTime today = LocalDateTime.now();
	        
	        for(CommunityReply dto : list) {
	            LocalDateTime dateTime = LocalDateTime.parse(dto.getReg_date(), formatter);
	            gap = dateTime.until(today, ChronoUnit.HOURS);
	            dto.setGap(gap);
	            
	            dto.setContent(myUtil.htmlSymbols(dto.getContent()));
	            
	            dto.setReg_date(dto.getReg_date().substring(0, 10));
	        }
			
		} catch (Exception e) {
			log.info("listCommunityReply : ", e);
			throw e;
		}
		
		return list;
	}

	@Override
	public CommunityReply findCommunityReplyById(long replyNum) throws Exception {
		CommunityReply dto = null;
		
		try {
			dto = mapper.findById(replyNum);
		} catch (Exception e) {
			log.info("findCommunityReplyById : ", e);
		}
		
		return dto;
	}

	@Override
	public void updateCommunityReplyHitCount(long replyNum) throws Exception {
		try {
			mapper.updateHitCount(replyNum);
		} catch (Exception e) {
			log.info("updateCommunityReplyHitCount : ", e);
			throw e;
		}
		
	}

	@Override
	public CommunityReply findPrevCommunityReply(Map<String, Object> map) throws Exception {
		CommunityReply dto = null;
		
		try {
			dto = mapper.findByPrev(map);
		} catch (Exception e) {
			log.info("findPrevCommunityReply : ", e);
		}
		
		return dto;
	}

	@Override
	public CommunityReply findNextCommunityReply(Map<String, Object> map) throws Exception {
		CommunityReply dto = null;
		
		try {
			dto = mapper.findByNext(map);
		} catch (Exception e) {
			log.info("findNextCommunityReply : ", e);
		}
		
		return dto;
	}

	@Override
	public void insertReplyLike(Map<String, Object> map) throws Exception {
		try {
			mapper.insertReplyLike(map);
		} catch (Exception e) {
			log.info("insertReplyLike : ", e);
			
			throw e;
		}
		
	}

	@Override
	public void deleteReply(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteReply(map);
		} catch (Exception e) {
			log.info("deleteReply : ", e);
			
			throw e;
		}
		
	}

	@Override
	public Map<String, Object> replyLikeCount(Map<String, Object> map) throws Exception {
		Map<String, Object> list = null;
		
		try {
			list = mapper.replyLikeCount(map);
		} catch (Exception e) {
			log.info("replyLikeCount : ", e);
		}
		
		return list;
	}

	protected int userReplyLiked(Map<String, Object> map) {
		int result = -1;
		
		try {
			result = mapper.userReplyLiked(map).orElse(-1);
		} catch (Exception e) {
			log.info("userReplyLiked : ", e);
		} 
		
		return result;
	}
	@Override
	public void updateReplyShowHide(Map<String, Object> map) throws Exception {
		try {
			mapper.updateReplyShowHide(map);
		} catch (Exception e) {
			log.info("updateReplyShowHide : ", e);
			
			throw e;
		}
	}

}
