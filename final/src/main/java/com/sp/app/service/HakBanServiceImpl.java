package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.HakBanMapper;
import com.sp.app.model.HakBan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class HakBanServiceImpl implements HakBanService {
	private final HakBanMapper mapper;
	
	@Override
	public void insertHakBan(HakBan dto) throws Exception {
		try {
			mapper.insertHakBan(dto);
		} catch (Exception e) {
			log.info("insertHakBan : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateHakBan(HakBan dto) throws Exception {
		try {
			mapper.updateHakBan(dto);
		} catch (Exception e) {
			log.info("insertHakBan : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteHakBan(long hakBanNum) throws Exception {
		try {
			mapper.deleteHakBan(hakBanNum);
		} catch (Exception e) {
			log.info("insertHakBan : ", e);
			throw e;
		}
		
	}

	@Override
	public HakBan findHakBanById(long hakBanNum) throws Exception {
		HakBan dto = null;
		
		try {
			dto = mapper.findHakBanById(hakBanNum);
		} catch (Exception e) {
			log.info("insertHakBan : ", e);
			
		}
		return dto;
	}

	@Override
	public List<HakBan> listHakBan(Map<String, Object> map) throws Exception {
		List<HakBan> list = null;
		
		try {
			list = mapper.listHakBan(map);
		} catch (Exception e) {
			log.info("insertHakBan : ", e);
			throw e;
		}
		return list;
	}

}
