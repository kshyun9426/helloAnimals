package com.yuhan.helloanimal.service;

import java.util.List;

import com.yuhan.helloanimal.vo.CommunityAttachVO;

public interface ICommunityAttachService {
	
	public List<CommunityAttachVO> getList(long community_no);
	
	public int getCount(long community_no);
	
}
