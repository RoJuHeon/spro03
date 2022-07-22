package com.keduit.service;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.keduit.domain.BoardVO;
import com.keduit.domain.Criteria;
import com.keduit.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@Controller
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper mapper;

	@Override
	public Long register(BoardVO board) {
		log.info("register...." + board);
		mapper.insertSelectKey(board);//읽어온 다음에 mapper를 호출
		return board.getBno();
	}
	
	//한 건 읽어오기
	@Override
	public BoardVO get(Long bno) {
		log.info("get....");
		return mapper.read(bno);
	}
	
	//여러개 읽어오기
//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList....");
//		return mapper.getList();
//	}
	
	//
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList with criteria => " + cri);
		return mapper.getListWithPaging(cri);
	}
	
	//수정하기
	@Override
	public int modify(BoardVO board) {
		log.info("modify..." + board);
		return mapper.update(board);
	}
	
	//삭제하기
	@Override
	public boolean remove(Long bno) {
		log.info("remove...");
		return mapper.delete(bno)==1;
	}
	
	//검색한 페이지 총 수
	@Override
	public int getTotalCount(Criteria cri) {
		log.info("get total count...");
		return mapper.getTotalCount(cri);
	}


}
