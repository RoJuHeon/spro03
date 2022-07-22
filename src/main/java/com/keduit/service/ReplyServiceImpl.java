package com.keduit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.keduit.domain.Criteria;
import com.keduit.domain.ReplyPageDTO;
import com.keduit.domain.ReplyVO;
import com.keduit.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Controller
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {//view에게 받아와서 시킴
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	
	@Override
	public int register(ReplyVO vo) {//mapper.insert, 하나 입력해야하니까 vo
		log.info("---댓글 등록 작업---" + vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		log.info("---댓글 상세조회---" + rno);
		return mapper.read(rno);
	}
	
	//업데이트
	@Override
	public int modify(ReplyVO vo) {
		log.info("--- 댓글 수정 하기---" + vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		log.info("---댓글 삭제하기---" + rno);
		return mapper.delete(rno);
	}
	
	//전체페이지를 가져오기
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("---댓글 목록 가져오기---" + cri + "," + bno);
		return mapper.getListwithPaging(cri, bno);
	}
	
	//해당 페이지를 선택했을때 목록가져오기
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),//게시글 번호 하나 주면 해당의 댓글수를 가져오기
				mapper.getListwithPaging(cri, bno));//해당 페이지 수를 읽어오기
	}
}
