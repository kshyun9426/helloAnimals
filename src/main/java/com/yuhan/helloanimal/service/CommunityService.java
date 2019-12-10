package com.yuhan.helloanimal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.helloanimal.exception.NoGetCommunityException;
import com.yuhan.helloanimal.mapper.CommunityMapper;
import com.yuhan.helloanimal.vo.CommunityVO;
import com.yuhan.helloanimal.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class CommunityService implements ICommunityService {

	
	@Autowired
	private CommunityMapper communityMapper;
	
	@Override
	public void insertCommunity(CommunityVO vo) {
		log.info("커뮤니티 서비스단 삽입진행, VO:"+vo);
		communityMapper.insertCommunity(vo);
	}

	@Override
	public List<CommunityVO> getCommunityListWithPaging(Criteria cri) {
		return communityMapper.getCommunityList(cri);
	}

	@Override
	public CommunityVO getSpecificCommunity(long community_no) throws NoGetCommunityException{
		CommunityVO vo = communityMapper.getCommunity(community_no);
		if(vo != null)	
			return vo;
		throw new NoGetCommunityException();
	}
	
	
	
}



























