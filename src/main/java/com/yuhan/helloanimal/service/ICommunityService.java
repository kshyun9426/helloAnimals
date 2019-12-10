package com.yuhan.helloanimal.service;

import java.util.List;

import com.yuhan.helloanimal.vo.CommunityVO;
import com.yuhan.helloanimal.vo.Criteria;

public interface ICommunityService {
	
	public void insertCommunity(CommunityVO vo);
	
	public List<CommunityVO> getCommunityListWithPaging(Criteria cri);
	
	public CommunityVO getSpecificCommunity(long community_no);
}
