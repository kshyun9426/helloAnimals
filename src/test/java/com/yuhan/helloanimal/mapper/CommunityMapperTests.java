package com.yuhan.helloanimal.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuhan.helloanimal.config.RootConfig;
import com.yuhan.helloanimal.vo.CommunityVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class CommunityMapperTests {
	
	@Autowired
	private CommunityMapper mapper;
	
	private CommunityVO m_tempVO;
	
	public CommunityMapperTests() {
		this.m_tempVO = new CommunityVO();
		this.m_tempVO.setCommunity_content("테스트 내용입니다.");
		this.m_tempVO.setCommunity_password("password123");
		this.m_tempVO.setCommunity_title("테스트 제목입니다.");
	}
	
	//테스트 마무리
//	@Test
//	public void testInsert() {
//		mapper.insertCommunity(m_tempVO);
//	}
	
	//테스트 성공
//	@Test
//	public void testUpdate() {
//		this.m_tempVO.setCommunity_title("수정된 title입니다.");
//		this.m_tempVO.setCommunity_no(1);
//		int updatedCount = mapper.updateCommunity(m_tempVO);
//		if(updatedCount != 1) {
//			log.info("수정 실패");
//		}else {
//			log.info("수정 성공");
//		}
//	}
	
	//테스트 마무리
//	@Test
//	public void testGet() {
//		CommunityVO vo = mapper.getCommunity(1L);
//		log.info(vo);
//	}
	
	//테스트 마무리
//	@Test
//	public void testIncreaseHits() {
//		int resultCnt = mapper.increaseHits(1L);
//		if(resultCnt == 1) {
//			log.info("성공");
//		}else {
//			log.info("실패");
//		}
//	}
	
	//테스트 마무리
//	@Test
//	public void testDelete() {
//		int resultCnt = mapper.deleteCommunity(1L);
//		if(resultCnt == 1) {
//			log.info("성공");
//		}else {
//			log.info("실패");
//		}
//	}
	
}































