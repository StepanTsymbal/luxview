package com.spr.service;

import java.util.List;

import com.spr.model.FileInfo;
import com.spr.model.LineInfo;

public interface LineInfoService {
	
	List<LineInfo> findAll();
	
//	@Query("select l from Bank l where l.name = :name")
	List<LineInfo> findByFileInfo(FileInfo fileInfo);

}
