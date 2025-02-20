package com.sp.app.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.QaMapper;
import com.sp.app.model.Qa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class QaServiceImpl implements QaService {
	private final QaMapper mapper;
	
	@Override
	public void insertQa(Qa dto) throws Exception {
		try {
			mapper.insertQa(dto);
		} catch (Exception e) {
			log.info("insertQa : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateQa(Qa dto) throws Exception {
		try {
			mapper.updateQa(dto);
		} catch (Exception e) {
			log.info("updateQa: ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteQa(long qaNum) throws Exception {
		try {
			mapper.deleteQa(qaNum);
		} catch (Exception e) {
			log.info("deleteQa : ", e);
			throw e;
		}
		
	}

	@Override
	public Qa findQaById(long qaNum) throws Exception {
		Qa dto = null;
		
		try {
			dto = mapper.findQaById(qaNum);
		} catch (Exception e) {
			log.info("findQaById : ", e);
		}
		
		return dto;
	}

	@Override
	public List<Qa> listQa(Map<String, Object> map) throws Exception {
		List<Qa> list = null;
		
		try {
			list = mapper.listQa(map);
		} catch (Exception e) {
			log.info("listQa : ", e);
		}
		
		return list;
	}

	@Override
	public int dataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("dataCount : ", e);
		}
		
		return result;
	}

	@Override
	public void updateAnswer(Qa dto) throws SQLException {
		try {
			mapper.updateAnswer(dto);
		} catch (Exception e) {
			log.info("updateAnswer : ", e);
			
			throw e;
		}
		
	}

}
