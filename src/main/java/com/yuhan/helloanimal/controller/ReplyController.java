package com.yuhan.helloanimal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yuhan.helloanimal.exception.NoGetReplyException;
import com.yuhan.helloanimal.service.IReplyService;
import com.yuhan.helloanimal.vo.CommunityReplyVO;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ReplyController {

	@Autowired
	private IReplyService replyService;
	
	@GetMapping(value="/community/list/reply/{community_no}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<CommunityReplyVO>> getReplyList(@PathVariable("community_no")long no){
		try {
			List<CommunityReplyVO> replyList = replyService.getList(no);
			return new ResponseEntity<List<CommunityReplyVO>>(replyList, HttpStatus.OK);
		}catch(NoGetReplyException ex) {
			return new ResponseEntity<List<CommunityReplyVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/community/insert/reply", consumes="application/json", produces= "text/plain;charset=UTF-8")
	public ResponseEntity<String> insertReply(@RequestBody CommunityReplyVO reply){
		replyService.insert(reply);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@DeleteMapping(value="/community/delete/reply", consumes="application/json", produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> deleteReply(@RequestBody CommunityReplyVO replyVO){
		log.info(replyVO);
		CommunityReplyVO vo = replyService.get(replyVO.getReply_no());
		if(!vo.getReply_password().equals(replyVO.getReply_password())) {
			return new ResponseEntity<String>("notMatchPassword", HttpStatus.OK);
		}
		if(replyService.remove(replyVO.getReply_no())) {
			return new ResponseEntity<String>("remove success", HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
































