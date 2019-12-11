package com.yuhan.helloanimal.mapper;

import java.util.List;

import com.yuhan.helloanimal.vo.CommunityAttachVO;

public interface CommunityAttachMapper {
	
	public void insert(CommunityAttachVO vo);
	
	public int delete(String uuid);
	
	public List<CommunityAttachVO> findByCommunity_no(long community_no);
	
	public int deleteAll(long community_no);
	
	public int getCountByCommunity_no(long community_no);
	
	
}


















