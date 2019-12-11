package com.yuhan.helloanimal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuhan.helloanimal.service.ICommunityAttachService;
import com.yuhan.helloanimal.service.ICommunityService;
import com.yuhan.helloanimal.vo.CommunityAttachVO;
import com.yuhan.helloanimal.vo.CommunityVO;
import com.yuhan.helloanimal.vo.Criteria;
import com.yuhan.helloanimal.vo.PageDTO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class CommunityController {
	
	@Autowired
	private ICommunityService communityService;
	
	//커뮤니티 리스트
	@GetMapping("/community/list")
	public void showCommunityList(Model model, Criteria cri) {
		cri.setAmount(5); //한 페이지당 5개씩 출력하도록
		List<CommunityVO> communityList = communityService.getCommunityListWithPaging(cri);
		int totalCommunityCnt = communityService.getTotalCount();
		model.addAttribute("pageMaker", new PageDTO(cri,totalCommunityCnt));
		model.addAttribute("list", communityList);
	}
	
	@GetMapping("/community/inputForm")
	public void showCommunityInputForm() {
		
	}
	
	//커뮤니티 입력
	@PostMapping("/community/input")
	public String insertCommunity(Model model, CommunityVO vo, RedirectAttributes rttr) {
		
		log.info("====================== 전달된 이미지정보들 ==============================");
		if(vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach->log.info(attach));
		}
		communityService.insertCommunity(vo);
		rttr.addFlashAttribute("result", "커뮤니티 글이 등록되었습니다.");
		return "redirect:/community/list";
	}
	
	//커뮤니티 상세보기 페이지
	@GetMapping("/community/page")
	public void showCommunityPage(Model model, @RequestParam("no") long community_no) {
		CommunityVO vo = communityService.getSpecificCommunity(community_no);
		communityService.increateHits(community_no);
		model.addAttribute("communityInfo", vo);
	}
	
}













