package com.yuhan.helloanimal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.helloanimal.mapper.CommunityAttachMapper;
import com.yuhan.helloanimal.vo.CommunityAttachVO;

@Service
public class CommunityAttachService implements ICommunityAttachService {

	@Autowired
	private CommunityAttachMapper attachMapper;
	
	@Override
	public List<CommunityAttachVO> getList(long community_no) {	
		List<CommunityAttachVO> attachList = attachMapper.findByCommunity_no(community_no);
		return attachList;
	}

	@Override
	public int getCount(long community_no) {
		return attachMapper.getCountByCommunity_no(community_no);
	}

}
