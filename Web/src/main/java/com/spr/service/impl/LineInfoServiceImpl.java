package com.spr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spr.model.LineInfo;
import com.spr.repository.LineInfoRepository;
import com.spr.service.LineInfoService;

@Service
public class LineInfoServiceImpl implements LineInfoService {
	
	@Autowired
	private LineInfoRepository lineInfoRepository;

	public List<LineInfo> findAll() {

		return lineInfoRepository.findAll();
	}

}
