package com.spr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spr.model.FileInfo;
import com.spr.service.FileInfoService;

@Controller
public class ViewerController {
	
	@Autowired
	private FileInfoService fileInfoService;
	
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<FileInfo>> getAllFiles() {

    	HttpHeaders headers = new HttpHeaders();
    	headers.add("Access-Control-Allow-Origin", "*");

    	
        List<FileInfo> fileList = fileInfoService.getAll();
        if(fileList.isEmpty()){
            return new ResponseEntity<List<FileInfo>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<FileInfo>>(fileList, headers, HttpStatus.OK);
    }

}
