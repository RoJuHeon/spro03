package com.keduit.service;

import java.util.List;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;

public interface BoardService {
	
	public Long register(BoardVO board);
	
	//한 건만 읽어옴
	public BoardVO get(Long bno);
	
	//여러건 읽어옴, getList를 뿌림
	//public List<BoardVO> getList();
	//Criteria 타입의 cri 추가
	public List<BoardVO> getList(Criteria cri);
	
	//수정
	public int modify(BoardVO board);
	
	//삭제
	public boolean remove(Long bno);
	
	//검색한 페이지 수
	public int getTotalCount(Criteria cri);
}
