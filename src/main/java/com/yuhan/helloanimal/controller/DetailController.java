package com.yuhan.helloanimal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.yuhan.helloanimal.vo.AnimalInfo;
import com.yuhan.helloanimal.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class DetailController {
	
	@PostMapping("/detail/page")
	public void detailView(Model model, AnimalInfo animalDetailInfo, @ModelAttribute("cri") Criteria cri) {
		
		log.info(animalDetailInfo);
		model.addAttribute("animalInfo", animalDetailInfo);
	}
	
}























