package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.common.MyUtil;
import com.sp.app.mapper.FaqMapper;
import com.sp.app.model.Faq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FaqServiceImpl implements FaqService {
	private final FaqMapper mapper;
	private final MyUtil myUtil;
	
	@Override
	public void insertFaq(Faq dto) throws Exception {
		try {
			mapper.insertFaq(dto);
		} catch (Exception e) {
			log.info("insertFaq : ", e);
			
			throw e;
		}
		
	}

	@Override
	public void updateFaq(Faq dto) throws Exception {
		try {
			mapper.updateFaq(dto);
		} catch (Exception e) {
			log.info("updateFaq : ", e);
			
			throw e;
		}
		
	}

	@Override
	public void deleteFaq(long faqNum) throws Exception {
		try {
			mapper.deleteFaq(faqNum);
		} catch (Exception e) {
			log.info("deleteFaq : ", e);
			
			throw e;
		}
		
	}

	@Override
	public Faq findFaqById(long faqNum) throws Exception {
		Faq dto = null;
		
		try {
			dto = mapper.findFaqById(faqNum);
		} catch (Exception e) {
			log.info("findFaqById : ", e);
		}
		
		return dto;
	}

	@Override
	public List<Faq> listFaq(Map<String, Object> map) {
		List<Faq> list = null;

		try {
			list = mapper.listFaq(map);
			
			for (Faq dto : list) {
				dto.setAnswer(myUtil.htmlSymbols(dto.getAnswer()));
			}
		} catch (Exception e) {
			log.info("listFaq : ", e);
		}

		return list;
	}

	@Override
	public int faqDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("faqDataCount : ", e);
		}
		
		return result;
	}
}
