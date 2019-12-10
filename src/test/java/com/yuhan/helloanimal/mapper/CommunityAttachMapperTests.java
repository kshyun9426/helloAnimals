package com.yuhan.helloanimal.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuhan.helloanimal.config.RootConfig;
import com.yuhan.helloanimal.vo.CommunityAttachVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class CommunityAttachMapperTests {
	
	@Autowired
	private CommunityAttachMapper mapper;
	
	private CommunityAttachVO attachVO;
	
	public CommunityAttachMapperTests() {
		this.attachVO = new CommunityAttachVO();
		attachVO.setUuid("UUID2");
		attachVO.setImagename("이미지 이름2");
		attachVO.setUploadpath("업로드 경로2");
		attachVO.setCommunity_no(2L);
	}
	
	//테스트 마무리
//	@Test
//	public void testInsert() {
//		mapper.insert(attachVO);
//	}
	
	//테스트 마무리
//	@Test
//	public void testFindByCommunity_no() {
//		List<CommunityAttachVO> list = mapper.findByCommunity_no(2L);
//		list.forEach(vo->{
//			log.info(vo);
//		});
//	}
	
	//테스트 마무리
//	@Test
//	public void testDelete() {
//		int resultCnt = mapper.delete("UUID1");
//		if(resultCnt == 1)
//			log.info("성공");
//		else 
//			log.info("실패");
//	}
	
	//테스트 마무리
//	@Test
//	public void testDeleteAll() {
//		int resultCnt = mapper.deleteAll(2L);
//		if(resultCnt != 0)
//			log.info("성공");
//		else 
//			log.info("실패");
//	}
	
	
	
}



















