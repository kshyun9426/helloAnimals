package com.yuhan.helloanimal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yuhan.helloanimal.api.Api;
import com.yuhan.helloanimal.api.XmlString;
import com.yuhan.helloanimal.vo.AnimalInfo;
import com.yuhan.helloanimal.vo.Criteria;
import com.yuhan.helloanimal.vo.PageDTO;

@Controller
public class mainController {
	
	@GetMapping("/main/page")
	public void moveToMainPage(Model model, Criteria cri) {
		try {
			Api api = new Api(String.valueOf(cri.getPageNum()));
			XmlString resultXml = new XmlString(api.getResultXmlStr());
			String totalCount = resultXml.extractXmlValue("<totalCount>");
			List<String> itemTagList = resultXml.extractSpecificXmlStr("item");
			List<AnimalInfo> animalList = new ArrayList<>();
			for(int i=0; i<itemTagList.size(); i++) {
				String item = itemTagList.get(i);
				String kindCd = XmlString.extractXmlValue("<kindCd>", item); //품종
				String colorCd = XmlString.extractXmlValue("<colorCd>", item); //색상
				String age = XmlString.extractXmlValue("<age>", item); //나이
				String sexCd = XmlString.extractXmlValue("<sexCd>", item); //성별
				String popfile = XmlString.extractXmlValue("<popfile>", item); //썸네일사진
				String happenDt = XmlString.extractXmlValue("<happenDt>", item); //접수일
				String processState = XmlString.extractXmlValue("<processState>", item); //상태
				String neuterYn = XmlString.extractXmlValue("<neuterYn>", item); //중성화 여부
				String specialMark = XmlString.extractXmlValue("<specialMark>", item); //특징
				String careNm = XmlString.extractXmlValue("<careNm>", item); //보호소 이름
				String careTel = XmlString.extractXmlValue("<careTel>", item); //보호소 번호
				String careAddr = XmlString.extractXmlValue("<careAddr>", item); //보호소 장소
				String orgNm = XmlString.extractXmlValue("<orgNm>", item); //관할 기관
				String chargeNm = XmlString.extractXmlValue("<chargeNm>", item); //담당자
				String officetel = XmlString.extractXmlValue("<officetel>", item); //담당자연락처				
				String happenPlace = XmlString.extractXmlValue("<happenPlace>", item); //발견장소
				String weight = XmlString.extractXmlValue("<weight>", item); //무게
				
				
				
				AnimalInfo animalInfo = new AnimalInfo();
				animalInfo.setKindCd(kindCd);
				animalInfo.setColorCd(colorCd);
				animalInfo.setAge(age);
				animalInfo.setSexCd(sexCd);
				animalInfo.setPopfile(popfile);
				animalInfo.setHappenDt(happenDt);
				animalInfo.setProcessState(processState);
				animalInfo.setNeuterYn(neuterYn);
				animalInfo.setSpecialMark(specialMark);
				animalInfo.setCareNm(careNm);
				animalInfo.setCareTel(careTel);
				animalInfo.setCareAddr(careAddr);
				animalInfo.setOrgNm(orgNm);
				animalInfo.setChargeNm(chargeNm);
				animalInfo.setOfficetel(officetel);
				animalInfo.setHappenPlace(happenPlace);
				animalInfo.setWeight(weight);
				animalList.add(animalInfo);
			}
			model.addAttribute("animalList", animalList);
			model.addAttribute("pageMaker", new PageDTO(cri, Integer.parseInt(totalCount)));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
}






















