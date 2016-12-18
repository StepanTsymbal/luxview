package com.spr.controller;

import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.spr.model.FileInfo;
//import com.spr.model.FileInfo;
import com.spr.service.FileInfoService;
import com.spr.service.LineInfoService;

@Controller
//@RequestMapping(value ="/go")
public class ViewerController {
	
	@Autowired
	FileInfoService fileInfoService;
	
	@Autowired
	LineInfoService lineInfoService;
	
//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseEntity<List<FileInfo>> getAllFiles() {
//    	
////    	HttpHeaders responseHeaders = new HttpHeaders();
////    	responseHeaders.setContentType(MediaType.valueOf("application/json"));
//    	
//        List<FileInfo> fileList = fileInfoService.getAll();
//        if(fileList.isEmpty()){
//            return new ResponseEntity<List<FileInfo>>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<List<FileInfo>>(fileList, HttpStatus.OK);
//    }
    
//	@RequestMapping(value="/list", method=RequestMethod.GET)
//	public ModelAndView fileInfoListPage() {
//		ModelAndView mav = new ModelAndView("file-list");
//		List<FileInfo> fileList = fileInfoService.getAll();
//		mav.addObject("fileList", fileList);
//		return mav;
//	}
	
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody List<FileInfo> getFiles() {
    	
    	List<FileInfo> fileList = fileInfoService.getAll();
 
//   	 employee.setName(name);
//   	 employee.setEmail("employee1@genuitec.com");
 
    	return fileList;
//   	 return new Gson().toJson(fileList);
 
    }

}
