package com.keduit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyVO;

public interface ReplyMapper {
	//추상메서드
	public int insert(ReplyVO vo);
	
	//한 건 읽어오기
	public ReplyVO read(Long bno);
	
	//삭제하기
	public int delete(Long rno);
	
	//수정하기
	public int update(ReplyVO reply);
	
	//리스트
	public List<ReplyVO> getListwithPaging(
			@Param("cri") Criteria cri, @Param("bno") Long bno);
	
	//int값을 리턴, 게시글 번호를 줘서 총댓글의 개수를 읽어오기
	public int getCountByBno(Long bno);
	
}
