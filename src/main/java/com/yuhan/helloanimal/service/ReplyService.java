package com.yuhan.helloanimal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuhan.helloanimal.exception.NoGetReplyException;
import com.yuhan.helloanimal.mapper.CommunityReplyMapper;
import com.yuhan.helloanimal.vo.CommunityReplyVO;

@Service
public class ReplyService implements IReplyService {

	@Autowired
	private CommunityReplyMapper replyMapper;
	
	@Override
	public void insert(CommunityReplyVO vo) {
		replyMapper.insert(vo);
	}

	@Override
	public CommunityReplyVO get(long reply_no) throws NoGetReplyException{
		CommunityReplyVO vo = replyMapper.get(reply_no); 
		if(vo == null)
			throw new NoGetReplyException();
		return vo;
	}

	@Override
	public boolean remove(long reply_no) {
		int removeCnt = replyMapper.delete(reply_no);
		if(removeCnt != 0)
			return true;
		return false;
	}

	@Override
	public List<CommunityReplyVO> getList(long community_no) throws NoGetReplyException{
		List<CommunityReplyVO> list = replyMapper.getList(community_no);
		if(list == null)
			throw new NoGetReplyException();
		return list;
	}

	
	
}
