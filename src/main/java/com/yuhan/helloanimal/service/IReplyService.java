package com.yuhan.helloanimal.service;

import java.util.List;

import com.yuhan.helloanimal.exception.NoGetReplyException;
import com.yuhan.helloanimal.vo.CommunityReplyVO;

public interface IReplyService {
	
	public void insert(CommunityReplyVO vo);
	
	public CommunityReplyVO get(long reply_no) throws NoGetReplyException;
	
	public boolean remove(long reply_no);
	
	public List<CommunityReplyVO> getList(long community_no) throws NoGetReplyException;
	
}
