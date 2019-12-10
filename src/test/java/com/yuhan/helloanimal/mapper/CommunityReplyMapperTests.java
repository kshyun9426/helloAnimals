package com.yuhan.helloanimal.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuhan.helloanimal.config.RootConfig;
import com.yuhan.helloanimal.vo.CommunityReplyVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class CommunityReplyMapperTests {

	@Autowired
	private CommunityReplyMapper mapper;
	
	private CommunityReplyVO replyVo;
	
	public CommunityReplyMapperTests() {
		this.replyVo = new CommunityReplyVO();
		replyVo.setCommunity_no(2L);
		replyVo.setReply_content("리플 내용입니다.1");
		replyVo.setReply_password("리플 password1");
		replyVo.setReply_writer("리플 작성자 김승현");
	}
	
	//테스트 마무리
//	@Test
//	public void testInsert() {
//		mapper.insert(replyVo);
//	}
	
	//테스트 마무리
//	@Test
//	public void testGet() {
//		log.info(mapper.get(1L));
//	}
	
	//테스트 마무리
//	@Test
//	public void testDelete() {
//		int resultCnt = mapper.delete(1L);
//		if(resultCnt == 1) {
//			log.info("성공");
//		}else {
//			log.info("실패");
//		}
//	}
	
	
	
}

















