package com.yuhan.helloanimal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yuhan.helloanimal.service.ICommunityService;
import com.yuhan.helloanimal.vo.CommunityVO;
import com.yuhan.helloanimal.vo.Criteria;
import com.yuhan.helloanimal.vo.PageDTO;

@Controller
public class CommunityController {
	
	@Autowired
	private ICommunityService communityService;
	
	@GetMapping("/community/list")
	public void showCommunityList(Model model, Criteria cri) {
		List<CommunityVO> communityList = communityService.getCommunityListWithPaging(cri);
		model.addAttribute("pageMaker", new PageDTO(cri,123));
		model.addAttribute("list", communityList);
	}
	
	@GetMapping("/community/inputForm")
	public void showCommunityInputForm() {
		
	}
	
	@PostMapping("/community/input")
	public String insertCommunity(Model model, CommunityVO vo, RedirectAttributes rttr) {
		
		communityService.insertCommunity(vo);
		rttr.addFlashAttribute("result", "커뮤니티 글이 등록되었습니다.");
		return "redirect:/community/list";
	}
	
	@GetMapping("/community/page")
	public void showCommunityPage(Model model, @RequestParam("no") long community_no) {
		
		CommunityVO vo = communityService.getSpecificCommunity(community_no);
		model.addAttribute("communityInfo", vo);
	}
	
}













