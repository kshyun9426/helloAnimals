package com.yuhan.helloanimal.mapper;

import java.util.List;

import com.yuhan.helloanimal.vo.CommunityReplyVO;

public interface CommunityReplyMapper {
	
	public void insert(CommunityReplyVO vo);
	
	public int delete(long reply_no);
	
	public CommunityReplyVO get(long reply_no);
	
	public List<CommunityReplyVO> getList(long community_no);
}
