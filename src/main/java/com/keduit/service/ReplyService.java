package com.keduit.service;

import java.util.List;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;

public interface ReplyService {
	//추방메서드, 등록
	public int register(ReplyVO vo);
	
	//번호주고 하나 읽어와
	public ReplyVO get(Long rno);
	
	//한묶을 줘야함
	public int modify(ReplyVO vo);
	
	//삭제
	public int remove(Long rno);
	
	//bno주고 전체페이지를 읽어옴
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	//bno주고 해당 페이지 읽어오기
	public ReplyPageDTO getListPage(Criteria cri, Long bno);
}
