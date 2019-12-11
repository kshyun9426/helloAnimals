package com.yuhan.helloanimal.mapper;

import java.util.List;

import com.yuhan.helloanimal.vo.CommunityVO;
import com.yuhan.helloanimal.vo.Criteria;

public interface CommunityMapper {
	
	public void insertCommunity(CommunityVO vo);
	
	public CommunityVO getCommunity(long community_no);
	
	public int deleteCommunity(long community_no);
	
	public int updateCommunity(CommunityVO vo);
	
	public int increaseHits(long community_no);
	
	public List<CommunityVO> getCommunityList(Criteria cri);
	
	public int getTotalCnt();
}
