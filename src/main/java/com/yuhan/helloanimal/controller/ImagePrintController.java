package com.yuhan.helloanimal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuhan.helloanimal.service.CommunityAttachService;
import com.yuhan.helloanimal.vo.CommunityAttachVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class ImagePrintController {
	
	@Autowired
	private CommunityAttachService attachService;
	
	@GetMapping(value="/attachList", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<CommunityAttachVO>> getAttachList(long community_no){
		return new ResponseEntity<>(attachService.getList(community_no), HttpStatus.OK);
	}
	
	@GetMapping(value="/attachPreImage", produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<CommunityAttachVO> getAttachOneImg(long community_no){
		List<CommunityAttachVO> list = attachService.getList(community_no);
		if(list.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(list.get(0), HttpStatus.OK);
	}
	
	
}



















