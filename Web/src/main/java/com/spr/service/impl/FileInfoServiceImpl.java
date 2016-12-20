package com.spr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spr.model.FileInfo;
import com.spr.repository.FileInfoRepository;
import com.spr.service.FileInfoService;

@Service
public class FileInfoServiceImpl implements FileInfoService {
	
	@Autowired
	FileInfoRepository fileInfoRepository;

	public List<FileInfo> getAll() {
		
		return fileInfoRepository.findAll();
	}

}
